package pkg2dam_ad_udx_prácticay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio1 {

    // Scanner global para leer lo que el usuario escribe por teclado
    final static Scanner sc = new Scanner(System.in);
    // Nombre del fichero donde vamos a guardar los datos
    final static String FICHERO = "usuario.txt";

    public static void main(String[] args) {
        // Pedimos los datos del usuario uno a uno usando nuestro método auxiliar
        String dni = pedirDatosTexto("DNI");
        String nombre = pedirDatosTexto("nombre");
        String apellidos = pedirDatosTexto("apellidos");
        String fechaNacimiento = pedirDatosTexto("fecha de nacimiento");
        String numTelefono = pedirDatosTexto("número de teléfono");

        // Llamamos al método que se encarga de guardar estos datos en el fichero
        guardarDatosFichero(dni, nombre, apellidos, fechaNacimiento, numTelefono);

        // Llamamos al método que lee el fichero y muestra los datos por pantalla
        leerDatosFichero(FICHERO);

        // Cerramos el scanner al terminar el programa
        sc.close();
    }

    // Método para pedir un dato por teclado y evitar que nos lo dejen en blanco
    private static String pedirDatosTexto(String dato) {
        System.out.print("Introduce tu " + dato + ": ");
        return sc.nextLine().trim();
    }

    // Método que comprueba si el fichero existe en el ordenador
    // Devuelve true si existe y false si no existe
    public static boolean comprobarExisteFichero(String nombreFichero) {
        File f = new File(nombreFichero);
        if (!f.exists()) {
            System.out.println("Aviso: El archivo '" + nombreFichero + "' todavía no existe.");
            return false;
        }
        return true;
    }

    // Método para escribir los datos en el fichero de texto
    private static void guardarDatosFichero(String dni, String nombre, String apellidos, String fechaNac, String tlf) {
        BufferedWriter bw = null;

        try {
            // Creamos el FileWriter para conectarnos al archivo y el BufferedWriter para escribir texto
            // Por defecto, si el archivo ya existía, lo sobreescribe con los nuevos datos
            bw = new BufferedWriter(new FileWriter(FICHERO));

            // Escribimos cada dato y con bw.newLine() damos un salto de línea
            bw.write("DNI: " + dni);
            bw.newLine();

            bw.write("Nombre: " + nombre);
            bw.newLine();

            bw.write("Apellido: " + apellidos);
            bw.newLine();

            bw.write("Fecha de Nacimiento: " + fechaNac);
            bw.newLine();

            bw.write("Número de teléfono: " + tlf);
            bw.newLine();

            System.out.println("\nDatos guardados correctamente en " + FICHERO);

        } catch (FileNotFoundException e) {
            // Salta si no se puede crear el archivo o la ruta no existe
            System.err.println("No se ha encontrado el archivo: " + e.getMessage());
        } catch (IOException e) {
            // Salta si ocurre algún fallo mientras se está escribiendo en el disco
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        } finally {
            // En el bloque finally cerramos siempre el archivo con bw.close()
            // Esto es obligatorio para que se guarden los cambios y no se quede abierto
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el archivo: " + e.getMessage());
            }
        }
    }

    // Método para leer el archivo y mostrar lo que contiene por consola
    private static void leerDatosFichero(String nombreFichero) {
        // Antes de ponernos a leer, comprobamos si el archivo existe con nuestro método
        if (!comprobarExisteFichero(nombreFichero)) {
            return;
        }

        BufferedReader br = null;

        try {
            // Usamos FileReader para abrir el archivo y BufferedReader para poder leer líneas enteras
            br = new BufferedReader(new FileReader(nombreFichero));
            String linea;

            System.out.println("\n--- DATOS LEÍDOS DESDE EL FICHERO ---");

            // br.readLine() va leyendo el archivo línea por línea
            // Cuando ya no quedan más líneas que leer, devuelve 'null' y el bucle termina
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            System.out.println("-------------------------------------\n");

        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado al leer: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            // Cerramos el lector en el finally para liberar el archivo
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el lector: " + e.getMessage());
            }
        }
    }
}
