//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    /*Utilizando el operador Elvis, crea un programa que te pida por
teclado números enteros e indique si es un número entero o no
el valor que ha introducido el usuario.
Deberá estar pidiendo números y mostrando los mensajes hasta
que el usuario introduzca el valor cero
    * */
    //Aceptaremos quie la variable pueda ser nula o entera
    var num: Int?
do {
    println("introduce numeros para ver si son enteros")
    println("Pulsa 0 para finalizar. ")
    var numLeido = readln().trim()
    //Intentamos pasarlo a entero o nulo y asi controlamos el null
    num=numLeido.toIntOrNull()

    var mensaje : String? =null


    if(num!=null){
        mensaje= "Es un numero entero "
    }else{
    mensaje = null
    }
    //Se puede mete dentro de un print
    println(mensaje?:"No es null")
}while(num!=0)
}
