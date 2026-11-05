package com.iesvb.psp.ut2.b1

import com.iesvb.psp.ut2.log
import java.lang.Thread.sleep
import kotlin.concurrent.thread
// EstadosCocinero.kt · B1.2: un cocinero pasa por los seis estados de un hilo
// (código de partida: completa lo que indican los comentarios)
// El horno es un cerrojo: con synchronized(horno) { … } solo un hilo puede estar dentro.
// Si otro hilo lo tiene, el que llega se queda BLOCKED hasta que quede libre (apuntes, 5.1)
val horno = Any()
// Guarda aquí el resultado del cálculo de hornear: si no se usa, el JIT puede quitar el bucle
@Volatile var resultado = 0L
fun main() {
// thread(start = false, …) crea el hilo pero NO lo arranca: así puedes ver el estado NEW
    val cocinero = thread(start = false, name = "cocinero") {

        var calor=0.0
// 1. Prepara la masa: duerme 300 ms
        sleep(300)
// 2. Espera el horno: synchronized(horno) { } tiene) (TIMED_WAITING) (BLOCKED si main lo
        synchronized(horno) {
            // 3. Hornea: un bucle largo que sume, por ejemplo, 1 000 millones de i % 7 (RUNNABLE) for (i in 1..1_000_000_000) calor += i % 7
            for(i in 1..1_000_000_000) {
                calor += i % 7
            }
            // 4. Guarda la suma en resultado resultado = calor
            resultado += calor.toLong()
        }



    }


    // Espera a que termine el cocinero (join)

    val ayudante = thread(start = false, name = "ayudante") {
        cocinero.join()

    }
    log("NEW ${cocinero.state}")

    // 2. ARRANCAR Y ESPERAR UN POCO
    cocinero.start()
    ayudante.start()
    Thread.sleep(100)
    // A los 100ms, el cocinero está en medio de su sleep(300)
    log("2. TIMED_WAITING: ${cocinero.state}")
    // 3. CONSULTAR AL AYUDANTE
    // El ayudante está clavado en el join() esperando al cocinero
    log("3. WAITING: ${ayudante.state}")
// 4. MAIN SECUESTRA EL HORNO
    synchronized(horno) {
        Thread.sleep(400) // Main retiene el horno 400ms
        // El cocinero despierta de preparar la masa, pero como main tiene el horno, se bloquea
        log("4. BLOCKED: ${cocinero.state}")
    }
    // 5. MAIN SUELTA EL HORNO Y ESPERA UN INSTANTE
    Thread.sleep(20)
    // El cocinero ya ha entrado al horno y está a tope calculando el bucle for
    log("5. RUNNABLE: ${cocinero.state}")

    // 6. FIN DE LA JORNADA
    cocinero.join()
    ayudante.join()
    log("6. TERMINATED: ${cocinero.state}")
}

       //(WAITING)
// Consulta cada estado con log("…${cocinero.state}") en el momento adecuado:
//   1. Antes de arrancarlo                                  -> NEW
//   2. Arranca cocinero y ayudante; duerme 100 ms y consulta -> TIMED_WAITING
//   3. Consulta el estado del ayudante                      -> WAITING
//   4. Coge el horno con synchronized(horno) { … }; dentro, duerme 400 ms
//
//    (el cocinero ya ha despertado y lo espera) y consulta -> BLOCKED
//   5. Ya fuera del horno, duerme 20 ms y consulta          -> RUNNABLE
//   6. Espera a los dos hilos (join) y consulta


