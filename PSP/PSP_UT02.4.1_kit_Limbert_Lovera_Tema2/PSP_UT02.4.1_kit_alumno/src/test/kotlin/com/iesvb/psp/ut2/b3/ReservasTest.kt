package com.iesvb.psp.ut2.b3

import kotlin.concurrent.thread
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

// ReservasTest.kt · B3.3: las reservas en orden global no se bloquean y conservan el stock
// (código de partida: completa lo que indican los comentarios)
class ReservasTest {
    @Test
    fun cuatroClientesSinInterbloqueo() {
        for (repeticion in 1..10) {
            var total = 0
            // 1. Lanza la simulación en un hilo aparte: val prueba = thread { total = simular(false) }
            // 2. Espérala como mucho 10 segundos: prueba.join(10_000)
            // 3. assertFalse(prueba.isAlive, "…"): si sigue viva, está bloqueada
            // 4. assertEquals(3200, total)
        }
    }
}
