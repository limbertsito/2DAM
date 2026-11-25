//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    val salir=false
    var precio=0
    var ciudad=""
    do {
        println("VENTA BILLETES")
        println("1.Madrid")
        println("2.valladolid")
        println("3.Zamora")
        println("4.Ávila")
        println("5.salir")
        println("Seleccione una opción")
        val opcion=readln().toInt()
       when (opcion) {
           1-> {precio =18
            ciudad="Madrid"
           }
           2->{precio=14
            ciudad="Valladolid"
           }
           3->{precio=12
            ciudad="Zamora"
           }
           4->{precio=10
           ciudad="Ávila"
           }
           else->println("El numero no está dentro de las opciones")
       }

        println("Introduce el número de ")
        var numViajero= readln().toInt()
       while(numViajero !in 1..9) {
           println("Ha intorudcido un numero fuera de rango vuelve a introucirlo")
           numViajero=readln().toInt()
       }
        if(numViajero in 1..10){
            println("RESUMEN DE LA RESERVA ")
            println("Destino :"+ciudad)
            println("número de vaijeros: $numViajero")
            println("precio por persona: $precio")
            println("precio total: ${precio*numViajero}€")

            for (i in 1..numViajero) {
                print("Billete nº$i+1 ")
            }

        }
    }while(opcion!=5)



}