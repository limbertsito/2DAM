package psp.ut1.proyecto

import psp.ut1.util.procesoKotlin
import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

// B5 · Orquestador. Completa los huecos marcados con TODO.
// Lanza un proceso Trabajador por cada fichero .txt de una carpeta, los espera con un tiempo
// máximo, interpreta lo que devuelven y muestra un informe.

/** Retardo que se pone a los ficheros cuyo nombre empieza por «lento» (para probar el timeout). */
const val RETARDO_LENTO_MS = "8000"

/** Lo que ha pasado con un fichero: estado "OK", "FALLO (2)", "TIMEOUT"... y su error, si lo hay. */
data class ResultadoFichero(
    val fichero: String,
    val estado: String,
    val datos: Estadisticas?,
    val error: String = ""
)

/**
 * Lanza un Trabajador para [fichero]. Su stdout va a «<carpeta>/<nombre>.out» y su stderr
 * a «<carpeta>/<nombre>.err»: así nunca se bloquea por tener lleno el buffer (apuntes 8.5).
 */
fun lanzarTrabajador(fichero: File, carpeta: File): Process {
    // TODO B5 · Paso 1 · Prepara procesoKotlin("psp.ut1.proyecto.TrabajadorKt", fichero.path).
    //   - Si el nombre del fichero empieza por "lento", añade a su entorno la variable
    //     TRABAJADOR_RETARDO_MS con el valor RETARDO_LENTO_MS:  pb.environment()[nombre] = valor
    //   - redirectOutput a File(carpeta, fichero.name + ".out")
    //   - redirectError  a File(carpeta, fichero.name + ".err")
    //   Lánzalo con start() y devuelve el Process.
    return TODO("B5 · paso 1: lanzar un trabajador")
}

/** Espera a [trabajador] como mucho [timeoutSeg] s. Devuelve su código, o null si hubo timeout. */
fun esperarTrabajador(trabajador: Process, timeoutSeg: Long): Int? {
    // TODO B5 · Paso 2 · Como en tu Ejecutor (B2.1): waitFor con tiempo. Si termina, devuelve
    //   exitValue(). Si no, destroy(), espera 2 s, destroyForcibly() si sigue vivo, waitFor()
    //   y devuelve null.
    return TODO("B5 · paso 2: esperar con timeout")
}

/** Ya hecho: traduce el código de un trabajador (null = timeout) y sus ficheros en un resultado. */
fun crearResultado(fichero: File, carpeta: File, codigo: Int?): ResultadoFichero {
    if (codigo == null) return ResultadoFichero(fichero.name, "TIMEOUT", null)
    if (codigo != 0) {
        val error = File(carpeta, fichero.name + ".err").readText().trim()
        return ResultadoFichero(fichero.name, "FALLO ($codigo)", null, error)
    }
    val datos = interpretarSalida(File(carpeta, fichero.name + ".out").readLines())
    if (datos == null) return ResultadoFichero(fichero.name, "SALIDA MAL FORMADA", null)
    return ResultadoFichero(fichero.name, "OK", datos)
}

/** Todos los trabajadores A LA VEZ: primero se lanzan todos y después se espera a cada uno. */
fun analizarEnParalelo(ficheros: List<File>, carpeta: File, timeoutSeg: Long): List<ResultadoFichero> {
    val resultados = mutableListOf<ResultadoFichero>()
    // TODO B5 · Paso 3 · Lanza un trabajador por fichero (lanzarTrabajador) y guarda los Process
    //   en una lista. Después, recorre la lista: espera a cada uno (esperarTrabajador) y añade
    //   crearResultado(fichero, carpeta, codigo) a «resultados».
    TODO("B5 · paso 3: en paralelo")
    return resultados
}

