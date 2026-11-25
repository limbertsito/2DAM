const val tamañoMax=5//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    /*
     Crea un programa que tenga una lista de palabras (por ejemplo: "sol",
"computadora", "luz", "programación", "gato"). Utiliza el método filter para
crear una nueva lista que contenga solo las palabras que tengan 5 letras o más.
Para la lambda dentro del filtro, dale un nombre descriptivo al parámetro (por
ejemplo, { palabra -> ... }).
Por último, imprime la nueva lista.
Pista: puedes saber cuántas letras tiene un texto usando .length (por
ejemplo: palabra.length).
     */
    val cosasRandom : List<String> = listOf("sol", "computadora", "luz", "Programación", "gato")

    //Utilizamos el parametro exxlpicito de la flecha ->
    //palabta seria a primera palabra
    val segundaLista = cosasRandom.filter { palabra -> palabra.length >= tamañoMax}

    println("Palabras originales $cosasRandom")
    println("Palabras de 5 o mas letras $segundaLista")
}