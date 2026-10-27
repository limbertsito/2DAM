package psp.ut1.util

import java.io.File

/** `true` si el programa se está ejecutando sobre Windows. */
val esWindows: Boolean = System.getProperty("os.name").lowercase().contains("win")

/**
 * Crea un [ProcessBuilder] que arranca **otra JVM** con nuestro mismo classpath
 * y ejecuta la clase principal indicada.
 *
 * Así podemos lanzar como proceso hijo cualquier `main` de nuestro propio proyecto
 * (desde el IDE, desde un JAR o desde la terminal) sin preocuparnos de rutas.
 *
 * @param claseMain nombre completo de la clase generada por Kotlin,
 *        p. ej. `"psp.ut1.hijos.MayusculasKt"`
 * @param args argumentos que recibirá el `main` del hijo
 */
fun procesoKotlin(claseMain: String, vararg args: String): ProcessBuilder {
    val java = File(System.getProperty("java.home"), "bin/java").path
    val classpath = System.getProperty("java.class.path")
    return ProcessBuilder(listOf(java, "-cp", classpath, claseMain) + args)
}

/** Devuelve el comando necesario para que la shell del sistema interprete [linea]. */
fun comandoShell(linea: String): List<String> =
    if (esWindows) listOf("cmd", "/c", linea) else listOf("sh", "-c", linea)
