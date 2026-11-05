package com.iesvb.psp.ut2

// Log.kt · Escribe cada mensaje con el nombre del hilo que lo produce (apartado 9.1)
fun log(mensaje: String) = println("[${Thread.currentThread().name}] $mensaje")
