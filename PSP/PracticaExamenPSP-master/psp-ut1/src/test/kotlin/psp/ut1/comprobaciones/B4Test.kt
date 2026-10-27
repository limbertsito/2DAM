package psp.ut1.comprobaciones

import psp.ut1.b4.contarEnParalelo
import psp.ut1.b4.ejecutarGrafo
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/** Comprobaciones del bloque 4. Solo estas: gradlew test --tests "*B4Test" (tarda unos 12 s) */
class B4Test {

    @Test
    fun `B4_1 hay 9592 primos hasta 100000 con 1 y con 4 procesos`() {
        assertEquals(9592L, contarEnParalelo(100_000, 1))
        assertEquals(9592L, contarEnParalelo(100_000, 4))
    }

    @Test
    fun `B4_2 todo bien y B y C a la vez`() {
        val inicio = System.currentTimeMillis()
        val informe = ejecutarGrafo(0)
        val segundos = (System.currentTimeMillis() - inicio) / 1000.0
        assertEquals(listOf("A ok", "B ok", "C ok", "D ok"), informe)
        assertTrue(segundos < 7.5, "Tardó $segundos s: B y C no se ejecutan a la vez (serían 8 s)")
    }

    @Test
    fun `B4_2 si C falla D se cancela`() {
        assertEquals(listOf("A ok", "B ok", "C falla (5)", "D cancelada"), ejecutarGrafo(5))
    }
}
