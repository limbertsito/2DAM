package com.iesvb.psp.ut2.b5

import com.iesvb.psp.ut2.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

// RepartoCorrutinas.kt · B5.1: tres repartidores como corrutinas, en main y en Default
// (código de partida: completa lo que indican los comentarios)
suspend fun repartir(nombre: String) {
    // 3 entregas: en cada una, espera con delay(Random.nextLong(50, 150))
    // y anótala con log("$nombre: entrega $i")
}

fun main() {
    runBlocking {
        log("--- sin dispatcher ---")
        // Lanza tres corrutinas: launch { repartir("A") }, y lo mismo con "B" y "C"
    }
    runBlocking {
        log("--- con Dispatchers.Default ---")
        // Lo mismo, pero con launch(Dispatchers.Default) { … }
    }
}
