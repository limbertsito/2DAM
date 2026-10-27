package psp.ut1

import psp.ut1.util.procesoKotlin
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/** Pruebas que verifican el entorno. Ejecutar con: ./gradlew test */
class EntornoTest {

    @Test
    fun `se puede lanzar un hijo Kotlin y recoger su codigo de salida`() {
        val hijo = procesoKotlin("psp.ut1.hijos.DormilonKt", "test", "0", "3")
            .redirectErrorStream(true)
            .start()
        val salida = hijo.inputStream.bufferedReader().use { it.readText() }
        assertEquals(3, hijo.waitFor())
        assertTrue(salida.contains("[test] termino"))   // texto sin tildes: no depende de la codificación
    }
}
