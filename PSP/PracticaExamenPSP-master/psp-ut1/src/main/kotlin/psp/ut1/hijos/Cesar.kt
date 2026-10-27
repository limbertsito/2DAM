package psp.ut1.hijos

import kotlin.system.exitProcess

// PROPORCIONADO: proceso hijo del B3.1. No hace falta tocarlo.

/** Desplaza una letra A-Z / a-z [d] posiciones (también negativas); el resto se deja igual. */
fun cifrarCaracter(c: Char, d: Int): Char {
    if (c in 'A'..'Z') return 'A' + Math.floorMod(c - 'A' + d, 26)
    if (c in 'a'..'z') return 'a' + Math.floorMod(c - 'a' + d, 26)
    return c
}

/** Cifra un texto completo carácter a carácter. */
fun cifrar(texto: String, d: Int): String {
    val resultado = StringBuilder()
    for (c in texto) {
        resultado.append(cifrarCaracter(c, d))
    }
    return resultado.toString()
}

/**
 * Proceso hijo. Uso: Cesar <desplazamiento>
 * Lee su stdin hasta EOF y escribe cada línea cifrada por stdout.
 * Códigos: 0 éxito · 2 desplazamiento no numérico o ausente.
 */
fun main(args: Array<String>) {
    if (args.size != 1 || args[0].toIntOrNull() == null) {
        System.err.println("Cesar: el desplazamiento debe ser un número entero")
        exitProcess(2)
    }
    val d = args[0].toInt()
    while (true) {
        val linea = readlnOrNull() ?: break      // null = EOF: el padre cerró nuestro stdin
        println(cifrar(linea, d))
    }
}
