package psp.ut1.b2

import psp.ut1.util.procesoKotlin

/**
 * Comprueba que el entorno está bien montado: paquetes, classpath y lanzamiento de hijos.
 * Salida esperada:
 *   [prueba] empiezo (PID ...), tardaré 1 s
 *   [prueba] termino con código 0
 *   Código del hijo: 0
 */
fun main() {
    val hijo = procesoKotlin("psp.ut1.hijos.DormilonKt", "prueba", "1")
        .redirectErrorStream(true)
        .start()
    print(hijo.inputStream.bufferedReader().use { it.readText() })
    println("Código del hijo: ${hijo.waitFor()}")
}
