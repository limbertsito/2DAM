package psp.ut1.proyecto

// B5 · PROPORCIONADO: no hace falta tocarlo.
// Convierte las líneas que el Trabajador escribe por stdout en unas Estadisticas.

/**
 * Interpreta la salida de un trabajador (ver el protocolo en Trabajador.kt).
 * Devuelve null si la salida no respeta el protocolo (falta FIN, una línea rara...).
 */
fun interpretarSalida(lineas: List<String>): Estadisticas? {
    var numLineas = -1
    var palabras = -1
    var caracteres = -1L
    val frecuencias = mutableMapOf<String, Int>()
    var fin = false
    // «?: return null»: si el número no es válido, la salida no respeta el protocolo
    for (linea in lineas) {
        val partes = linea.trim().split(" ")
        if (partes[0] == "FICHERO" || partes[0] == "") continue
        if (fin) return null                                   // nada después de FIN
        if (partes[0] == "LINEAS" && partes.size == 2) {
            numLineas = partes[1].toIntOrNull() ?: return null
        } else if (partes[0] == "PALABRAS" && partes.size == 2) {
            palabras = partes[1].toIntOrNull() ?: return null
        } else if (partes[0] == "CARACTERES" && partes.size == 2) {
            caracteres = partes[1].toLongOrNull() ?: return null
        } else if (partes[0] == "PALABRA" && partes.size == 3) {
            frecuencias[partes[1]] = partes[2].toIntOrNull() ?: return null
        } else if (partes[0] == "FIN" && partes.size == 1) {
            fin = true
        } else {
            return null
        }
    }
    if (!fin || numLineas < 0 || palabras < 0 || caracteres < 0) return null
    return Estadisticas(numLineas, palabras, caracteres, frecuencias)
}
