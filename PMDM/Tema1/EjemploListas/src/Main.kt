//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
         val diasSemana: MutableList<String> = mutableListOf("Lunes","Martes","Miercoles","Jueves","Viernes")
    val nuevaLista : MutableList<String> = mutableListOf()

    diasSemana.forEach {
        nuevaLista.add(it)
    }
    println(nuevaLista)
}