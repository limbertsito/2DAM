/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica2examenad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author limbe
 */
public class Practica2ExamenAD {

    final static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        System.out.println("introduce tu dni");

        String dni = sc.nextLine();

        System.out.println("introduice tu nombre ");
        String nombre = sc.nextLine();

        System.out.println("intorduce tu apellidos : ");
        String apellidos = sc.nextLine();

        System.out.println("introduce tu fecha de nacimiento_: ");
        String fechaNacimiento = sc.nextLine();

        System.out.println("Introxuce tu telfono");
        String telefono = sc.nextLine();

        //Creamso el fichero
        File fichero = new File("src/datos.txt");

        BufferedWriter bw = null;
        try {
            //Creamso el Stream de salida

            bw = new BufferedWriter(new FileWriter(fichero, true));
            bw.write(dni);
            bw.newLine();
            bw.write(nombre);
            bw.newLine();
            bw.write(apellidos);
            bw.newLine();
            bw.write(fechaNacimiento);
            bw.newLine();
            bw.write(telefono);
            bw.newLine();

        } catch (IOException ex) {
            System.out.println("Error al aescribir: " + ex.getMessage());
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ex) {
                    System.out.println("Error ala cerrar" + ex.getMessage());
                }
            }
        }
        
       BufferedReader br= null;
       
       try{
           br=new BufferedReader(new FileReader(fichero));
           String linea = "";
           
           while((linea= br.readLine())!=null){
               System.out.println(linea);
               
           }
           
           
       } catch (FileNotFoundException ex) {
           System.out.println("Error al encontrar el archivo : "+ex.getMessage() );
       } catch (IOException ex) {
           System.out.println("Error : "+ex.getMessage());
       }
       finally{
            try {
                br.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el flujo de lectura : "+ex.getMessage());
            }
       }

    }

}
