package com.iesvb.psp.ut2.s5.b1

import com.iesvb.psp.ut2.log
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.concurrent.thread

// Jornada.kt · B1.3: la jornada acaba al segundo; si el repartidor sigue, se le interrumpe
// (código de partida: completa lo que indican los comentarios)
fun main() {
    var hechas = 0                  // entregas que lleva el repartidor

    runBlocking {
        val repartidor = launch {
            try {
                for (i in 1..10){
                    delay(300)
                    hechas++
                    log("entrega $i")
                }
                log("he terminado todas!!")
            }catch (e: CancellationException){
                log("fin de la jornada : dejo el reparto tras $hechas entregas")
                throw e
            }

        }
        val acabo = withTimeoutOrNull(1_000){
            repartidor.join()
        }
        if (acabo ==null){
            log("Ha pasado el timepo lo cancelo")
            repartidor.cancelAndJoin()  //Cancela y lanza la excepcion y espera a que acaben
        }
        log("Entregas hechas : $hechas sigue activo¿? ${repartidor.isActive}")
    }
}
