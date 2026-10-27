//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println("Creadno cuenta 1  ")
    val cuenta1 = CuentaBancaria("Pepe", "ES123456")
    println("Creando cuenta 2")
    val cuenta2 = CuentaBancaria("Juan", "ES987654", 150.0)
    println("\n--- Operaciones con la cuenta de pepe ---")
    cuenta1.ingresar(50.0)
    cuenta1.ingresar(-10.0)
    cuenta1.retirar(20.0)
    cuenta1.retirar(100.0)
    cuenta1.mostrarInformacion()

    println("\n--- Operaciones con la cuenta de Juan ---")
    cuenta2.retirar(50.0)
    cuenta2.retirar(0.0)
    cuenta2.mostrarInformacion()
}