package EjercicioPrueba1

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
        println("Intorduce cuantos viajeros")
            var numV=readln().toIntOrNull()?:0
        println("Introduce el precio por billete: ")
            var precio=readln().toDoubleOrNull()?:0.0
        println("Introduce el nombre del pasajero principal ")
        var nombre=readln()
    println("Reservaa de $nombre")
    println("$numV viajeros")
    println("precio por persona $precio €")
    println("Precio Total ${precio*numV}€")
}