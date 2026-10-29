package psp.ut1.b3

import psp.ut1.util.procesoKotlin
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File

// B3.2 · Conversación con un almacén clave-valor. Completa los huecos marcados con TODO.
// El hijo (hijos/ServidorKV.kt) ya está hecho: por cada línea que recibe por su stdin
// responde UNA línea por su stdout, y anota cada petición en su stderr.

/** Habla con un proceso ServidorKV. Su stderr (el registro) va al fichero [log]. */
class ClienteKV(log: File) {
    // TODO B3.2 · Paso 1 · Lanza procesoKotlin("psp.ut1.hijos.ServidorKVKt") con su stderr
    //   añadido al final del log: redirectError(ProcessBuilder.Redirect.appendTo(log)).
    private val hijo: Process = procesoKotlin("psp.ut1.hijos.ServidorKVKt")
        .redirectError(ProcessBuilder.Redirect.appendTo(log))
        .start()

    // Ya hecho: para escribir en el stdin del hijo y leer de su stdout
    private val haciaHijo: BufferedWriter = hijo.outputStream.bufferedWriter()
    private val desdeHijo: BufferedReader = hijo.inputStream.bufferedReader()

    /** Envía una petición y devuelve la línea de respuesta (null si el hijo ha terminado). */
    fun enviar(peticion: String): String? {
        // TODO B3.2 · Paso 2 · Escribe la petición, newLine() y flush() (sin flush el hijo no
        //   recibe nada: se queda en el buffer). Después lee UNA línea con readLine().
        haciaHijo.write(peticion)
        haciaHijo.newLine()
        haciaHijo.flush()


        return desdeHijo.readLine()
    }

    /** Cierra la conversación y devuelve el código de salida del hijo. */
    fun cerrar(): Int {
        // TODO B3.2 · Paso 3 · Cierra «haciaHijo» (el hijo recibe EOF) y espera al hijo (waitFor).
        haciaHijo.close()
        hijo.waitFor()
        return   hijo.exitValue()
    }
}

/** Ya hecho: lee órdenes del teclado y las reenvía al servidor hasta QUIT. */
fun main() {
    val cliente = ClienteKV(File("kv.log"))
    println("Órdenes: SET <clave> <valor> · GET <clave> · DEL <clave> · KEYS · QUIT")
    while (true) {
        print("> ")
        val orden = readlnOrNull() ?: "QUIT"        // fin del teclado (Ctrl+D / Ctrl+Z): QUIT
        val respuesta = cliente.enviar(orden)
        if (respuesta == null) {
            println("El servidor ha terminado.")
            break
        }
        println(respuesta)
        if (orden.trim().uppercase() == "QUIT") break
    }
    println("Código de salida del servidor: ${cliente.cerrar()}")
}
