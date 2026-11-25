const val MIN=20
const val MAX=30
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    /*
    Crea una lista de números enteros que simulen temperaturas (por ejemplo: ). Tu objetivo es filtrar las temperaturas que sean "agradables"
(entre 20 y 30 grados, ambos incluidos).
Debes hacerlo en dos pasos:
o Crea una variable llamada esAgradable y guárdale una expresión lambda
explícita que reciba un entero y devuelva verdadero si cumple la
condición. Su estructura debe ser: { temperatura: Int -> ... }.
o Usa el método filter pasándole directamente la variable esAgradable por
los paréntesis y guarda el resultado en una nueva lista. Imprime el
resultado.
Pista: Para comprobar dos condiciones a la vez (que sea mayor o igual a
20 Y menor o igual a 30) debes usar el operador &&.
     */

    //Creamos la lista de temperatura
    val temperaturas : List<Int> =listOf(15,22, 35, 10, 28, 40)

    //Creamos la funcion lambda y la guasrdamos dentro de la variable

    var esAgradable={temperatura: Int ->temperatura in MIN..MAX}

    val tempInRate = temperaturas.filter (esAgradable)

    println("Las temperaturas dentro de rango son $tempInRate")
}