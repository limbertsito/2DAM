package psp.ut1.hijos

import kotlin.system.exitProcess

// PROPORCIONADO: proceso hijo del B4.1. No hace falta tocarlo.

/** true si [n] es primo (división por tentativa hasta la raíz cuadrada). */
fun esPrimo(n: Long): Boolean {
    if (n < 2) return false
    if (n < 4) return true
    if (n % 2 == 0L) return false
    var d = 3L
    while (d * d <= n) {
        if (n % d == 0L) return false
        d += 2
    }
    return true
}

/**
 * Proceso hijo. Uso: ContadorPrimos <desde> <hasta>
 * Imprime SOLO el número de primos del intervalo [desde, hasta].
 * Códigos: 0 éxito · 2 argumentos incorrectos.
 */
fun main(args: Array<String>) {
    if (args.size != 2 || args[0].toLongOrNull() == null || args[1].toLongOrNull() == null) {
        System.err.println("Uso: ContadorPrimos <desde> <hasta>   (desde <= hasta)")
        exitProcess(2)
    }
    val desde = args[0].toLong()
    val hasta = args[1].toLong()
    if (desde > hasta) {
        System.err.println("Uso: ContadorPrimos <desde> <hasta>   (desde <= hasta)")
        exitProcess(2)
    }
    var cuenta = 0
    for (n in desde..hasta) {
        if (esPrimo(n)) cuenta++
    }
    println(cuenta)
}
