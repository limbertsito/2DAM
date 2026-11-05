package com.iesvb.psp.ut2.b1

import com.iesvb.psp.ut2.log
import kotlin.concurrent.thread
// Jornada.kt · B1.3: la jornada acaba al segundo; si el repartidor sigue, se le interrumpe
// (código de partida: completa lo que indican los comentarios)
fun main() {
    var hechas = 0 // entregas que lleva el repartidor
    val repartidor = thread(name = "repartidor") {
        // Dentro de un try { … } catch (e: InterruptedException) { … }:
        // try: 10 entregas; en cada una, duerme 300 ms, suma 1 a hechas y anótala con log.
        // Si acaba las 10, escribe "he terminado todas"
        // catch: sleep lanza InterruptedException si interrumpen al hilo mientras duerme.
        // Escribe con log cuántas entregas ha hecho y termina
        // (nunca dejes el catch vacío)
        try {
            for (i in 1..10){
                Thread.sleep(300)
                hechas++
                log("Entrega $hechas completada")
            }
            log("He terminado todas ")
        }catch (e: InterruptedException){
            log("$hechas enrtregas")
        }
    }
    // 1. Espera al repartidor como mucho 1 segundo: join(1_000)
    repartidor.join(1_000)
    // 2. Si sigue vivo (isAlive), escribe un aviso con log, interrúmpelo (interrupt)
    // y espera a que termine (join)
    if(repartidor.isAlive){
        log("El tiempo se ha agotado, interrumpo al repartidor: ")
        repartidor.interrupt()
        repartidor.join()

    }
    // 3. Escribe con log las entregas hechas y si el repartidor sigue vivo
    log("Jornada terminada. Enregas hechas : $hechas , sigue viva? ${repartidor.isAlive}")
}