package com.iesvb.psp.ut2.b5

import com.iesvb.psp.ut2.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

// CanalEmpaquetado.kt · B5.3: productor-consumidor con Channel y contador con Mutex
// (código de partida: completa lo que indican los comentarios)
val mutex = Mutex()
var unidades = 0

fun main() {
    runBlocking {
        val canal = Channel<Int>(3)                 // cada elemento: unidades de un pedido
        launch {
            // La web: para id de 1 a 10, envía id % 4 + 1 unidades con canal.send(…)
            // y escribe log("web: pedido $id enviado"). Al terminar, cierra el canal: canal.close()
        }
        for (n in 1..2) {
            launch(Dispatchers.Default) {
                // Empaquetador n: recorre el canal con for (u in canal) y, para cada pedido:
                //   1. delay(u * 20L)
                //   2. suma u a unidades dentro de mutex.withLock { … }
                //   3. log("empaquetador $n: $u unidades")
            }
        }
    }
    log("unidades empaquetadas: $unidades (esperado 25)")
}
