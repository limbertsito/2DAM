/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica1examenad;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author limbe
 */
public class Practica1ExamenAD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
//        String nombre = "file1.txt";
//        String ruta = "src";
//        File archivo = new File(ruta,nombre);
//        
//        comprobarExiste(archivo);
//       
//        mostraDatos(archivo);
//        
        String ruta = "src";
        File  dirAD= new File(ruta,"2DAM/AD");
        File dirPSP=new File(ruta,"2DAM/PSP");
        File txt = new File(ruta,"2DAM/MisNotas.txt");
        
        dirAD.mkdirs();
        dirPSP.mkdirs();
        
        
        if(dirAD.isDirectory() && dirPSP.isDirectory()){
            File p1=new File(dirAD.getAbsolutePath(),"p1.txt");
            File ud1= new File(dirAD.getAbsolutePath(),"UD1.txt");
            File ud2= new File(dirAD.getAbsolutePath(),"UD2.txt");
            
            File psp1= new File(dirPSP.getAbsolutePath(),"UD1.txt");
            File psp2= new File(dirPSP.getAbsolutePath(),"UD2.txt");
            
            try{
                p1.createNewFile();
                ud1.createNewFile();
                ud2.createNewFile();
                
                psp1.createNewFile();
                psp2.createNewFile();
                txt.createNewFile();
                System.out.println("CREADOS CON EXITO !");
                
                File destino = new File(dirAD.getAbsolutePath(),"Practica1.txt");
                cambiarNombre(p1,destino);
            }catch(IOException e){
                System.out.println("Error al crear los archivos : "+e.getMessage());
            }
        }
        
        File directorio= new File("2DAM");        
        
      listarRecursivo(directorio);  

        
        
        
        
        
    }
    private static void listarRecursivo(File directorio) {
        
        File [] lista = directorio.listFiles();
        
        if(lista!=null){
            for (File file : lista) {
                if(file.isFile()&& file.getName().endsWith(".txt")){
                    System.out.println(file.getName());
                }
                if(file.isDirectory()){
                    listarRecursivo(file);
                }
            }
            
        }
        }

    private static void comprobarExiste(File archivo) {
         if(archivo.exists()){
            System.out.println("Archivo existe!");
            
        }else {
            System.out.println("No existe el archivo!!");
            
            System.out.println("Vamos a crear el archivo");
           try{
            boolean creado = archivo.createNewFile();
            
            if (creado){
                            System.out.println("Fichero creado " + archivo.getAbsolutePath());

            }
           }catch (IOException e){
               System.out.println("Error al crear el fichero: "+e.getMessage());
           }
        }
    }

    private static void mostraDatos(File archivo) {
        
        System.out.println("Nombre : "+archivo.getName());
        
        System.out.println("Ruta : "+archivo.getAbsolutePath());
        
        System.out.println("Direcotrio padre : "+archivo.getParent());
        
        System.out.println("Es un fichero? "+ archivo.isFile());
        
        System.out.println("Permisos : r: "+archivo.canRead()+" w: "+archivo.canWrite()+" x: "+archivo.canExecute());
        
        System.out.println("Longitud : "+archivo.length());
    }

    private static void cambiarNombre(File p1, File destino) {
       boolean cambiado = p1.renameTo(destino);
       
       if (cambiado){
           System.out.println("Se ha cmabiado el nombre con exito");
       }
    }

    
    
    
    
}
