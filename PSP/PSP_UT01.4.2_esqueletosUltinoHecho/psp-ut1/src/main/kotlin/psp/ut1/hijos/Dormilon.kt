package psp.ut1.hijos

import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

// PROPORCIONADO: proceso hijo de prueba. No hace falta tocarlo.

/**
 * Simula una tarea lenta: escribe una línea, duerme y termina con el código indicado.
 * Uso: Dormilon <nombre> <segundos> [codigoSalida]
 */
fun main(args: Array<String>) {
    val nombre = args.getOrElse(0) { "tarea" }
    val segundos = args.getOrNull(1)?.toLongOrNull() ?: 1
    val codigo = args.getOrNull(2)?.toIntOrNull() ?: 0
    println("[$nombre] empiezo (PID ${ProcessHandle.current().pid()}), tardaré $segundos s")
    TimeUnit.SECONDS.sleep(segundos)                 // el proceso se queda parado esos segundos
    println("[$nombre] termino con código $codigo")
    exitProcess(codigo)                              // código de salida que verá el padre
}
