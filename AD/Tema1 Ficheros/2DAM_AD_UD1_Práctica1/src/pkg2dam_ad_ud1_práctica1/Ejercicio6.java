/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg2dam_ad_ud1_práctica1;

import java.io.File;

/**
 *
 * @author limbert.lovcub
 */
public class Ejercicio6 {

    public static void main(String[] args) {
        
        // Creamos el objeto File con el nombre del directorio para poder trabajar con sus métodos
        File directorio = new File("2DAM");
        
        // Obtenemos un array de objetos File con el contenido del directorio "2DAM"
        File[] archivos = directorio.listFiles();
        
        // Comprobamos que no sea null para evitar un NullPointerException
        if (archivos != null) {
            
            System.out.println("--- Mostrando solo archivos .txt ---");
            
            // Recorremos cada elemento del directorio "2DAM"
            for (int i = 0; i < archivos.length; i++) {
                
                // Verificamos si el elemento es un directorio (subcarpeta)
                if (archivos[i].isDirectory()) {
                    
                    // Obtenemos los archivos dentro de la subcarpeta
                    File[] subCarpetas = archivos[i].listFiles();
                    
                    // Recorremos la subcarpeta si no es null
                    if (subCarpetas != null) {
                        for (int j = 0; j < subCarpetas.length; j++) {
                            // IMPORTANTE: usamos [j] (no [i]) porque estamos recorriendo subCarpetas
                            // Solo mostramos los archivos que terminen en ".txt"
                            if (subCarpetas[j].getName().endsWith(".txt")) {
                                System.out.println("Archivo: " + subCarpetas[j].getName());
                                System.out.println("Ruta: " + subCarpetas[j].getAbsolutePath());
                            }
                        }
                    }
                    
                } else {
                    // Si no es un directorio, comprobamos si es un .txt en la raíz de "2DAM"
                    // (por ejemplo: misNotas.txt)
                    if (archivos[i].getName().endsWith(".txt")) {
                        System.out.println("Archivo: " + archivos[i].getName());
                        System.out.println("Ruta: " + archivos[i].getAbsolutePath());
                    }
                }
            }
        }
    }
}
