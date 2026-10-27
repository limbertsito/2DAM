package psp.ut1.hijos

import kotlin.system.exitProcess

// PROPORCIONADO: proceso hijo del B3.2. No hace falta tocarlo.

/**
 * Procesa una petición del protocolo y devuelve la respuesta (una línea).
 *   SET <clave> <valor>  -> OK               (el valor puede tener espacios)
 *   GET <clave>          -> VALOR <valor> | NO_EXISTE
 *   DEL <clave>          -> OK | NO_EXISTE
 *   KEYS                 -> CLAVES <n> <c1> <c2> ...   (orden alfabético)
 *   otra cosa            -> ERROR <motivo>
 * QUIT se trata en main, porque además termina el proceso.
 */
fun procesar(datos: MutableMap<String, String>, linea: String): String {
    val partes = linea.trim().split(" ", limit = 3)   // como mucho 3: orden, clave, valor
    val orden = partes[0].uppercase()
    if (orden == "") return "ERROR petición vacía"

    if (orden == "SET") {
        if (partes.size < 3) return "ERROR uso: SET <clave> <valor>"
        datos[partes[1]] = partes[2]
        return "OK"
    }
    if (orden == "GET") {
        if (partes.size < 2) return "ERROR uso: GET <clave>"
        val valor = datos[partes[1]]
        if (valor == null) return "NO_EXISTE"
        return "VALOR $valor"
    }
    if (orden == "DEL") {
        if (partes.size < 2) return "ERROR uso: DEL <clave>"
        if (datos.remove(partes[1]) == null) return "NO_EXISTE"
        return "OK"
    }
    if (orden == "KEYS") {
        var respuesta = "CLAVES ${datos.size}"
        for (clave in datos.keys.sorted()) {
            respuesta += " $clave"
        }
        return respuesta
    }
    return "ERROR orden desconocida: $orden"
}

/**
 * Proceso hijo: almacén clave-valor en memoria. Lee peticiones por stdin, responde por stdout
 * (una línea por petición) y registra cada petición por stderr, para no romper el protocolo.
 */
fun main() {
    val datos = mutableMapOf<String, String>()
    System.err.println("[kv] servidor iniciado, PID ${ProcessHandle.current().pid()}")
    while (true) {
        val linea = readlnOrNull() ?: break           // EOF: el padre se ha ido
        System.err.println("[kv] petición: $linea")
        if (linea.trim().uppercase() == "QUIT") {
            println("ADIOS")
            exitProcess(0)
        }
        println(procesar(datos, linea))
    }
    System.err.println("[kv] EOF recibido, termino")
}