/** Los trabajadores DE UNO EN UNO: se lanza uno, se espera, y después el siguiente. */
fun analizarEnSecuencia(ficheros: List<File>, carpeta: File, timeoutSeg: Long): List<ResultadoFichero> {
    val resultados = mutableListOf<ResultadoFichero>()
    // TODO B5 · Paso 4 · Por cada fichero: lanzarTrabajador, esperarTrabajador y añade
    //   crearResultado(...) a «resultados».
    TODO("B5 · paso 4: en secuencia")
    return resultados
}

/** Ya hecho: la tabla por fichero, los totales, las 5 palabras más frecuentes y los errores. */
fun mostrarInforme(resultados: List<ResultadoFichero>) {
    val formato = "%-14s %8s %9s %11s  %s"
    println(formato.format("FICHERO", "LÍNEAS", "PALABRAS", "CARACTERES", "ESTADO"))
    var lineas = 0L
    var palabras = 0L
    var caracteres = 0L
    val frecuencias = mutableListOf<Map<String, Int>>()
    var correctos = 0
    for (r in resultados) {
        val d = r.datos
        if (d == null) {
            println(formato.format(r.fichero, "-", "-", "-", r.estado))
        } else {
            println(formato.format(r.fichero, d.lineas, d.palabras, d.caracteres, r.estado))
            lineas += d.lineas
            palabras += d.palabras
            caracteres += d.caracteres
            frecuencias.add(d.frecuencias)
            correctos++
        }
    }
    println(formato.format("TOTAL", lineas, palabras, caracteres, "$correctos de ${resultados.size} OK"))
    var top = ""
    for ((palabra, veces) in topPalabras(sumarFrecuencias(frecuencias), 5)) {
        top += "$palabra ($veces) "
    }
    println("Las 5 más frecuentes: ${top.trim()}")
    for (r in resultados) {
        if (r.error != "") println("Error en ${r.fichero}: ${r.error}")
    }
}

/** Ya hecho: los ficheros .txt de [carpeta] por orden alfabético (vacía si no hay ninguno). */
fun buscarTxt(carpeta: File): List<File> {
    val contenido = carpeta.listFiles() ?: return listOf()     // null: la carpeta no existe
    val txt = mutableListOf<File>()
    for (f in contenido) {
        // No se comprueba si es un fichero a propósito: así «ilegible.txt» (una carpeta)
        // llega al trabajador y se ve cómo falla.
        if (f.name.endsWith(".txt")) txt.add(f)
    }
    return txt.sortedBy { it.name }
}

/** Uso: Orquestador <carpeta> [--timeout S] [--secuencial] */
fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Uso: Orquestador <carpeta> [--timeout S] [--secuencial]")
        exitProcess(1)
    }
    val ficheros = buscarTxt(File(args[0]))
    if (ficheros.isEmpty()) {
        println("No hay ficheros .txt en ${args[0]} (o la carpeta no existe)")
        exitProcess(2)
    }
    var timeout = 5L
    val i = args.indexOf("--timeout")
    if (i >= 0 && i + 1 < args.size) timeout = args[i + 1].toLong()
    val secuencial = args.contains("--secuencial")

    val salidas = File("salidas")                    // aquí quedan los .out y .err
    salidas.mkdirs()
    var modo = "en paralelo"
    if (secuencial) modo = "en secuencia"
    println("Analizando ${ficheros.size} ficheros de ${args[0]} ($modo, timeout $timeout s)")

    val inicio = System.currentTimeMillis()
    val resultados: List<ResultadoFichero>
    if (secuencial) {
        resultados = analizarEnSecuencia(ficheros, salidas, timeout)
    } else {
        resultados = analizarEnParalelo(ficheros, salidas, timeout)
    }
    val segundos = (System.currentTimeMillis() - inicio) / 1000.0
    mostrarInforme(resultados)
    println("Tiempo total: %.2f s".format(segundos))

    for (r in resultados) {
        if (r.estado != "OK") exitProcess(1)         // algún fichero ha fallado
    }
}
