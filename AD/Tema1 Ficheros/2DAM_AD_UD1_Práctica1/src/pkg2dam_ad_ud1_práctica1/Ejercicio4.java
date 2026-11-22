/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package pkg2dam_ad_ud1_práctica1;
import java.io.File;
/**
 *
 * @author limbe
 */
public class Ejercicio4 {
    public static void main(String[] args) {
        // Creamos el objeto File con el nombre del directorio para poder trabajar con sus métodos
        File directorio=new File("2DAM");
        
        // Creamos un array de String para guardar la lista de archivos y carpetas dentro de "2DAM"
        // list() devuelve null si el directorio no existe o no es un directorio
        String []archivos=directorio.list();
        
        // Comprobamos que no sea null para evitar un NullPointerException
        //Siempre antes de leer un array hay que comprobar que no sea nulo,
        //ya que si lo fuera nos daria un error de ejecucion
        if(archivos != null){
            for (String archivo : archivos) {
                System.out.println("Se ha encontrado : " + archivo);
            }
        } else {
            System.out.println("El directorio no existe o no es válido");
        }
        
    }
}
