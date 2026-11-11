package com.iesvb.psp.ut2.b4

import java.util.concurrent.Executors
import java.util.concurrent.Future

// MedirTienda.kt · B4.4: ¿cuánto se gana con más hilos? Calcular frente a esperar
// (código de partida: completa lo que indican los comentarios)
class Trabajo(val calcular: Boolean) : Runnable {
    override fun run() {
        if (calcular) {
            var hash = 0L                           // tarea de cálculo: muchas operaciones
            for (i in 1..300_000_000) hash = hash * 31 + i
            if (hash == 42L) println("improbable")  // usa el resultado: así el JIT no lo quita
        } else {
            Thread.sleep(200)                       // tarea que espera a otro servicio
        }
    }
}

// Ejecuta 16 trabajos en un pool de «hilos» hilos y devuelve los milisegundos que tarda
fun medir(calcular: Boolean, hilos: Int): Long {
    val pool = Executors.newFixedThreadPool(hilos)
    val inicio = System.currentTimeMillis()
    val pendientes = ArrayList<Future<*>>()
    // 1. Envía 16 Trabajo(calcular) con pool.submit(…) y guarda los Future en pendientes
    // 2. Espera a todos con get()
    pool.shutdown()
    return System.currentTimeMillis() - inicio
}

fun main() {
    val nucleos = Runtime.getRuntime().availableProcessors()
    Trabajo(true).run()                             // calentamiento del JIT
    println("16 tareas · núcleos: $nucleos")
    println("Hilos   Cálculo   Espera")
    // Para h en intArrayOf(1, 2, nucleos, 16), escribe una fila con medir(true, h) y medir(false, h):
    //   println("%5d   %5d ms  %5d ms".format(h, …, …))
}
