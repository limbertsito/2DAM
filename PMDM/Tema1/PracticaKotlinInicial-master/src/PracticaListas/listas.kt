package PracticaListas

fun main(){
    val listaMutables =mutableListOf<String>("Madrid","Salamanca","Avila")
    listaMutables.add("Zamora")
    var contador=1
    for (m in listaMutables){

        println("$contador º : $m")
        contador++
    }
}