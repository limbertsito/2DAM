package psp.ut1.comprobaciones

import psp.ut1.proyecto.analizar
import psp.ut1.proyecto.analizarEnParalelo
import psp.ut1.proyecto.analizarEnSecuencia
import psp.ut1.proyecto.buscarTxt
import java.io.File
import java.nio.file.Files
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/** Comprobaciones del bloque 5. Solo estas: gradlew test --tests "*B5Test" (tarda unos 10 s) */
class B5Test {
    private val carpeta: File = Files.createTempDirectory("salidas").toFile()

    @Test
    fun `B5 corpus en paralelo y en secuencia con los totales correctos`() {
        val ficheros = buscarTxt(File("corpus"))
        val paralelo = analizarEnParalelo(ficheros, carpeta, 20)
        assertEquals(ficheros.size, paralelo.size)
        for (i in ficheros.indices) {
            assertEquals("OK", paralelo[i].estado, ficheros[i].name)
            val esperado = ficheros[i].bufferedReader().useLines { analizar(it) }
            assertEquals(esperado, paralelo[i].datos, ficheros[i].name)
        }
        assertEquals(paralelo, analizarEnSecuencia(ficheros, carpeta, 20))
    }

    @Test
    fun `B5 un fichero que falla y uno que se pasa del tiempo`() {
        val inicio = System.currentTimeMillis()
        val r = analizarEnParalelo(buscarTxt(File("corpus-errores")), carpeta, 3)
        val segundos = (System.currentTimeMillis() - inicio) / 1000.0
        assertEquals("cuento.txt", r[0].fichero)
        assertEquals("OK", r[0].estado)
        assertEquals("ilegible.txt", r[1].fichero)
        assertTrue(r[1].estado.startsWith("FALLO (2)"), r[1].estado)
        assertEquals("lento.txt", r[2].fichero)
        assertEquals("TIMEOUT", r[2].estado)
        assertTrue(segundos < 7, "Tardó $segundos s: el trabajador lento no se destruyó a tiempo")
        assertEquals(0L, ProcessHandle.current().children().count(), "Quedan trabajadores vivos")
    }
}
