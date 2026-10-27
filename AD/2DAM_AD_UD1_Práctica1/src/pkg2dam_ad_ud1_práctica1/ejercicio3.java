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
public class ejercicio3 {
    public static void main(String[] args) {
        
        // Creamos un objeto File que apunta al archivo original que queremos renombrar
        File archivoOG=new File("2DAM/AD/P1.txt");
        
        // Creamos otro objeto File con el nuevo nombre deseado
        // Importante: debe estar en el mismo directorio (misma ruta padre) que el original,
        // ya que renameTo() no mueve archivos entre directorios de forma fiable
        File f=new File("2DAM/AD/Practica1.txt");
        
        // El método renameTo(File dest) renombra el archivo al nombre del File pasado por parámetro
        // Devuelve true si el renombrado fue exitoso, false en caso contrario
        // Puede fallar si el archivo original no existe o si ya existe un archivo con el nombre destino
        if(archivoOG.renameTo(f)){
            System.out.println("El nombre del archivo ha sido cambiado a "+f.getName());
        } else {
            System.out.println("El nombre no se ha cambiado a "+f.getName());
        }
        
    }
}
