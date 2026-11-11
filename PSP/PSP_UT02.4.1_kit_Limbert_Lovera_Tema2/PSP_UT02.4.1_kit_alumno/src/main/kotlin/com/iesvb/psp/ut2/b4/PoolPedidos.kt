package com.iesvb.psp.ut2.b4

import com.iesvb.psp.ut2.log
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

// PoolPedidos.kt · B4.2: un pool de 4 hilos calcula el importe de 20 pedidos
// (código de partida: completa lo que indican los comentarios)
class CalcularImporte(val id: Int) : Callable<Int> {
    override fun call(): Int {
        Thread.sleep(100)                           // simula consultar precios
        val importe = id * 250                      // en céntimos
        log("pedido $id: $importe céntimos")
        return importe
    }
}

fun main() {
    // 1. Crea un pool de 4 hilos: Executors.newFixedThreadPool(4)
    val inicio = System.currentTimeMillis()
    val futuros = ArrayList<Future<Int>>()
    // 2. Para id de 1 a 20, envía un CalcularImporte(id) con pool.submit(…) y guarda el Future en futuros

    var total = 0
    // 3. Suma el resultado de cada Future con get()

    val ms = System.currentTimeMillis() - inicio
    // 4. Cierra el pool con shutdown() y espera a que termine: awaitTermination(5, TimeUnit.SECONDS)
    //    devuelve true si ha terminado a tiempo
    val terminado = false
    log("total $total céntimos en $ms ms · pool terminado: $terminado")
}
