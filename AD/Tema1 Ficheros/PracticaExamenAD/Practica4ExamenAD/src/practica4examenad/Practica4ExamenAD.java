/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica4examenad;

// Importaciones de archivos y colecciones
import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author limbe
 */
public class Practica4ExamenAD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        List<Alumno> listaAlumnos = new ArrayList<>();
        
        listaAlumnos.add(new Alumno (1,"Ana","García López","DAM",2,8.5));
        listaAlumnos.add(new Alumno(2,"Pedro","gomez Perez", "DAM",1,7.5));
        
        System.out.println("GUARDADNO EN XML");
        GestionAlumno.guardarAlumnosXML(listaAlumnos);
        
        //leer
        System.out.println("LEYENDO");
        
        List<Alumno>alumnosLeidos = GestionAlumno.leerAlumnosXML();
        for (Alumno alumno : alumnosLeidos) {
            System.out.println(alumno);
        }
    }
    
    
}
