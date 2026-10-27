const val IMPORTE_MIN=0.0 //Para la vaalidacxion de importes minimos
class CuentaBancaria (
    val titular: String,
    val numeroCuenta : String
){
    var saldo : Double = IMPORTE_MIN
        private set // para que se pueda ver deede fuera de la clase peor no modificar+


    //Mostraaremos los mensajes iniciales
    init{
        println("Cuenta $numeroCuenta creada correctamente para $titular.")
        println("Saldo inicial: $saldo €")
    }

    constructor(
        titular: String,
        numeroCuenta: String,
        saldoInicial: Double
    ) : this(
        titular,
        numeroCuenta
    ) {
        if(saldoInicial>=0){
            this.saldo = saldoInicial
            println("Generando cuentra para  $titular.")
        }else{
            println("No se pouede crear la cuent con saldo negativo")
        }



    }

    fun ingresar(cantidad: Double){
        if (cantidad<=IMPORTE_MIN){
            println("Error: No se puede ingresar una cantidad igual o inferior a ceroo")
        }
        else{
            saldo+=cantidad
            println("Ingreso realizado correctamente. Nuevo salddo: $saldo€")
        }
    }

    fun retirar(cantidad: Double) {
        if (cantidad <= IMPORTE_MIN) {
            println("Error: No se puede retirar una cantidad igual o inferior a cero.")
        } else if (cantidad > saldo) {
            println("Error: No se puede retirar más dinero del que existe en la cuenta.")
        } else {
            saldo -= cantidad
            println("Retirada realizada correctamente. Nuevo saldo: $saldo €")
        }
    }

    fun mostrarInformacion() {
        println("Titular: $titular , Número de cuenta: $numeroCuenta , Saldo: $saldo €")
    }
}