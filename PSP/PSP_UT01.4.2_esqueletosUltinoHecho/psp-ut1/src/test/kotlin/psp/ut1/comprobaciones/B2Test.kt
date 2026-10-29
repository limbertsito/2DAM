package psp.ut1.comprobaciones

import psp.ut1.b2.ejecutar
import psp.ut1.b2.hijos
import psp.ut1.b2.listar
import psp.ut1.b2.rama
import psp.ut1.b2.vigilar
import psp.ut1.util.comandoShell
import psp.ut1.util.esWindows
import psp.ut1.util.procesoKotlin
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

/** Comprobaciones del bloque 2. Solo estas: gradlew test --tests "*B2Test" */
class B2Test {

    @Test
    fun `B2_1 un comando correcto devuelve su salida y codigo 0`() {
        val r = ejecutar(comandoShell("echo hola"))
        assertEquals(0, r.codigo)
        assertTrue(r.salida.contains("hola"))
        assertFalse(r.timeout)
    }

    @Test
    fun `B2_1 un comando que falla devuelve codigo distinto de 0 y su error`() {
        var linea = "ls /no_existe_psp"
        if (esWindows) linea = "dir C:\\no_existe_psp"
        val r = ejecutar(comandoShell(linea))
        assertNotEquals(0, r.codigo)
        assertTrue(r.errores.isNotBlank(), "El mensaje de error debería estar en «errores»")
    }

    @Test
    fun `B2_1 un comando lento se destruye al llegar al timeout`() {
        var lento = listOf("sleep", "30")
        if (esWindows) lento = listOf("ping", "-n", "30", "127.0.0.1")
        val inicio = System.currentTimeMillis()
        val r = ejecutar(lento, 2)
        val ms = System.currentTimeMillis() - inicio
        assertTrue(r.timeout)
        assertEquals(-1, r.codigo)
        assertTrue(ms < 10_000, "Tardó $ms ms: no se destruyó a tiempo")
    }

    @Test
    fun `B2_2 el listado incluye nuestro propio proceso`() {
        val miPid = ProcessHandle.current().pid().toString()
        var aparece = false
        for (linea in listar()) {
            if (linea.trim().startsWith("$miPid ")) aparece = true
        }
        assertTrue(aparece, "No aparece nuestro PID $miPid")
    }

    @Test
    fun `B2_2 hijos muestra a un hijo nuestro`() {
        val hijo = procesoKotlin("psp.ut1.hijos.DormilonKt", "prueba", "10")
            .redirectOutput(ProcessBuilder.Redirect.DISCARD).start()
        val lineas = hijos(ProcessHandle.current().pid())
        hijo.destroyForcibly()
        hijo.waitFor()
        var aparece = false
        for (linea in lineas) {
            if (linea.trim().startsWith("${hijo.pid()} ")) aparece = true
        }
        assertTrue(aparece, "No aparece el hijo ${hijo.pid()} en:\n${lineas.joinToString("\n")}")
    }

    @Test
    fun `B2_2 hijos de un PID que no existe lo dice`() {
        assertTrue(hijos(999_999_999).first().startsWith("No existe"))
    }

    @Test
    fun `B2_2 la rama empieza en nuestro proceso y sube`() {
        val lineas = rama()
        val miPid = ProcessHandle.current().pid().toString()
        assertTrue(lineas[1].trim().startsWith("$miPid "), "La rama debe empezar en $miPid")
        assertTrue(lineas.size >= 3, "Debería haber al menos nuestro proceso y su padre")
    }

    @Test
    fun `B2_3 un programa que termina antes del limite`() {
        val pb = procesoKotlin("psp.ut1.hijos.DormilonKt", "rapido", "1", "4")
        assertEquals("normal", vigilar(pb, 10))
    }

    @Test
    fun `B2_3 un programa que se pasa del limite se destruye`() {
        val pb = procesoKotlin("psp.ut1.hijos.DormilonKt", "lento", "30")
        assertEquals("destroy", vigilar(pb, 2))
    }
}
