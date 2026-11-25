const val MIN=0
const val MAX=50//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    println("Sorteo loteria")
    println("POrfvor introduce 6 numero uncios entre $MIN y $MAX")

    var contador=1
    val numerosLoteria :MutableList<Int> = mutableListOf()
    do {
        println("Introduzca ek numero $contador")
        var numero=readln().toIntOrNull()?:-1
        if(numero!=-1 && numero !in numerosLoteria){
            numerosLoteria.add(numero)
            contador++
        }else{
            println("Vuulve a introducir el numero $contador")
        }

    }while (contador<=6)
    println("Boletio completado")
    for (numero in numerosLoteria){
        if (numero ==numerosLoteria.lastIndex){
            print("$numero")
        }
        print("$numero*")

    }

}


