package com.example.practicaexamejetpackcifrado

// Enum para identificar el resultado de la comprobación del código
enum class DestinoCanal {
    ALFA,
    BRAVO,
    INVALIDO
}

// 1. Determina a qué canal corresponde el PIN introducido
fun validarCodigo(codigo: String): DestinoCanal {
    return when (codigo) {
        CODIGO_ALFA -> DestinoCanal.ALFA
        CODIGO_BRAVO -> DestinoCanal.BRAVO
        else -> DestinoCanal.INVALIDO
    }
}

// 2. Transforma la longitud del código en asteriscos y guiones (ej. "**--")
fun obtenerIndicadorVisual(codigo: String): String {
    val asteriscos = "*".repeat(codigo.length)
    val guiones = "-".repeat(LONGITUD_PIN - codigo.length)
    return asteriscos + guiones
}

// 3. Cifrado Unicode +1 para el Canal BRAVO (Requisito 8)
fun cifrarTextoUnicode(texto: String): String {
    return texto.map { char -> (char.code + 1).toChar() }.joinToString("")
}