package com.iesvb.psp.ut2.s5.b1.b2

import com.iesvb.psp.ut2.log
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask

// Facturacion.kt · B2.1: la facturación de tres tiendas, calculada en paralelo
// (código de partida: completa lo que indican los comentarios)
suspend fun facturarTienda(nombre:String, ventas:  DoubleArray): Double{
    log("$nombre sumo ${ventas.size} ventas")

    delay(500)
    var total = 0.0
    for (v in ventas){
        total+=v
    }
    //retornamos el double
    return total
}


fun main() {
    val tiendas = arrayOf(
        doubleArrayOf(19.95, 4.50, 32.00),
        doubleArrayOf(120.00, 15.25),
        doubleArrayOf(9.99, 9.99, 9.99, 9.99),
    )
    val inicio = System.currentTimeMillis()
    runBlocking {
        //lo equivaklente al future task
       val tareas = ArrayList<Deferred<Double>>()
       for (i in tiendas.indices){
           //lanza esta corrutiina y se que lo que estoy llamnado devulev un valor
           tareas.add(async { facturarTienda("tienda-${i+1}",tiendas[i]) })
       }
        var total = 0.0
        for (t in tareas) total +=t.await()
        val ms = System.currentTimeMillis() -inicio
        log("facturacion total : %.2f € em %d ms".format(total,ms))


    }

}
