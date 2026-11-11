package com.iesvb.psp.ut2.b2

import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

// InventarioCorregido.kt · B2.3: el inventario de Inventario.kt, corregido
// (código de partida: completa lo que indican los comentarios)
class InventarioSeguro(var stock: Int) {
    // Protege la función ENTERA (comprobar y restar) con @Synchronized
    fun vender(): Boolean {
        if (stock > 0) {
            Thread.sleep(1)
            stock--
            return true
        }
        return false
    }
}

fun main() {
    for (intento in 1..5) {
        val inventario = InventarioSeguro(100)
        val vendidas = AtomicInteger()                 // la suman cuatro hilos: por eso es atómica
        val cajas = ArrayList<Thread>()
        for (i in 1..4) {
            cajas.add(thread {
                // 50 veces: si inventario.vender() devuelve true, suma 1 a vendidas (incrementAndGet)
            })
        }
        for (c in cajas) c.join()
        println("Intento $intento: stock final ${inventario.stock}, vendidas ${vendidas.get()}")
    }
}
