/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pkg2dam_ad_ud1_práctica1;
import java.io.File;
import java.io.IOException;
/**
 *
 * @author limbe
 */
public class Ejercicio2 {
    public static void main(String[] args) {

        
        //Creamos los objetos File y tras eso llamaremos a mkdir para crearlas
        File carpeta1=new File("2DAM/AD");
        File carpeta2=new File("2DAM/PSP");
            
       //Creamos las dos carpetas de tal manera que para que cree las carpetas intermedias por si no las hubiera
       if(carpeta1.mkdirs()){
           System.out.println("se han creado el directorio: "+carpeta1.getName());
       }else{
           System.out.println("El directorio ya existía");
       }
       
       //repetimos con el segundo directorio

       if(carpeta2.mkdir()){
        System.out.println("se han creado el directorio: "+carpeta2.getName());
       }else{
        System.out.println("El directorio "+carpeta2.getName()+" ya existía");
       }
        
        //Definimos la ruta de los ficheros internos de cada carpeta
        
        File p1=new File(carpeta1,"P1.txt");
        File p2=new File(carpeta1,"UD1.txt");
        File p3=new File(carpeta1,"UD2.txt");
        
        File p4=new File(carpeta2,"UD1.txt");
        File p5=new File(carpeta2,"UD2.txt");
        
        //Este documento está a la altura de las subcarpetas
        //Usaremos el getParent() de la carpeta 1 o 2 para obtener la ruta superior
        // ya que está a la altura de "2DAM"
        //Y le pondremos el nombre del archivo que queremos crear
        
        File txt=new File(carpeta1.getParent(),"misNotas.txt");
        
        //Controlamos la excepción ya que el fichero se creará solo si no existe
        try {
            p1.createNewFile();
            p2.createNewFile();
            p3.createNewFile();
            p4.createNewFile();
            p5.createNewFile();
            txt.createNewFile();
        } catch (IOException ex) {
            System.err.println("ERROR: "+ex.getMessage());
        }

    }
}
