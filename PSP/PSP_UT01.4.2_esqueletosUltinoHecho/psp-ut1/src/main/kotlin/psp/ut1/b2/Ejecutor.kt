package psp.ut1.b2

import psp.ut1.util.comandoShell
import psp.ut1.util.esWindows
import java.io.File
import java.util.concurrent.TimeUnit

// B2.1 · Un ejecutor reutilizable. Completa los huecos marcados con TODO.

/** Lo que devuelve [ejecutar]. [codigo] vale -1 si el proceso se destruyó por timeout. */
data class Resultado(
    val codigo: Int,
    val salida: String,
    val errores: String,
    val timeout: Boolean
)

/**
 * Ejecuta [comando], espera como mucho [timeoutSeg] segundos y devuelve su resultado.
 *
 * La salida y los errores del hijo van a dos ficheros temporales. Así el hijo nunca se
 * bloquea por tener lleno el buffer de la tubería (apuntes 8.5) y podemos esperarle con
 * un tiempo máximo. Cuando el hijo termina, leemos los dos ficheros.
 */
fun ejecutar(comando: List<String>, timeoutSeg: Long = 10): Resultado {
    val ficheroSalida = File.createTempFile("salida", ".txt")
    val ficheroErrores = File.createTempFile("errores", ".txt")

    // TODO B2.1 · Paso 1 · Lanza el proceso.
    //   Crea un ProcessBuilder con «comando», manda su stdout a «ficheroSalida»
    //   (redirectOutput) y su stderr a «ficheroErrores» (redirectError). Después, start().
    val proceso: Process = ProcessBuilder(comando)
        .redirectOutput(ficheroSalida)
        .redirectError(ficheroErrores)
        .start()
    // TODO B2.1 · Paso 2 · Espera a que termine, como mucho «timeoutSeg» segundos.
    //   waitFor(tiempo, TimeUnit.SECONDS) devuelve true si terminó y false si no le dio tiempo.

    val terminado: Boolean =proceso.waitFor(timeoutSeg, TimeUnit.SECONDS)

    var codigo = -1
    if (terminado) {
        // TODO B2.1 · Paso 3 · Ha terminado: guarda en «codigo» su código de salida (exitValue).
        codigo = proceso.exitValue()
    } else {
        // TODO B2.1 · Paso 4 · No ha terminado a tiempo: termínalo.
        //   Primero con destroy() y espera 2 s. Si sigue vivo, destroyForcibly().
        //   Al final, waitFor() para asegurarte de que ya no existe.
        proceso.destroy()
        if(!proceso.waitFor(2, TimeUnit.SECONDS)){
            proceso.destroyForcibly()
        }
        proceso.waitFor()
    }

    // Ya hecho: leer los dos ficheros y borrarlos
    val resultado = Resultado(codigo, ficheroSalida.readText(), ficheroErrores.readText(), !terminado)
    ficheroSalida.delete()
    ficheroErrores.delete()
    return resultado
}

/** Ya hecho: muestra un resultado de forma legible. */
fun mostrar(titulo: String, r: Resultado) {
    println("== $titulo")
    println("   código: ${r.codigo}   timeout: ${r.timeout}")
    println("   salida: ${r.salida.trim()}")
    println("   errores: ${r.errores.trim()}")
}

fun main() {
    mostrar("Comando correcto", ejecutar(comandoShell("echo hola")))

    var falla = "ls /no_existe"
    if (esWindows) falla = "dir C:\\no_existe"
    mostrar("Comando que falla", ejecutar(comandoShell(falla)))

    var lento = listOf("sleep", "30")
    if (esWindows) lento = listOf("ping", "-n", "30", "127.0.0.1")
    mostrar("Comando lento (timeout 3 s)", ejecutar(lento, 3))
}
