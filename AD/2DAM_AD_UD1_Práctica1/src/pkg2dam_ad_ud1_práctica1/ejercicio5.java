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
public class ejercicio5 {
    public static void main(String[] args) {
        
        // Creamos un objeto File que representa el directorio raíz "2DAM"
        File directorio = new File("2DAM");
        
        // Comprobamos que el directorio existe y que realmente es un directorio (no un archivo)
        if (directorio.exists() && directorio.isDirectory()) {
            System.out.println("Existe el directorio " + directorio.getName());
            
            // listFiles() devuelve un array de objetos File con el contenido del directorio
            // A diferencia de list(), que devuelve Strings, listFiles() devuelve objetos File
            // con los que podemos usar métodos como isDirectory(), getAbsolutePath(), etc.
            File[] archivos = directorio.listFiles();
            
            // Comprobamos que no sea null para evitar NullPointerException
            if (archivos != null) {
                
                // Recorremos todos los elementos (archivos y carpetas) del directorio "2DAM"
                for (int i = 0; i < archivos.length; i++) {
                    
                    // Si el elemento es un directorio (subcarpeta), entramos a listar su contenido
                    if (archivos[i].isDirectory()) {
                        
                        // Obtenemos los archivos dentro de la subcarpeta
                        File[] subCarpetas = archivos[i].listFiles();
                        
                        // Recorremos cada archivo dentro de la subcarpeta
                        // e imprimimos su ruta absoluta completa
                        for (int j = 0; j < subCarpetas.length; j++) {
                            System.out.println("Ruta completa: " + subCarpetas[j].getAbsolutePath());
                        }
                        
                    } else {
                        // Si no es un directorio, es un archivo normal
                        // Imprimimos directamente su ruta absoluta
                        System.out.println("Ruta completa: " + archivos[i].getAbsolutePath());
                    }
                }
            }
            
        } else {
            System.out.println("El directorio no existe o no es válido");
        }
        
    }
}
