package psp.ut1.b2

import psp.ut1.util.procesoKotlin
import java.util.concurrent.TimeUnit

// B2.3 · El vigilante. Completa los huecos marcados con TODO.

/**
 * Lanza [pb], lo vigila cada segundo y, si pasa de [maximo] segundos, lo termina.
 * Devuelve cómo terminó: "normal", "destroy" o "destroyForcibly".
 */
fun vigilar(pb: ProcessBuilder, maximo: Int): String {
    // TODO B2.3 · Paso 1 · Lanza el proceso y escribe:
    //   [vigilante] vigilando PID <pid> (máximo <maximo> s)
    val proceso: Process = pb.start()
    println("[vigilante] vigilando PID <${proceso.pid()}> (máximo <$maximo> s")

    // TODO B2.3 · Paso 2 · Cada segundo, hasta llegar al máximo, espera como mucho 1 s
    //   (waitFor(1, TimeUnit.SECONDS)). Si ha terminado, escribe
    //   [vigilante] terminó solo con código <código>   y devuelve "normal".
    //   Si no, escribe   [vigilante] vivo <n> s   y sigue.
    for ( s in 1..maximo) {
        if (proceso.waitFor(1, TimeUnit.SECONDS)){
            println("[vigilante] terminó solo con codigo <${proceso.waitFor()}> ")
        }else{
            println("[Vigilante] vivo <$s>  s y sigue")
        }

    }

    // TODO B2.3 · Paso 3 · Se ha pasado del máximo. Escribe «[vigilante] límite: destroy()»,
    //   llama a destroy() y espera 3 s. Si ha terminado, escribe su código y devuelve "destroy".
    //   Si no, escribe «[vigilante] no responde: destroyForcibly()», llama a destroyForcibly(),
    //   espera con waitFor(), escribe su código y devuelve "destroyForcibly".
    print("[vigilante] límite: destroy()")
    proceso.destroy()
    if(proceso.waitFor(3, TimeUnit.SECONDS)){
        println("${proceso.pid()}")
        return "destroy"
    }else{
        println("[vigilante] no responde: destroyForcibly()")
        proceso.destroyForcibly()
        proceso.waitFor()
        println("${proceso.pid()}")
        return "destroyForcibly"
    }

}

/**
 * Uso:  Vigilante <segundosMax> <programa>un programa del sistema
 *       Vigilante <segundosMax> --kotlin <ClaseMain>un main del proyecto
 */
fun main(args: Array<String>) {
    if (args.size < 2 || args[0].toIntOrNull() == null) {
        println("Uso: Vigilante <segundosMax> <programa> [args...]")
        return
    }
    val pb: ProcessBuilder
    if (args[1] == "--kotlin") {
        // El * pasa los elementos del array como argumentos sueltos (procesoKotlin usa vararg)
        pb = procesoKotlin(args[2], *args.copyOfRange(3, args.size))
    } else {
        pb = ProcessBuilder(args.copyOfRange(1, args.size).toList())
    }
    pb.inheritIO()                                  // el hijo escribe en nuestra misma consola
    val fin = vigilar(pb, args[0].toInt())
    println("[vigilante] fin: $fin")
}
