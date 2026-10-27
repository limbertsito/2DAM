package psp.ut1.hijos

import kotlin.system.exitProcess

/**
 * Proceso hijo que simula una tarea lenta.
 * Uso: Dormilon <nombre> <segundos> [codigoSalida]
 */
fun main(args: Array<String>) {
    val nombre = args.getOrElse(0) { "tarea" }
    val segundos = args.getOrNull(1)?.toLongOrNull() ?: 1
    val codigo = args.getOrNull(2)?.toIntOrNull() ?: 0
    println("[$nombre] empiezo (PID ${ProcessHandle.current().pid()}), tardaré $segundos s")
    Thread.sleep(segundos * 1000)
    println("[$nombre] termino con código $codigo")
    exitProcess(codigo)                              // código de salida que verá el padre
}
