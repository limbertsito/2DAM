package com.iesvb.psp.ut2.b1

import com.iesvb.psp.ut2.log
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
        // 1. Prepara la masa: duerme 300 ms                    (TIMED_WAITING)
        // 2. Espera el horno: synchronized(horno) { }           (BLOCKED si main lo tiene)
        // 3. Hornea: un bucle largo que sume, por ejemplo, 1 000 millones de i % 7  (RUNNABLE)
        // 4. Guarda la suma en resultado
    }
    val ayudante = thread(start = false, name = "ayudante") {
        // Espera a que termine el cocinero (join)               (WAITING)
    }

    // Consulta cada estado con log("…${cocinero.state}") en el momento adecuado:
    //   1. Antes de arrancarlo                                  -> NEW
    //   2. Arranca cocinero y ayudante; duerme 100 ms y consulta -> TIMED_WAITING
    //   3. Consulta el estado del ayudante                      -> WAITING
    //   4. Coge el horno con synchronized(horno) { … }; dentro, duerme 400 ms
    //      (el cocinero ya ha despertado y lo espera) y consulta -> BLOCKED
    //   5. Ya fuera del horno, duerme 20 ms y consulta          -> RUNNABLE
    //   6. Espera a los dos hilos (join) y consulta             -> TERMINATED
}
