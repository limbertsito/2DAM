package com.iesvb.psp.ut2.b1

import com.iesvb.psp.ut2.log
import kotlin.random.Random

// Repartidores.kt · B1.1: tres repartidores entregan a la vez y main espera a todos
// (código de partida: completa lo que indican los comentarios)
class Repartidor(val entregas: Int) : Runnable {
    var hechas = 0                  // entregas que lleva este repartidor

    override fun run() {
        // Repite tantas veces como entregas tenga el repartidor:
        //   1. Simula lo que tarda la entrega: Thread.sleep(Random.nextLong(50, 150))
        //      (da de 50 a 149 ms: el segundo valor no se incluye)
        //   2. Suma 1 a hechas
        //   3. Anótala con log: "entrega 1 de 4", "entrega 2 de 4"…
    }
}

fun main() {
    // Crea dos listas: una de repartidores, ArrayList<Repartidor>(),
    // y otra de hilos, ArrayList<Thread>()

    // Repite 3 veces (n = 1, 2, 3):
    //   1. Crea un Repartidor con 4 entregas y añádelo a la lista de repartidores
    //   2. Crea su hilo con el nombre "repartidor-n" y añádelo a la lista de hilos
    //   3. Arranca el hilo

    // Espera a que terminen todos los hilos (join)

    // Suma las entregas hechas por cada repartidor y escribe el total con log
}
