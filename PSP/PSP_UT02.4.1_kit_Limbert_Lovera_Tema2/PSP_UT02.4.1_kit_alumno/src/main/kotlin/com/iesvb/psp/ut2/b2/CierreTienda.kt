package com.iesvb.psp.ut2.b2

import com.iesvb.psp.ut2.log
import kotlin.concurrent.thread

// CierreTienda.kt · B2.4: el cajero debe ver que la tienda cierra (código de partida)
class Tienda {
    var abierta = true
}

fun main() {
    val tienda = Tienda()
    val cajero = thread(name = "cajero", isDaemon = true) {
        var ventas = 0L
        while (tienda.abierta) ventas++
        log("cierro la caja tras $ventas ventas")
    }
    Thread.sleep(1_000)
    tienda.abierta = false
    log("he cerrado la tienda")
    cajero.join(2_000)
    if (cajero.isAlive) log("el cajero sigue trabajando: no ha visto el cambio")
    else log("el cajero ha cerrado a tiempo")
}
