package psp.ut1.proyecto

// B5 · PROPORCIONADO: contar palabras es programación general, no de procesos.
// No hace falta tocarlo. Lo usan el Trabajador (contar) y el Orquestador (sumar).

/** Lo que cuenta un trabajador en un fichero. */
data class Estadisticas(
    val lineas: Int,
    val palabras: Int,
    val caracteres: Long,
    val frecuencias: Map<String, Int>      // veces que aparece cada palabra (en minúsculas)
)

/** Separa palabras: todo lo que no sea letra ni dígito. */
private val SEPARADORES = Regex("[^\\p{L}\\p{N}]+")

/** Cuenta líneas, palabras, caracteres y la frecuencia de cada palabra. */
fun analizar(lineas: Sequence<String>): Estadisticas {
    var numLineas = 0
    var palabras = 0
    var caracteres = 0L
    val frecuencias = mutableMapOf<String, Int>()
    for (linea in lineas) {
        numLineas++
        caracteres += linea.length
        for (palabra in linea.lowercase().split(SEPARADORES)) {
            if (palabra.isEmpty()) continue
            palabras++
            frecuencias[palabra] = frecuencias.getOrDefault(palabra, 0) + 1
        }
    }
    return Estadisticas(numLineas, palabras, caracteres, frecuencias)
}

/** Suma las frecuencias de varios ficheros. */
fun sumarFrecuencias(mapas: List<Map<String, Int>>): Map<String, Int> {
    val total = mutableMapOf<String, Int>()
    for (mapa in mapas) {
        for ((palabra, veces) in mapa) {
            total[palabra] = total.getOrDefault(palabra, 0) + veces
        }
    }
    return total
}

/** Las [k] palabras más frecuentes (a igualdad, por orden alfabético). */
fun topPalabras(frecuencias: Map<String, Int>, k: Int): List<Pair<String, Int>> {
    val orden = compareByDescending<Pair<String, Int>> { it.second }.thenBy { it.first }
    return frecuencias.toList().sortedWith(orden).take(k)
}
