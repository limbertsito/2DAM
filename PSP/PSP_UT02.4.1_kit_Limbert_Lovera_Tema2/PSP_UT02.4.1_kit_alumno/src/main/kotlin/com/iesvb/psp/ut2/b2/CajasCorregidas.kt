package com.iesvb.psp.ut2.b2

import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

// CajasCorregidas.kt · B2.3: la caja de CajaRegistradora.kt corregida de tres formas
// (código de partida: completa lo que indican los comentarios)
// CAJEROS y COBROS vienen de CajaRegistradora.kt (mismo paquete).
class CajaSynchronized {
    var totalCentimos = 0

    // Forma 1: marca la función con @Synchronized
    fun cobrar(centimos: Int) {
        totalCentimos += centimos
    }
}

class CajaLock {
    val lock = ReentrantLock()
    var totalCentimos = 0

    // Forma 2: lock.lock() antes de sumar y lock.unlock() dentro de un finally
    fun cobrar(centimos: Int) {
        totalCentimos += centimos
    }
}

class CajaAtomica {
    val totalCentimos = AtomicInteger()

    // Forma 3: suma con totalCentimos.addAndGet(centimos)
    fun cobrar(centimos: Int) {
    }
}

fun main() {
    val esperado = CAJEROS * COBROS

    // La primera caja ya está hecha: copia este trozo para las otras dos
    // (en cada copia, cambia el nombre «a» por «b» y por «d»: dos val no pueden llamarse igual)
    var inicio = System.currentTimeMillis()
    val a = CajaSynchronized()
    var cajeros = ArrayList<Thread>()
    for (i in 1..CAJEROS) cajeros.add(thread { for (j in 1..COBROS) a.cobrar(1) })
    for (c in cajeros) c.join()
    var ms = System.currentTimeMillis() - inicio
    println("synchronized:  ${a.totalCentimos} de $esperado en $ms ms")

    // Repite con una CajaLock: "ReentrantLock: … de … en … ms"

    // Repite con una CajaAtomica: "AtomicInteger: … de … en … ms"
    // (el total se lee con totalCentimos.get())
}
