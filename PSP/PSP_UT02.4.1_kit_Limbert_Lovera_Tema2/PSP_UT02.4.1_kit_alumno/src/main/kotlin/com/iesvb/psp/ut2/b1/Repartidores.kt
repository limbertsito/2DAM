package com.iesvb.psp.ut2.s5

import com.iesvb.psp.ut2.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

// Repartidores.kt · B1.1: tres repartidores entregan a la vez y main espera a todos
// (código de partida: completa lo que indican los comentarios)
class Repartidor(val entregas: Int) {
    var hechas = 0 // entregas que lleva este repartidor
    suspend fun repartir(nombre: String) {
        for (i in 1..entregas) {
            delay(Random.nextLong(50, 150))
            hechas++
            log("$nombre: entrega $i de $entregas")
        }
    }
}
fun main() {
    val repartidores = ArrayList<Repartidor>()

    runBlocking {
        for (n in 1..3) {
            // 1. Crea un Repartidor con 4 entregas y añádelo a la lista de repartidores
            val repartidor = Repartidor(4)
            repartidores.add(repartidor)
            // 2. Creo una corrutina con el nombre "repartidor-n" y la lanzo
            launch { repartidor.repartir("repartidor-$n") }
        }
    }

    // Suma las entregas hechas por cada repartidor y escribe el total con log
    var totalEntregas = 0
    for (r in repartidores){
        totalEntregas += r.hechas
    }

    log("reparto terminado: ${repartidores.size}, $totalEntregas entregas")
}
 