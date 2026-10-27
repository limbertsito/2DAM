package psp.ut1.b4

import psp.ut1.util.procesoKotlin
import kotlin.time.measureTime

// B4.1 · Primos en paralelo. Completa los huecos marcados con TODO.
// El hijo (hijos/ContadorPrimos.kt) ya está hecho: «ContadorPrimos <desde> <hasta>» escribe
// por su stdout solo el número de primos del intervalo.

/** Ya hecho: divide [1, n] en k intervalos seguidos; devuelve pares (desde, hasta). */
fun intervalos(n: Long, k: Int): List<Pair<Long, Long>> {
    val trozos = mutableListOf<Pair<Long, Long>>()
    val tam = n / k
    var desde = 1L
    for (i in 1..k) {
        var hasta = desde + tam - 1
        if (i == k) hasta = n                       // el último se queda con el resto
        trozos.add(Pair(desde, hasta))
        desde = hasta + 1
    }
    return trozos
}

/** Cuenta los primos de [1, n] con [k] procesos hijo a la vez. Devuelve -1 si alguno falla. */
fun contarEnParalelo(n: Long, k: Int): Long {
    // TODO B4.1 · Paso 1 · Lanza un hijo por intervalo, TODOS a la vez, sin esperar a ninguno:
    //   procesoKotlin("psp.ut1.hijos.ContadorPrimosKt", desde.toString(), hasta.toString())
    //   Guarda cada Process en la lista «hijos».
    val hijos = mutableListOf<Process>()

    for ((desde, hasta) in intervalos(n, k)) {
        val proceso = procesoKotlin(
            "psp.ut1.hijos.ContadorPrimosKt",
            desde.toString(),
            hasta.toString()
        ).start()
        hijos.add(proceso)
    }
    // TODO B4.1 · Paso 2 · Ahora espera a todos, uno detrás de otro (barrera). De cada hijo:
    //   lee su stdout (inputStream.bufferedReader().use { it.readText() }), espera con
    //   waitFor() y, si el código no es 0, devuelve -1. Si va bien, suma el número leído
    //   (texto.trim().toLong()) a «total».
    var total = 0L
    for (hijo in hijos) {
        // Leemos la salida que produce el hijo por su stdout
        val texto = hijo.inputStream.bufferedReader().use { it.readText() }

        // Esperamos a que finalice y comprobamos su código de salida
        val codigo = hijo.waitFor()
        if (codigo != 0) {
            return -1L
        }

        // Sumamos los primos contados por este hijo
        total += texto.trim().toLong()
    }
    return total
}

/** Ya hecho: mide el tiempo con 1, 2, 4 y tantos procesos como núcleos. */
fun main() {
    val n = 10_000_000L
    val nucleos = Runtime.getRuntime().availableProcessors()
    println("Primos hasta $n · este equipo tiene $nucleos núcleos")
    println("  K  primos      tiempo (s)")
    for (k in listOf(1, 2, 4, nucleos, 2 * nucleos).distinct()) {   // distinct: sin repetir
        var primos = 0L
        val tiempo = measureTime { primos = contarEnParalelo(n, k) }
        println("%3d  %-10d  %.2f".format(k, primos, tiempo.inWholeMilliseconds / 1000.0))
    }
}
