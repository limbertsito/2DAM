//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val MIN_PEDIR_NOTAS = 1;
    val MAX_PEDIR_NOTAS = 30;
    val MIN_NOTA = 0.0;
    val MAX_NOTA = 10.0;

    var numeroNotas: Int?
    var notasAlmacenadas = mutableListOf<Double>();
    var notaAlumno: Double?
    do {
        println("Dime cuantas notas vas a introducir: ")
        numeroNotas = readln().toIntOrNull() ?: 0
        if (numeroNotas in MIN_PEDIR_NOTAS..MAX_PEDIR_NOTAS) {
            break;
        } else {
            println("Error: Introduce un numero entre ($MIN_PEDIR_NOTAS y $MAX_PEDIR_NOTAS)")

        }
    } while (true)
    while (notasAlmacenadas.size < numeroNotas) {
        println("Dime la nota ${notasAlmacenadas.size + 1}: ")
        notaAlumno = readln().toDoubleOrNull() ?: -1.0
        if (notaAlumno in MIN_NOTA..MAX_NOTA) {
            notasAlmacenadas.add(notaAlumno)
        } else {
            println("Introduzce un numero entre $MIN_NOTA y $MAX_NOTA: ")
        }

    }
    println("---------- RESULTADOS ----------")
    println("Notas introducidas $notasAlmacenadas")
    println("Nota media: %.2f".format(calcularMedia(notasAlmacenadas)))
    //Usamos el elvis por si el valor en cuneto a escalabilida llega un null
    println("Nota mas alta: ${notasAlmacenadas.maxOrNull()?:0.0}")
    println("Notas mas baja: ${notasAlmacenadas.minOrNull()?:0.0}")
    println("Numero aprobados:  ${calcularNumeroAprobados(notasAlmacenadas)}")
    println("Numero suspensos:  ${calcularNumeroSuspensos(notasAlmacenadas)}")
    println("Notas aprobadas: ${numeroAprobados(notasAlmacenadas)}")
}

fun calcularMedia(notas: List<Double>): Double {
    return notas.sumByDouble { it } / notas.size
}

fun calcularNumeroAprobados(listaNotas: List<Double>): Int {
    val esAprobado = { aprobados : Double -> aprobados >= 5.0 }
    return listaNotas.filter(esAprobado).size
}
fun calcularNumeroSuspensos(listaNotas: List<Double>): Int {
    val esSuspenso = {suspensos : Double -> suspensos < 5.0}
    return listaNotas.filter(esSuspenso).size
}
fun numeroAprobados(listaNotas: List<Double>): List<Double> {
    val esAprobado = {aprobados : Double -> aprobados >= 5.0}
    return listaNotas.filter(esAprobado)
}
