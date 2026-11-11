package com.iesvb.psp.ut2.b4

import com.iesvb.psp.ut2.log
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread
import kotlin.random.Random

// Empaquetado.kt · B4.1: la web produce 30 pedidos y tres empaquetadores los consumen
// (código de partida: completa lo que indican los comentarios)
class Pedido(val id: Int, val unidades: Int)

val FIN = Pedido(-1, 0)                             // píldora venenosa: «no hay más pedidos»

fun main() {
    val cola = ArrayBlockingQueue<Pedido>(5)        // como mucho 5 pedidos esperando
    val unidadesEmpaquetadas = AtomicInteger()      // la suman los tres empaquetadores
    var unidadesRecibidas = 0                       // solo la escribe la web

    val empaquetadores = ArrayList<Thread>()
    for (n in 1..3) {
        empaquetadores.add(thread(name = "empaquetador-$n") {
            var preparados = 0
            // 1. Saca un pedido con cola.take() (espera si la cola está vacía)
            // 2. Mientras no sea la píldora (pedido.id != FIN.id):
            //      - tarda 10 ms por unidad: Thread.sleep(pedido.unidades * 10L)
            //      - suma sus unidades con unidadesEmpaquetadas.addAndGet(…) y 1 a preparados
            //      - saca el siguiente pedido
            log("$preparados pedidos preparados")
        })
    }
    val web = thread(name = "web") {
        // 1. Para id de 1 a 30: crea Pedido(id, Random.nextInt(1, 6)), suma sus unidades a
        //    unidadesRecibidas y mételo con cola.put(pedido) (espera si la cola está llena)
        // 2. Mete una píldora FIN por cada empaquetador (3)
        log("30 pedidos recibidos")
    }
    // Espera a la web y a los tres empaquetadores (join)

    log("unidades recibidas $unidadesRecibidas, empaquetadas ${unidadesEmpaquetadas.get()}")
}
