package pkg2dam_ad_udx_prácticay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ejercicio3 {

    final static Scanner sc = new Scanner(System.in);
    final static String FICHERO = "correo.txt";

    public static void main(String[] args) {
        // 1. Pedimos los datos del correo al usuario y los guardamos en el archivo
        guardarDatos(FICHERO);

        // 2. Leemos el archivo y mostramos el correo por pantalla
        mostrarDatos(FICHERO);

        sc.close();
    }

    // Método para pedir un dato de texto por teclado
    private static String pedirDatosTexto(String campo) {
        System.out.print("Introduce el " + campo + ": ");
        return sc.nextLine().trim();
    }

    // Método para comprobar si el archivo existe antes de leerlo
    public static boolean comprobarExisteFichero(String nombreFichero) {
        File f = new File(nombreFichero);
        if (!f.exists()) {
            System.out.println("Aviso: El archivo '" + nombreFichero + "' no existe todavía.");
            return false;
        }
        return true;
    }

    // Pide los datos del correo, añade la fecha y hora actual, y lo escribe en el archivo
    public static void guardarDatos(String nombreFichero) {
        BufferedWriter bw = null;

        try {
            // Abrimos el archivo en modo escritura
            bw = new BufferedWriter(new FileWriter(nombreFichero));

            // Pedimos los datos usando nuestro método auxiliar
            String destinatario = pedirDatosTexto("destinatario");
            String asunto = pedirDatosTexto("asunto");
            String cuerpo = pedirDatosTexto("cuerpo del correo");

            // Obtenemos la fecha y hora del sistema en el momento justo del envío
            LocalDateTime ahora = LocalDateTime.now();
            // Le damos formato legible: día/mes/año hora:minutos:segundos
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String fechaHoraEnvio = ahora.format(formato);

            // Escribimos cada campo en el archivo con su salto de línea
            bw.write("DESTINATARIO: " + destinatario);
            bw.newLine();

            bw.write("ASUNTO: " + asunto);
            bw.newLine();

            bw.write("CUERPO: " + cuerpo);
            bw.newLine();

            bw.write("FECHA Y HORA DE ENVÍO: " + fechaHoraEnvio);
            bw.newLine();

            System.out.println("\nCorreo almacenado correctamente en " + nombreFichero);

        } catch (FileNotFoundException e) {
            System.err.println("No se pudo crear el archivo: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al escribir el correo: " + e.getMessage());
        } finally {
            // Siempre cerramos el flujo en el finally
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el archivo: " + e.getMessage());
            }
        }
    }

    // Lee el archivo de texto del correo y lo muestra formateado por consola
    public static void mostrarDatos(String nombreFichero) {
        // Verificamos antes si el archivo existe
        if (!comprobarExisteFichero(nombreFichero)) {
            return;
        }

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(nombreFichero));
            String linea;

            System.out.println("\n----- CORREO ENVIADO -----");

            // Leemos todas las líneas del archivo hasta llegar al final (null)
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            System.out.println("--------------------------\n");

        } catch (FileNotFoundException e) {
            System.err.println("Fichero no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el archivo: " + e.getMessage());
            }
        }
    }
}
