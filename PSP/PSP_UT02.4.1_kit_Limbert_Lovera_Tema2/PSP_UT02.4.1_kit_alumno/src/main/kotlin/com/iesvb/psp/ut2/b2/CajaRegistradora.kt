package com.iesvb.psp.ut2.b2

import kotlin.concurrent.thread

// CajaRegistradora.kt · B2.2: cuatro cajeros cobran en la misma caja (código de partida)
// La caja guarda céntimos (Int) para evitar los redondeos de Double.
class Caja {
    @Volatile var totalCentimos = 0                // @Volatile: ver apuntes, apartado 4.1

    fun cobrar(centimos: Int) {
        totalCentimos += centimos                   // leer, sumar y escribir: no es atómico
    }
}

val CAJEROS = 4
val COBROS = 50_000

fun probarCaja(caja: Caja) {
    val cajeros = ArrayList<Thread>()
    for (i in 1..CAJEROS) {
        cajeros.add(thread {
            for (j in 1..COBROS) caja.cobrar(1)
        })
    }
    for (c in cajeros) c.join()
}

fun main() {
    val esperado = CAJEROS * COBROS
    for (intento in 1..10) {
        val caja = Caja()
        probarCaja(caja)
        val perdido = esperado - caja.totalCentimos
        val total = caja.totalCentimos
        println("Intento %2d: %6d céntimos, se pierden %6d".format(intento, total, perdido))
    }
}
