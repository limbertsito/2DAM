import java.util.Locale
import java.util.Random
import kotlin.math.round

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    /**
     * Estás desarrollando un sistema rápido para matricular alumnos en segundo curso de
     * DAM. Para agilizar el proceso, el sistema utiliza un "perfil base" de alumno y genera
     * copias modificando solo los datos necesarios.
     * Tareas a realizar:
     * 1. Crea la Data Class: Define una data class llamada Alumno que tenga tres
     * propiedades inmutables (val):
     * o nombre (String)
     * o curso (String)
     * o notaMedia (Double)
     * 2. Pide datos al usuario: En tu función main(), pide al usuario que introduzca por
     * teclado cuántos alumnos desea generar.
     * 3. Crea un perfil base: Instancia un objeto de la clase Alumno que sirva como
     * plantilla. Por ejemplo: Alumno(nombre = "Sin asignar", curso = "2ºDAM",
     * notaMedia = 0.0).
     * 4. Genera los alumnos usando copy(): Crea un bucle que se repita tantas veces
     * como haya indicado el usuario. En cada iteración:
     * o Crea un nuevo alumno a partir de la plantilla usando la función copy().
     * o Cambia el nombre para que sea único (por ejemplo: "Alumno 1",
     * "Alumno 2", etc.).
     * o Asígnale una nota media aleatoria entre 0.0 y 10.0.
     * o Guarda los nuevos alumnos en una lista.
     * 5. Imprime los resultados desestructurando: Recorre la lista de alumnos
     * generados usando un bucle. En lugar de imprimir el objeto directamente, utiliza
     * variables separadas, y muestra un mensaje por consola.
     * Ejemplo de una ejecución:
     */
    println("Introduce cuantos alumnos deseas generar?  ")
    //Para que no pete en caso de que fuese null asi trabjaremos con el null a 0
    //Con el operador elvis si o si será un enteri
    var num: Int = readln().toIntOrNull() ?: 0

    //Aqaui el numero es entero pero comprobamos que no sea menos o igual a 0
    if(num<=0){
        println("Numero no valido")
        return
    }


    //Instanciamos el objeto base alumno
    val alumno=Alumno(nombre="Sin asignar","2ºDAM",notaMedia=0.0)

    println("Generando matriculas...")

    //Creamos la lista
    val alumnosDam: MutableList<Alumno> = mutableListOf()

    //Este sera el for que genere aleatoriamente la nota
    //A su vez añadiremos el alumno a la lista y la nta formateada
    for (i in 1..num) {
        //Hcaiendo esto forzmos a que tenga un solo decimal
        //        var nota=round(Random().nextDouble(0.0,10.0)*10)/10.0
        var nota=Random().nextDouble(0.0,10.0)

        var notaFormateada=String.format(Locale.US,"%.2f", nota*nota).toDouble()

        val nuevoAlumno = alumno.copy(
            nombre ="Alumno $i",
            notaMedia = notaFormateada
        )
        alumnosDam.add(nuevoAlumno)


    }

    //Pintamos los alumnos
    for (alumno in alumnosDam) {
        //Extraemos las tres propiedades en varioables separadas
        val(nombre, curso, notaMedia) = alumno

        println("El $nombre está matriculado en $curso con un $notaMedia de media")
    }
}

