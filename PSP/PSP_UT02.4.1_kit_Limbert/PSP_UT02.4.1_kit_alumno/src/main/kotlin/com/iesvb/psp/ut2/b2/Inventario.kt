package com.iesvb.psp.ut2.b2

import kotlin.concurrent.thread

// Inventario.kt · B2.3: el inventario que vende de más (código de partida)
class Inventario(var stock: Int) {
    fun vender(): Boolean {
        if (stock > 0) {
            Thread.sleep(1)                 // entre comprobar y restar puede colarse otro
            stock--
            return true
        }
        return false
    }
}

fun main() {
    for (intento in 1..5) {
        val inventario = Inventario(100)
        val cajas = ArrayList<Thread>()
        for (i in 1..4) cajas.add(thread { for (j in 1..50) inventario.vender() })
        for (c in cajas) c.join()
        println("Intento $intento: stock final ${inventario.stock}")
    }
}
