package psp.ut1.util

import java.io.File

// PROPORCIONADO: no hace falta tocarlo. Utilidades para todos los bloques (apuntes 5.3).

/** `true` si el programa se está ejecutando sobre Windows. */
val esWindows: Boolean = System.getProperty("os.name").lowercase().contains("win")

/**
 * Crea un [ProcessBuilder] que arranca **otra JVM** con nuestro mismo classpath y ejecuta
 * la clase principal indicada. Así se lanza como proceso hijo cualquier `main` del proyecto.
 *
 * @param claseMain nombre de la clase que genera Kotlin, p. ej. `"psp.ut1.hijos.DormilonKt"`
 * @param args argumentos que recibirá el `main` del hijo
 */
fun procesoKotlin(claseMain: String, vararg args: String): ProcessBuilder {
    val java = File(System.getProperty("java.home"), "bin/java").path
    val classpath = System.getProperty("java.class.path")
    // UTF-8 en stdout/stderr del hijo: así tildes y eñes llegan bien al padre también en Windows
    val utf8 = listOf("-Dstdout.encoding=UTF-8", "-Dstderr.encoding=UTF-8")
    return ProcessBuilder(listOf(java) + utf8 + listOf("-cp", classpath, claseMain) + args)
}

/** Devuelve el comando necesario para que la shell del sistema interprete [linea]. */
fun comandoShell(linea: String): List<String> {
    if (esWindows) return listOf("cmd", "/c", linea)
    return listOf("sh", "-c", linea)
}
