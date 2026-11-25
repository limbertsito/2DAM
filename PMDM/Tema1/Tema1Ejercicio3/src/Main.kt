//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    /**
     * En este ejercicio vamos a práctica cómo pasar una función como parámetro
     * de otra función usando una lambda.
     * • Vamos a crear nuestra propia función que recibe código. Al ser un bloque de
     * código que no recibe datos, no usaremos la flecha ->.
     * Crea una función tradicional llamada repetirAccion (fuera del main). Esta
     * función debe recibir dos parámetros:
     * o Un número llamado veces de tipo Int.
     * o Una acción llamada accion de tipo () -> Unit (una lambda sin
     * parámetros).
     * • Dentro de la función repetirAccion, haz un bucle (un for o un while) que dé
     * tantas vueltas como indique el parámetro veces. En cada vuelta, debe
     * ejecutar accion().
     * Finalmente, dentro de tu main, llama a esta función pasándole el número 3 y un
     * bloque de código (abriendo y cerrando llaves { }) que imprima el
     * mensaje "¡Estoy practicando lambdas!".
     * EL programa deberá repetir ese bloque tantas veces como se le haya indicado en
     * el primer parámetro.
     */
    println("Introduce las veces que quieres que se repita la accion")
    var veces= readln().toInt()

    //Aquí lo que hacemos le metemos un paraemtro int y la ñlambda
   repetirAccion(veces){
       println("Estoy practicando lambdas!!")
   }
    //Podria ser asi también, pero la manera óptima es la de arriba
    //repetirAccion(veces, {println("Estoy practicando lambdas!!")}
}
//Funcion que será el parametro de otra
fun repetirAccion(veces:Int, tipo: ()-> Unit){
    for(i in 1 .. veces){
        tipo() //Aqui ejecutamos la lambda
    }

}