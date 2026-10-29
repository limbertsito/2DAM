package psp.ut1.b3

import psp.ut1.util.procesoKotlin
import java.io.File

// B3.1 · Filtro por lotes: cifrado César. Completa los huecos marcados con TODO.
// El hijo (hijos/Cesar.kt) ya está hecho: lee líneas de su stdin hasta el final (EOF) y
// escribe cada una cifrada por su stdout.

/** Lo que devuelve [cifrarConHijo]: las líneas cifradas y el código de salida del hijo. */
data class RespuestaCesar(val lineas: List<String>, val codigo: Int)

/** Envía [lineas] al hijo Cesar por su stdin y recoge lo que devuelve por su stdout. */
fun cifrarConHijo(lineas: List<String>, desplazamiento: String): RespuestaCesar {
    // TODO B3.1 · Paso 1 · Lanza el hijo: procesoKotlin("psp.ut1.hijos.CesarKt", desplazamiento).
    //   Sus errores, a nuestra consola: redirectError(ProcessBuilder.Redirect.INHERIT).
    val hijo: Process = procesoKotlin("psp.ut1.hijos.CesarKt", desplazamiento)
        .redirectError(ProcessBuilder.Redirect.INHERIT)
        .start()

    // TODO B3.1 · Paso 2 · Escribe cada línea en el stdin del hijo, seguida de newLine().
    //   Para nosotros su stdin es hijo.outputStream; usa bufferedWriter().use { w -> ... }.
    //   Al acabar el use { } el stream se cierra: el hijo recibe EOF y sabe que no hay más.
    hijo.outputStream.bufferedWriter().use {
        for(linea in lineas){
            it.write("$linea")
            it.newLine()
        }

    }

    // TODO B3.1 · Paso 3 · Lee todas las líneas del stdout del hijo (hijo.inputStream):
    //   bufferedReader().use { it.readLines() }. Después espera al hijo con waitFor().
    val cifradas: List<String> = hijo.inputStream.bufferedReader().use {
        it.readLines()
    }
    val codigo: Int = hijo.waitFor()

    return RespuestaCesar(cifradas, codigo)
}

/** Uso: ClienteCesar <entrada.txt> <salida.txt> <desplazamiento> */
fun main(args: Array<String>) {
    if (args.size != 3) {
        println("Uso: ClienteCesar <entrada.txt> <salida.txt> <desplazamiento>")
        return
    }
    val entrada = File(args[0]).readLines()
    val respuesta = cifrarConHijo(entrada, args[2])
    File(args[1]).writeText(respuesta.lineas.joinToString("\n", postfix = "\n"))
    println("Líneas procesadas: ${respuesta.lineas.size} · código del hijo: ${respuesta.codigo}")
}
