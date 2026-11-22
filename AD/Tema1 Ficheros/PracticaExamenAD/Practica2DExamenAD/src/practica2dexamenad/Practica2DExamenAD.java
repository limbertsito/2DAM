/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica2dexamenad;

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
public class Practica2DExamenAD {

    final static Scanner sc = new Scanner(System.in);
    //Creamso el fichero
    final static File fichero = new File("src/datos.txt");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int opcion=-1;

        do {
             mostrarMenu();
             
             try{
             opcion = Integer.parseInt(sc.nextLine());
              switch (opcion) {
                case 1 -> {
                    insertarUsusario();
                }
                case 2 -> {
                    mostrarTodos();
                }
                case 3 -> {
                    mostrarNPrimeros();
                }
                case 4 -> {
                    buscarPorDni();
                }
                case 0 -> {
                    System.out.println("Saliendo...");
                }
                default ->
                    System.out.println("Opción invalida");
            }
             
             }catch(NumberFormatException e){
                 System.out.println("Error no es un numero entero"+ e.getMessage());
             }
           
        } while (opcion != 0);

    }

    private static void mostrarMenu() {
       
        
            System.out.println("MENUUUUU");
            System.out.println("1.Añadir nuevo usuario");
            System.out.println("2. Msotrar todos");
            System.out.println("3. Mostrar los N primeros");
            System.out.println("4. Buscar usuario por DNI");
            System.out.println("Elige una opcion 1-4");
          
    }

    private static void insertarUsusario() {

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
    }

    private static void mostrarTodos() {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(fichero));
            String linea = "";

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);

            }

        } catch (FileNotFoundException ex) {
            System.out.println("Error al encontrar el archivo : " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error : " + ex.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el flujo de lectura : " + ex.getMessage());
            }
        }
    }

    private static void mostrarNPrimeros() {

        System.out.println("introduce el numero de usuarios que quieires ver: ");
        int num = 0;
        try {
            num = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException numberFormatException) {
            System.out.println(numberFormatException.getMessage());
        }

        BufferedReader br = null;
        int contador = 0;
        try {
            br = new BufferedReader(new FileReader(fichero));
            String linea = "";

            while ((linea = br.readLine()) != null && contador < (num * 5)) {
                contador++;
                System.out.println(linea);

            }

        } catch (FileNotFoundException ex) {
            System.out.println("Error al encontrar el archivo : " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error : " + ex.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el flujo de lectura : " + ex.getMessage());
            }
        }
    }

    private static void buscarPorDni() {
        System.out.println("introduce el dni que buscas");
        String dniBuscado= sc.nextLine();
        boolean encontrado = false;
        BufferedReader br = null;
        int contador = 0;

        try {
            br = new BufferedReader(new FileReader(fichero));
            String linea = "";
            
            while ((linea=br.readLine())!=null){
                if(linea==dniBuscado && !encontrado){
                    encontrado=true;
                }
                if(encontrado){
                    System.out.println(linea);
                    contador++;
                }
                if(contador==5)break
                        ;
                if(!encontrado){System.out.println("No se ha encontraod ningun usuario con dni "+dniBuscado);}
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Error al encontrar el archivo : " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error : " + ex.getMessage());
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el flujo de lectura : " + ex.getMessage());
            }
        }
    }
}


