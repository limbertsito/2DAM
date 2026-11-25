//es una clase que etsa hecha para trabajar on los datos , dirctamente y no con la direccin de memeoria
data class Reserva(
    val cliente: String,
    val habitacion: Int,
    val noches: Int,
    val precioNoche: Double
){
    fun calcularTotal(): Double=noches*precioNoche
}
