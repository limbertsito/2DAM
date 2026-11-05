package com.iesvb.psp.ut2.b4

import com.iesvb.psp.ut2.log
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Semaphore
import kotlin.concurrent.thread
import kotlin.random.Random

// Etiquetas.kt · B4.3: la tienda abre cuando sus servicios están listos; solo hay 2 impresoras
// (código de partida: completa lo que indican los comentarios)
// Para comprobar el semáforo se cuentan las impresoras en uso (protegido por un cerrojo)
val registro = Any()
var enUso = 0
var maximoEnUso = 0

fun imprimir(impresoras: Semaphore, pedido: Int) {
    // 1. Pide una impresora: impresoras.acquire() (espera si las dos están ocupadas)
    // 2. try { … } finally { impresoras.release() }: dentro del try va todo lo de abajo
    synchronized(registro) {
        enUso++
        if (enUso > maximoEnUso) maximoEnUso = enUso
        log("imprimo la etiqueta del pedido $pedido ($enUso en uso)")
    }
    Thread.sleep(200)
    synchronized(registro) { enUso-- }
}

fun main() {
    // 1. Crea un CountDownLatch(3)
    for (servicio in arrayOf("pagos", "inventario", "envios")) {
        thread(name = servicio) {
            Thread.sleep(Random.nextLong(100, 400))
            log("listo")
            // 2. Avisa con countDown()
        }
    }
    // 3. Espera a los tres con await()
    log("todos los servicios listos: se abre la tienda")

    // 4. Crea un Semaphore(2): dos impresoras
    val pedidos = ArrayList<Thread>()
    // 5. Para p de 1 a 6, lanza un hilo "pedido-p" que llame a imprimir(impresoras, p) y guárdalo en pedidos
    for (h in pedidos) h.join()
    log("máximo de impresoras usadas a la vez: $maximoEnUso")
}
