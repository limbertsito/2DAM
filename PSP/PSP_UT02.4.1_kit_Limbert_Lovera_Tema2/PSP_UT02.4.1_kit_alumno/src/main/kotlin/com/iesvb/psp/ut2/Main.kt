package com.iesvb.psp.ut2

// Main.kt · Comprueba que el proyecto funciona y muestra el entorno
fun main() {
    val nucleos = Runtime.getRuntime().availableProcessors()
    println("Proyecto de la UT2 listo")
    println("Java ${System.getProperty("java.version")} · Kotlin ${KotlinVersion.CURRENT}")
    println("Núcleos disponibles: $nucleos")
    println("Hilo actual: ${Thread.currentThread().name}")
}
