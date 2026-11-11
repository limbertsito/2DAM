package com.iesvb.psp.ut2.b3

import com.iesvb.psp.ut2.log
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread
import kotlin.random.Random

// ReservasSeguras.kt · B3.3: reservar packs sin interbloqueo
// (código de partida: completa lo que indican los comentarios)
class ProductoSeguro(val nombre: String, var stock: Int) {
    val lock = ReentrantLock()                          // solo lo usa la solución 2
}

// Solución 1: orden global. Siempre se bloquea antes el producto con el nombre menor
fun reservarEnOrden(a: ProductoSeguro, b: ProductoSeguro): Boolean {
    var primero = a
    var segundo = b
    // 1. Si b.nombre < a.nombre, intercambia primero y segundo
    // 2. synchronized(primero) { synchronized(segundo) { … } }
    // 3. Dentro: si los dos tienen stock > 0, resta 1 a cada uno y devuelve true
    return false
}

// Solución 2: tryLock con tiempo máximo; si no se consiguen los dos, se suelta y se reintenta
fun reservarConTryLock(a: ProductoSeguro, b: ProductoSeguro): Boolean {
    while (true) {
        // 1. Si a.lock.tryLock(10, TimeUnit.MILLISECONDS) devuelve true:
        //      try { 2. Lo mismo con b.lock; si lo consigues:
        //                 try { reserva como en la solución 1 y return } finally { b.lock.unlock() }
        //      } finally { a.lock.unlock() }
        return false                                    // provisional: bórrala al completar los pasos
        // 3. Si no has conseguido los dos, espera al azar antes de reintentar
        Thread.sleep(Random.nextLong(1, 10))
    }
}

// 4 clientes hacen 100 reservas de packs al azar entre 4 productos con 1000 unidades cada uno.
// Devuelve el stock total que queda: debe ser 3200 (4000 - 4 clientes x 100 packs x 2 unidades).
fun simular(conTryLock: Boolean): Int {
    val productos = arrayOf(
        ProductoSeguro("cable", 1000), ProductoSeguro("monitor", 1000),
        ProductoSeguro("raton", 1000), ProductoSeguro("teclado", 1000),
    )
    val clientes = ArrayList<Thread>()
    for (c in 1..4) {
        clientes.add(thread(name = "cliente-$c") {
            for (i in 1..100) {
                val x = Random.nextInt(4)                   // dos productos distintos al azar
                var y = Random.nextInt(4)
                while (y == x) y = Random.nextInt(4)
                if (conTryLock) reservarConTryLock(productos[x], productos[y])
                else reservarEnOrden(productos[x], productos[y])
            }
        })
    }
    for (c in clientes) c.join()
    var total = 0
    for (p in productos) total += p.stock
    return total
}

fun main() {
    var inicio = System.currentTimeMillis()
    var total = simular(false)
    var ms = System.currentTimeMillis() - inicio
    log("orden global: stock total $total (esperado 3200) en $ms ms")
    inicio = System.currentTimeMillis()
    total = simular(true)
    ms = System.currentTimeMillis() - inicio
    log("tryLock:      stock total $total (esperado 3200) en $ms ms")
}
