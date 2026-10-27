package psp.ut1.b2

import kotlin.jvm.optionals.getOrNull

// B2.2 · Mini-ps. Completa los huecos marcados con TODO.
//
// Casi todo lo que da ProcessHandle es un Optional (puede que no haya dato):
//   opcional.isPresent  → true si hay valor
//   opcional.get()      → el valor (solo si isPresent)
//   opcional.orElse(x)  → el valor o, si no hay, x

/** Ya hecho: da formato de columna a los datos de un proceso. */
fun fila(pid: String, ppid: String, usuario: String, comando: String): String {
    val programa = comando.substringAfterLast('/').substringAfterLast('\\')   // sin la ruta
    return "%7s %7s  %-15s %s".format(pid, ppid, usuario.takeLast(15), programa)
}

val CABECERA = fila("PID", "PPID", "USUARIO", "COMANDO")

/** Una línea con el PID, el PPID, el usuario y el comando de [p]. */
fun describir(p: ProcessHandle): String {
    // TODO B2.2 · Paso 1 · Obtén los datos del proceso y devuelve fila(pid, ppid, usuario, comando).
    //   pid:     p.pid()
    //   ppid:    p.parent() es un Optional<ProcessHandle>: si hay padre, su pid(); si no, "?"
    //   usuario: p.info().user().orElse("?")
    //   comando: p.info().command().orElse("?")
        val pid=p.pid().toString()
    val ppid=if (p.parent() != null) p.pid().toString() else "?"
    val usuario=p.info().user().orElse("?")
    val comando=p.info().command().orElse("?")
        return fila(pid,ppid,usuario,comando)
}

/** Todos los procesos visibles, ordenados por PID. */
fun listar(): List<String> {
    val lineas = mutableListOf(CABECERA)
    // TODO B2.2 · Paso 2 · Recorre todos los procesos y añade a «lineas» describir(p) de cada uno.
    //   ProcessHandle.allProcesses() da todos; con .toList() los tienes en una lista.
    //   Ordénalos por PID antes del bucle: lista.sortedBy { it.pid() }
    val proc=ProcessHandle.allProcesses().toList().sortedBy { it.pid()}
    for (x in proc) {
        lineas.add(describir(x))
    }
    return lineas
}

/** El proceso [pid] y sus hijos directos. */
fun hijos(pid: Long): List<String> {
    val lineas = mutableListOf(CABECERA)
    // TODO B2.2 · Paso 3 · Busca el proceso con ProcessHandle.of(pid) (es un Optional).
    //   Si no existe, devuelve listOf("No existe el proceso $pid").
    //   Si existe, añade describir(proceso) y, después, describir(h) de cada hijo:
    //   proceso.children().toList()

    val opt=ProcessHandle.of(pid)
    if(!opt.isPresent){
        return listOf("No existe el proceso $pid")
    }else{
        val proceso=opt.get()
        lineas.add(describir(proceso))
        val listaHijos=proceso.children().toList()
        for (hijo in listaHijos) {
            lineas.add(describir(hijo))
        }
    }
    return lineas
}

/** Nuestro propio proceso, su padre, el padre de su padre… hasta el primero. */
fun rama(): List<String> {
    val lineas = mutableListOf(CABECERA)
    // TODO B2.2 · Paso 4 · Empieza en ProcessHandle.current() y, en un bucle while, añade
    //   describir(actual) y sube al padre con parent(). Para cuando no haya padre.

    var actual= ProcessHandle.current()
    lineas.add(describir(actual))
    while(actual.parent().isPresent){

            actual=actual.parent().get()
        lineas.add(describir(actual))

    }

    return lineas
}

/** Uso: MiniPs  |  MiniPs --hijos <pid>  |  MiniPs --yo */
fun main(args: Array<String>) {
    var lineas = listOf("Uso: MiniPs  |  MiniPs --hijos <pid>  |  MiniPs --yo")
    if (args.isEmpty()) {
        lineas = listar()
    } else if (args[0] == "--hijos" && args.size == 2 && args[1].toLongOrNull() != null) {
        lineas = hijos(args[1].toLong())
    } else if (args[0] == "--yo") {
        lineas = rama()
    }
    for (linea in lineas) println(linea)
}
