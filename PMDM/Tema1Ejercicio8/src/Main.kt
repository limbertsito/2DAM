const val OPCION_MIN = 1
const val OPCION_MAX = 6
const val PRECIO_MINIMO_FILTRO = 300.0
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val reservas = mutableListOf<Reserva>()
    var opcion = 0

    println(" --- GESTIÓN DE RESERVAS HOTEL --- ")

    while (opcion != OPCION_MAX) {
        mostrarMenu()
        print("Seleecciona una opción: ")

        val input = readln().trim()

        try {
            opcion = input.toInt()

            if (opcion !in OPCION_MIN..OPCION_MAX) {
                println("Error: La opción debe estar entre el $OPCION_MIN y el $OPCION_MAX.")
            } else {
                when (opcion) {
                    1 -> anadirReserva(reservas)
                    2 -> mostrarReservas(reservas)
                    3 -> buscarReservaPorCliente(reservas)
                    4 -> mostrarReservasCaras(reservas)
                    5 -> duplicarReserva(reservas)
                    6 -> println("Saliendo del programaa")
                }
            }

        } catch (e: NumberFormatException) {
            println("Error: Entrada no válida or favor, introduce un número entero: ")
        }
    }
}

fun mostrarMenu() {
    println("\n------------------MENU------------------")
    println("1. Nueva resserva")
    println("2. Mostrar reservas")
    println("3. Buscar reserva por cliente")
    println("4. Mostrar reservas superiores a $PRECIO_MINIMO_FILTRO €")
    println("5. Dupliscar una reserva (copy)")
    println("6. Salir")
    println("--------------------------------------------------")
    //Return readln().toIntOrNull()?: 0

}

fun anadirReserva(reservas: MutableList<Reserva>) {
    print("Nombre del clientee: ")
    val cliente = readln().trim()

    val habitacion = pedirEntero("Número de habitación: ")
    val noches = pedirEntero("Número de noches: ")
    val precioNoche = pedirDouble("Precio por noche: ")

    val nueva = Reserva(cliente, habitacion, noches, precioNoche)
    reservas.add(nueva)
    println("Reserva añadida con exito.")
}

fun mostrarReservas(reservas: MutableList<Reserva>) {
    if (reservas.isEmpty()) {
        println("No hayt reservas registradas.")
    } else {
        println("\nListado de reservas  :")
        for ((index, r) in reservas.withIndex()) {
            println("${index + 1}. Cliente: ${r.cliente} | Habitación: ${r.habitacion} | Noches: ${r.noches} | Total: ${r.calcularTotal()} €")
        }
    }
}

fun buscarReservaPorCliente(reservas: MutableList<Reserva>) {
    print("Introduce el nombre del cliete a buscar: ")
    val busqueda = readln().trim()
    val encontrada = reservas.firstOrNull { it.cliente.equals(busqueda, ignoreCase = true) }

    if (encontrada != null) {
        println("Reserva encontrada: Habitación ${encontrada.habitacion}, Noches: ${encontrada.noches}, Total: ${encontrada.calcularTotal()} €")
    } else {
        println("Error: No existe reserva para el cliente '$busqueda'.")
    }
}

fun mostrarReservasCaras(reservas: MutableList<Reserva>) {
    val caras = reservas.filter { it.calcularTotal() > PRECIO_MINIMO_FILTRO }
    if (caras.isEmpty()) {
        println("No hay reservas que superen los $PRECIO_MINIMO_FILTRO €.")
    } else {
        println("\nReservas de más de $PRECIO_MINIMO_FILTRO €:")
        caras.forEach { println("Cliente: ${it.cliente} -> Total: ${it.calcularTotal()} €") }
    }
}
fun duplicarReserva(reservas: MutableList<Reserva>) {
    if (reservas.isEmpty()) {
        println("No hay reservas para duplicar.")
        return
    }

    mostrarReservas(reservas)
    val posicion = pedirEntero("Introduce el número de la reserva a duplicar: ") - 1

    if (posicion !in reservas.indices) {
        println("Error: La reserva seleccionada no existe.")
    } else {
        val original = reservas[posicion]
        val nuevaHabitacion = pedirEntero("Introduce el nuevo número de habitación para la copia: ")

        val copia = original.copy(habitacion = nuevaHabitacion)
        reservas.add(copia)
        println("Reserva duplicada con éxito.")
    }
}

fun pedirEntero(mensaje: String): Int {

    //DO WHILE SIN TRY CATCH MIRAR
    while (true) {
        print(mensaje)
        val entrada = readln().trim()
        try {
            val valor = entrada.toInt()
            if (valor > 0) return valor
            println("Error: El número debe ser mayor a cero.")
        } catch (e: NumberFormatException) {
            println("Error: Entrada no válida. Por favor, introduce un número entero.")
        }
    }
}

fun pedirDouble(mensaje: String): Double {
    while (true) {
        print(mensaje)
        val entrada = readln().trim()
        try {
            val valor = entrada.toDouble()
            if (valor > 0.0) return valor
            println("Error: El valor debe ser mayor a cero.")
        } catch (e: NumberFormatException) {
            println("Error: Entrada no válida. Por favor, introduce un número.")
        }
    }
}