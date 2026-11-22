/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg2dam_ad_ud1_práctica1;

import java.io.File;

/**
 *
 * @author limbe
 */
public class Ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        //Declaramos la ruta y el nombre
        //Serán final ya que no tenemos pensado cambiar el nombre 
        final String RUTA="src/";
        final String NOMBRE="file001.txt";
        
        //Importamos la clase File y le damos argumentos a su constructor
        File fichero = new File(RUTA+NOMBRE);
        
        //Nos pintará el nombre del fichero o directorio
        System.out.println("Nombre: "+fichero.getName());
        
        //Nos devolverá la ruta del directorio por consola
        System.out.println("Ruta: "+fichero.getPath());
        
        //Nos pintará el nombre del directorio padre
        System.out.println("Directorio padre: "+fichero.getParent());
        
        //Haremos un condicional con el método exists(), si nos devuelve true pintaremos un mensaje por pantalla
        if(fichero.exists()){
            System.out.println("¡El fichero existe!");
        } else {
            System.out.println("El fichero no existe :(");
        }
        System.out.println("Permisos(rwx)=>"+fichero.canRead()+fichero.canWrite()+fichero.canExecute());
        
        //Mediante el método length sabremos la longitud que tiene en bytes
        System.out.println("Longitud del fichero: "+fichero.length()+" bytes");
        
    }
    
}
