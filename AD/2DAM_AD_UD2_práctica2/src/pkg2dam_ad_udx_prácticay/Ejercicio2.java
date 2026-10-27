package pkg2dam_ad_udx_prácticay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2 {

    final static Scanner sc = new Scanner(System.in);
    final static String FICHERO = "numeros.txt";

    public static void main(String[] args) {
        // 1. Pedimos los números y los guardamos en el archivo hasta que introduzca un 0
        guardarNumeros(FICHERO);

        // 2. Leemos el archivo y sumamos todos los números que contiene
        int suma = sumarNumeros(FICHERO);

        System.out.println("\n✓ La suma total de los números es: " + suma);

        sc.close();
    }

    // Método para comprobar si el archivo existe antes de intentar leerlo
    public static boolean comprobarExisteFichero(String nombreFichero) {
        File f = new File(nombreFichero);
        if (!f.exists()) {
            System.out.println("Aviso: El archivo '" + nombreFichero + "' no existe todavía.");
            return false;
        }
        return true;
    }

    // Método para pedir números enteros por teclado de forma segura
    // Si el usuario mete letras, evitamos que el programa se cuelgue
    private static int pedirNumeroEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = sc.nextInt();
                sc.nextLine(); // Limpiamos el intro (salto de línea) que queda en el buffer
                return valor;
            } catch (Exception e) {
                System.out.println("Error: Introduce un número entero válido (sin letras ni decimales).");
                sc.nextLine(); // Limpiamos el texto erróneo para que no se quede atascado en bucle
            }
        }
    }

    // Pide números hasta que se introduzca un 0 y los guarda en el fichero
    public static void guardarNumeros(String nombreFichero) {
        BufferedWriter bw = null;

        try {
            // Abrimos el archivo para escribir texto en él
            bw = new BufferedWriter(new FileWriter(nombreFichero));
            int numero;

            System.out.println("Introduce números enteros (introduce 0 para finalizar):");

            do {
                numero = pedirNumeroEntero("Introduce un número: ");

                // Si no es cero, lo guardamos en el fichero
                if (numero != 0) {
                    // IMPORTANTE PARA EL EXAMEN:
                    // Usamos String.valueOf(numero) para que guarde el texto del número (ej: "5")
                    // Si pusiéramos solo bw.write(numero), escribiría el carácter según la tabla ASCII
                    bw.write(String.valueOf(numero));
                    bw.newLine(); // Damos un salto de línea para que cada número tenga su fila
                }

            } while (numero != 0);

            System.out.println("Datos guardados correctamente en " + nombreFichero);

        } catch (FileNotFoundException e) {
            System.err.println("No se pudo crear el archivo: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        } finally {
            // Siempre cerramos el archivo en el finally para volcar los datos y liberar el fichero
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el archivo: " + e.getMessage());
            }
        }
    }

    // Lee el fichero línea a línea, convierte el texto a número y los va sumando
    public static int sumarNumeros(String nombreFichero) {
        // Si el archivo no existe, no intentamos abrirlo y devolvemos 0
        if (!comprobarExisteFichero(nombreFichero)) {
            return 0;
        }

        int suma = 0;
        BufferedReader br = null;

        try {
            // Usamos FileReader y BufferedReader para leer el archivo de texto
            br = new BufferedReader(new FileReader(nombreFichero));
            String linea;

            // Leemos línea a línea hasta que br.readLine() devuelva null (fin del fichero)
            while ((linea = br.readLine()) != null) {
                linea = linea.trim(); // Quitamos espacios que puedan sobrar

                // Si la línea está vacía, pasamos a la siguiente
                if (linea.isEmpty()) {
                    continue;
                }

                try {
                    // Como del archivo leemos texto (String), lo convertimos a int para poder sumarlo
                    int numero = Integer.parseInt(linea);
                    suma += numero; // Acumulamos el valor en la variable suma
                } catch (NumberFormatException e) {
                    // Si alguna línea tuviese letras o datos raros, mostramos aviso sin detener el programa
                    System.err.println("Línea no numérica ignorada: " + linea);
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Fichero no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            // Cerramos el BufferedReader en el finally para no dejar el archivo bloqueado
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el archivo: " + e.getMessage());
            }
        }

        return suma;
    }
}
