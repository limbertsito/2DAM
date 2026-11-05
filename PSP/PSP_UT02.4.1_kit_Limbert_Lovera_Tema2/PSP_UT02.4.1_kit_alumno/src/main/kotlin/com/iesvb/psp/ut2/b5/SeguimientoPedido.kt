package com.iesvb.psp.ut2.b5

import com.iesvb.psp.ut2.log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

// SeguimientoPedido.kt · B5.4: cancelar, tiempo máximo y un fallo que cancela al resto
// (código de partida: completa lo que indican los comentarios)
fun main() {
    runBlocking {
        // Situación 1: el seguimiento del envío se cancela a los 450 ms
        val seguimiento = launch {
            try {
                for (km in 10..1000 step 10) {
                    delay(100)
                    log("el paquete está a $km km")
                }
            } catch (e: CancellationException) {
                log("seguimiento cancelado: el cliente ha cerrado la página")
                // Vuelve a lanzar la excepción: throw e
            }
        }
        // Espera 450 ms con delay, cancela el seguimiento con cancel() y espera a que acabe con join()

        // Situación 2: el banco tarda 500 ms y solo le damos 200
        val confirmacion = withTimeoutOrNull(200) {
            delay(500)
            "pago confirmado"
        }
        // Si confirmacion es null, escribe "el banco no responde en 200 ms: queda pendiente";
        // si no, escribe confirmacion

        // Situación 3: dentro de un coroutineScope, una corrutina falla y la otra no llega a imprimir
        try {
            coroutineScope {
                // 1. launch { delay(300); log("etiqueta impresa") }
                // 2. launch { delay(100); throw IllegalStateException("código postal inexistente") }
            }
        } catch (e: IllegalStateException) {
            log("pedido anulado: ${e.message}")
        }
    }
}
