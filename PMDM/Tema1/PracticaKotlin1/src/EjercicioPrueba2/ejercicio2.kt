package EjercicioPrueba2


    fun main(){
        println("VENTA DE BILLETES ")
        var opcion  : Int = mostrarMenu()
        var precio : Int = when(opcion){
            1 ->18
            2 -> 14
            3 ->12
            4 -> 10
            5 -> return
            else ->-1
        }
        println("introduce el numero de viajeros")
    var numViajeros=readln().toIntOrNull()?:0
        while (numViajeros !in 1 ..10){
            println("Debes introducir un numero de viajeros entre 1 y 10")
            numViajeros=readln().toIntOrNull()?:0
        }
        mostrarResumen(opcion, numViajeros,precio)
    }

    private fun mostrarResumen(opcion:Int, numViajeros:Int,precio: Int) {
        var ciudad :String =
        when(opcion){
            1 -> "Madrid"
            2 -> "Valladolid"
            3 -> "Zamora"
            4-> "Avila"
            else ->"Error"
        }
        println("Resumen de la reserva")
        println("Destino $ciudad")
        println("Numero de viajeros $numViajeros")
        println("Precio por Persona $precio€")
        println("Precio Total ${precio*numViajeros}€")
        println("Billetes generados: ")
        for(i in 1..numViajeros){
            println("Billene nº$i")
        }


    }

    private fun mostrarMenu() : Int {
        println("1.Madrid")
        println("2.Valladolid")
        println("3.Zamora")
        println("4.Avila")
        println("5.Salir")
        println("Selecciona una opcion: ")
        var opcion=readln().toIntOrNull()?:0
        while (opcion !in 1..5 ){
            println("Ese no es una entrada válida , vuelve a introducir: ")
            opcion=readln().toIntOrNull()?:0

        }
        return opcion
    }
