package com.iesvb.psp.ut2.b3

import com.iesvb.psp.ut2.log
import kotlin.concurrent.thread

// Reservas.kt · B3.1: dos clientes reservan los mismos productos en orden distinto
// Para reservar un pack se bloquea primero un producto y después el otro.
class Producto(val nombre: String, var stock: Int)

fun reservarPack(primero: Producto, segundo: Producto) {
    synchronized(primero) {
        log("tengo ${primero.nombre}, quiero ${segundo.nombre}")
        Thread.sleep(100)
        synchronized(segundo) {
            primero.stock--
            segundo.stock--
            log("pack reservado")
        }
    }
}

fun main() {
    val teclado = Producto("teclado", 10)
    val raton = Producto("raton", 10)
    val ana = thread(name = "cliente-ana") { reservarPack(teclado, raton) }
    val luis = thread(name = "cliente-luis") { reservarPack(raton, teclado) }
    ana.join(2_000)
    luis.join(2_000)
    if (ana.isAlive && luis.isAlive) log("los dos clientes siguen esperando: interbloqueo")
    // Con -Desperar el proceso sigue vivo para poder hacer el volcado de hilos (B3.2)
    if (System.getProperty("esperar") != null) Thread.sleep(Long.MAX_VALUE)
    Runtime.getRuntime().halt(0)
}
