package psp.ut1.b4

import psp.ut1.util.procesoKotlin

// B4.2 · Tareas con dependencias. Completa los huecos marcados con TODO.
//
//             ┌──► B (3 s) ──┐
//     A (2 s) ┤              ├──► D (1 s)
//             └──► C (2 s) ──┘
//
// B y C empiezan a la vez cuando A termina bien; D, solo cuando B y C terminan bien.

/** Ya hecho: una tarea es un Dormilon que tarda [segundos] y termina con [codigo]. */
fun tarea(nombre: String, segundos: Int, codigo: Int = 0): ProcessBuilder {
    return procesoKotlin("psp.ut1.hijos.DormilonKt", nombre, segundos.toString(), codigo.toString())
        .inheritIO()                                // sus mensajes, en nuestra consola
}

/**
 * Ejecuta el grafo. [codigoDeC] es el código con que termina C (0 = bien).
 * Devuelve una línea por tarea: "A ok", "C falla (5)", "D cancelada"...
 */
fun ejecutarGrafo(codigoDeC: Int): List<String> {
    val informe = mutableListOf<String>()

    // TODO B4.2 · Paso 1 · Lanza A, espera a que termine (waitFor) y guarda su código.
    //   Si no es 0: añade "A falla (<código>)", "B cancelada", "C cancelada" y "D cancelada"
    //   y devuelve el informe. Si es 0, añade "A ok".

        val procesoA=tarea("A",2).start()//nombre y segubndis que tarda en finalizar
        val codigoA=procesoA.waitFor() //caundo termina nos dara su codigo de ejecucion

        if (codigoA!=0){
            informe.add("A falla ($codigoA)")
            informe.add("B cancelada")
            informe.add("C cancelada")
            informe.add("D cancelada")
            return informe
        }else{
            informe.add("A ok")
        }

    // TODO B4.2 · Paso 2 · Lanza B (3 s) y C (2 s, con «codigoDeC») A LA VEZ: primero los dos
    //   start() y después los dos waitFor(). Añade "B ok" o "B falla (<código>)", y lo mismo con C.
    val procesoB = tarea("B", 3).start()
    val procesoC = tarea("C", 2, codigoDeC).start()
    val codigoB: Int = procesoB.waitFor()
    val codigoC: Int = procesoC.waitFor()

    if (codigoB == 0) {
        informe.add("B ok")
    } else {
        informe.add("B falla ($codigoB)")
    }

    if (codigoC == 0) {
        informe.add("C ok")
    } else {
        informe.add("C falla ($codigoC)")
    }
    // TODO B4.2 · Paso 3 · Si B y C han terminado bien, lanza D, espera y añade "D ok"
    //   (o "D falla (<código>)"). Si no, añade "D cancelada".

    if(codigoB == 0 && codigoC == 0){
        val procesoD = tarea("D", 1).start()
        val codigoD = procesoD.waitFor()
        if (codigoD == 0){
            informe.add("D ok")
        }else{
            informe.add("D falla ($codigoD)")
        }
    }else{
        informe.add("D cancelada")
    }

    return informe
}

/** Ya hecho: los dos escenarios, con el tiempo que tarda cada uno. */
fun main() {
    for (codigoDeC in listOf(0, 5)) {
        println("=== Escenario: C termina con código $codigoDeC")
        val inicio = System.currentTimeMillis()
        val informe = ejecutarGrafo(codigoDeC)
        val segundos = (System.currentTimeMillis() - inicio) / 1000.0
        println("Resultado: $informe · %.1f s".format(segundos))
    }
}
