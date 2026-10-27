package psp.ut1.proyecto

import java.io.File
import java.io.FileNotFoundException
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

// B5 · Trabajador. PROPORCIONADO: no hace falta tocarlo, pero léelo, porque el Orquestador
// tiene que entender su salida, sus errores y sus códigos.

/**
 * Proceso hijo. Uso: Trabajador <ruta-del-fichero>
 *
 * Analiza el fichero (UTF-8) y escribe el resultado por **stdout** con este protocolo,
 * una información por línea:
 *
 *     FICHERO <nombre>
 *     LINEAS <n>
 *     PALABRAS <n>
 *     CARACTERES <n>
 *     PALABRA <palabra> <frecuencia>      (una línea por cada palabra distinta)
 *     FIN
 *
 * Los mensajes de error van por **stderr**. Códigos de salida:
 * 0 bien · 1 uso incorrecto · 2 no se puede leer el fichero · 3 otro error.
 *
 * Si existe la variable de entorno TRABAJADOR_RETARDO_MS, espera esos milisegundos antes
 * de terminar (sirve para probar el timeout del Orquestador).
 */
fun main(args: Array<String>) {
    if (args.size != 1) {
        System.err.println("Uso: Trabajador <ruta-del-fichero>")
        exitProcess(1)
    }
    val fichero = File(args[0])
    try {
        val estadisticas = fichero.bufferedReader().useLines { lineas -> analizar(lineas) }

        val retardo = System.getenv("TRABAJADOR_RETARDO_MS")
        if (retardo != null && retardo.toLongOrNull() != null) {
            TimeUnit.MILLISECONDS.sleep(retardo.toLong())
        }

        val salida = System.out.bufferedWriter()
        salida.write("FICHERO ${fichero.name}\n")
        salida.write("LINEAS ${estadisticas.lineas}\n")
        salida.write("PALABRAS ${estadisticas.palabras}\n")
        salida.write("CARACTERES ${estadisticas.caracteres}\n")
        for ((palabra, veces) in estadisticas.frecuencias) {
            salida.write("PALABRA $palabra $veces\n")
        }
        salida.write("FIN\n")
        salida.flush()
    } catch (e: FileNotFoundException) {
        // No existe, es una carpeta o no tenemos permiso: e.message lo explica
        System.err.println("No se puede leer el fichero: ${e.message}")
        exitProcess(2)
    } catch (e: Exception) {
        System.err.println("Error analizando ${fichero.path}: ${e.message}")
        exitProcess(3)
    }
}
