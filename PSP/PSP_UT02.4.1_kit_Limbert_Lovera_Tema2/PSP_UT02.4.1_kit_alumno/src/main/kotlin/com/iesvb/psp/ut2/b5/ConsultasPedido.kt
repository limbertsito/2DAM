package com.iesvb.psp.ut2.b5

import com.iesvb.psp.ut2.log
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

// ConsultasPedido.kt · B5.2: stock, precio y envío de un pedido, seguidas y a la vez
// (código de partida: completa lo que indican los comentarios)
suspend fun consultarStock(): Int {
    delay(400)
    return 7
}

suspend fun consultarPrecio(): Int {
    delay(600)
    return 1995                                     // céntimos
}

suspend fun consultarEnvio(): Int {
    delay(300)
    return 2                                        // días
}

fun main() {
    runBlocking {
        var inicio = System.currentTimeMillis()
        val s = consultarStock()
        val p = consultarPrecio()
        val e = consultarEnvio()
        var ms = System.currentTimeMillis() - inicio
        log("seguidas: stock $s, precio $p, envío $e días en $ms ms")

        inicio = System.currentTimeMillis()
        // 1. Lanza las tres consultas a la vez: val stock = async { consultarStock() }, y lo mismo con precio y envío
        // 2. Recoge los tres resultados con await(): val s2 = stock.await()…
        // 3. Mide el tiempo y escribe: "a la vez: stock …, precio …, envío … días en … ms"
    }
}
