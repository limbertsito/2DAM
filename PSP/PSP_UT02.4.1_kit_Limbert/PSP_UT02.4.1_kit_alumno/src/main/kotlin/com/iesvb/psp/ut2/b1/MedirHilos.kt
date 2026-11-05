package com.iesvb.psp.ut2.b1

// MedirHilos.kt · B1.4: tareas que esperan 1 s con hilos de plataforma y con virtuales
// (código de partida: completa lo que indican los comentarios)
// La tarea: solo espera un segundo, como en Virtuales.kt (apuntes, 7.4)
class Espera : Runnable {
    override fun run() {
        Thread.sleep(1_000)
    }
}
// Lanza "tareas" hilos (virtuales o de plataforma) y devuelve cuántos ms tardan todos
fun medir(tareas: Int, virtuales: Boolean): Long {
    // 1. Guarda la hora de inicio: System.currentTimeMillis()
   val inicio = System.currentTimeMillis()
    val hilos = ArrayList<Thread>()
    // 2. Crea una lista de hilos y repite "tareas" veces: lanza un hilo con Espera()

    for (i in 1..tareas){
        val hilo = if (virtuales){
            Thread.ofVirtual().start(Espera())
        }else{
            Thread.ofPlatform().start(Espera())
        }
        hilos.add(hilo)
    }

    // - si virtuales es true: Thread.ofVirtual().start(Espera())
    // - si no: Thread.ofPlatform().start(Espera())
    // y añádelo a la lista
    // 3. Espera a que terminen todos (join)
    // 4. Devuelve la hora actual menos la de inicio
    for (h in hilos) h.join()
    return System.currentTimeMillis() // cámbialo por el tiempo medido
}
fun main() {
    println("Tareas Plataforma Virtuales")
    // Para 100, 1 000 y 10 000 tareas (for (tareas in intArrayOf(100, 1_000, 10_000))):
    // 1. Mide con hilos de plataforma y después con virtuales
    // 2. Escribe una fila de la tabla. Para alinear las columnas:
    // println("%6d %7d ms %6d ms".format(tareas, plataforma, virtuales))
    for (tareas in intArrayOf(100, 1_000, 10_000)) {
        val plataforma = medir(tareas, false)
        val virtuales = medir(tareas, true)

        println("%6d   %7d ms   %6d ms".format(tareas, plataforma, virtuales))
    }
}