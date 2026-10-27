package psp.ut1.comprobaciones

import psp.ut1.b3.ClienteKV
import psp.ut1.b3.cifrarConHijo
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/** Comprobaciones del bloque 3. Solo estas: gradlew test --tests "*B3Test" */
class B3Test {

    @Test
    fun `B3_1 cifra con el hijo y descifra con el desplazamiento contrario`() {
        val texto = listOf("Hola, Mundo", "", "Zorro xyz 123")
        val cifrado = cifrarConHijo(texto, "3")
        assertEquals(0, cifrado.codigo)
        assertEquals(listOf("Krod, Pxqgr", "", "Cruur abc 123"), cifrado.lineas)
        val descifrado = cifrarConHijo(cifrado.lineas, "-3")
        assertEquals(texto, descifrado.lineas)
    }

    @Test
    fun `B3_1 el hijo termina con codigo 2 si el desplazamiento no es un numero`() {
        assertEquals(2, cifrarConHijo(listOf(), "x").codigo)
    }

    @Test
    fun `B3_2 una conversacion completa con el almacen`() {
        val log = File.createTempFile("almacen", ".log")
        val cliente = ClienteKV(log)
        assertEquals("OK", cliente.enviar("SET ciudad Valladolid"))
        assertEquals("VALOR Valladolid", cliente.enviar("GET ciudad"))
        assertEquals("NO_EXISTE", cliente.enviar("GET pais"))
        assertEquals("CLAVES 1 ciudad", cliente.enviar("KEYS"))
        assertEquals("ADIOS", cliente.enviar("QUIT"))
        assertEquals(0, cliente.cerrar())
        assertTrue(log.readText().contains("petición: GET ciudad"), "El log debería tener las peticiones")
        log.delete()
    }
}
