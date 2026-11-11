package com.iesvb.psp.ut2.b2

import com.iesvb.psp.ut2.log
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

// Facturacion.kt · B2.1: la facturación de tres tiendas, calculada en paralelo
// (código de partida: completa lo que indican los comentarios)
class FacturarTienda(val ventas: DoubleArray) : Callable<Double> {
    override fun call(): Double {
        log("sumo ${ventas.size} ventas")
        Thread.sleep(500)                           // simula leer las ventas de la tienda
        // Suma las ventas con un bucle for y devuelve el total
        var suma= 0.0
        for (i in ventas.indices){
            suma +=ventas[i]
        }
        return suma
    }
}

fun main() {
    val tiendas = arrayOf(
        doubleArrayOf(19.95, 4.50, 32.00),
        doubleArrayOf(120.00, 15.25),
        doubleArrayOf(9.99, 9.99, 9.99, 9.99),
    )
    val inicio = System.currentTimeMillis()
    val tareas = ArrayList<FutureTask<Double>>()
    // Para cada tienda (i en tiendas.indices):
    for (i in tiendas.indices) {

        //   1. Crea un FutureTask con un FacturarTienda de sus ventas y añádelo a tareas
        var tarea = FutureTask(FacturarTienda(tiendas[i]))


        //   2. Arranca un hilo con esa tarea, llamado "tienda-1", "tienda-2"…: Thread(tarea, nombre).start()
        Thread(tarea, "tienda-${i + 1}").start()
        tareas.add(tarea)
    }
        var total= 0.0
        for (t in tareas.indices){
            total+=tareas[t].get()
        }


    // Suma el resultado de cada tarea con get(): espera a que su hilo termine

    val ms = System.currentTimeMillis() - inicio
    log("facturación total: %.2f € en %d ms".format(total, ms))
}
