package pkg2dam_ad_udx_prácticay;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio4 {

    final static Scanner sc = new Scanner(System.in);
    final static String FICHERO = "usuarios.txt";

    public static void main(String[] args) {
        int opcion = -1;

        // Bucle do-while para repetir el menú hasta que el usuario elija la opción 5 (Salir)
        do {
            mostrarMenu();

            // Pedimos la opción del menú de forma segura
            opcion = pedirNumero("Introduce una opción: ");

            switch (opcion) {
                case 1:
                    // Pedimos los datos del nuevo usuario mediante nuestro método modular
                    String dni = pedirDatosTexto("DNI");
                    String nombre = pedirDatosTexto("nombre");
                    String apellidos = pedirDatosTexto("apellidos");
                    String fechaNacimiento = pedirDatosTexto("fecha de nacimiento");
                    String numTelefono = pedirDatosTexto("número de teléfono");

                    // Guardamos el usuario en el archivo
                    guardarDatosFichero(dni, nombre, apellidos, fechaNacimiento, numTelefono);
                    System.out.println("Usuario guardado correctamente.\n");
                    break;

                case 2:
                    // Muestra todos los usuarios guardados
                    mostrarTodosLosUsuarios();
                    break;

                case 3:
                    // Pide cuántos usuarios mostrar y los lista
                    int numUsuarios = pedirNumero("Introduce cuántos usuarios quieres mostrar: ");
                    mostrarLosNUsuarios(numUsuarios);
                    break;

                case 4:
                    // Pide un DNI y busca a ese usuario concreto
                    String dniBuscado = pedirDatosTexto("DNI del usuario a buscar");
                    buscarUsuarioPorDNI(dniBuscado);
                    break;

                case 5:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Debe ser entre 1 y 5.\n");
            }
        } while (opcion != 5);

        sc.close();
    }

    // Muestra el menú de opciones por consola
    private static void mostrarMenu() {
        System.out.println("--- MENÚ PRINCIPAL ---");
        System.out.println("1. Almacenar nuevo usuario");
        System.out.println("2. Muestra todos los usuarios");
        System.out.println("3. Muestra solo los N primeros");
        System.out.println("4. Muestra un usuario concreto en base al DNI");
        System.out.println("5. Salir");
    }

    // Método modular para pedir texto por teclado
    private static String pedirDatosTexto(String dato) {
        System.out.print("Introduce tu " + dato + ": ");
        return sc.nextLine().trim();
    }

    // Método modular para pedir un número entero sin que el programa se caiga si meten letras
    private static int pedirNumero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = sc.nextInt();
                sc.nextLine(); // Limpiamos el salto de línea que deja nextInt() en el buffer
                return valor;
            } catch (Exception e) {
                System.out.println("Error: Introduce un número entero válido.");
                sc.nextLine(); // Limpiamos el texto erróneo para que no se quede en bucle infinito
            }
        }
    }

    // Método para comprobar si el archivo existe antes de intentar leerlo
    public static boolean comprobarExisteFichero(String nombreFichero) {
        File f = new File(nombreFichero);
        if (!f.exists()) {
            System.out.println("Aviso: El archivo '" + nombreFichero + "' todavía no existe.\n");
            return false;
        }
        return true;
    }

    // OPCIÓN 1: Guarda un usuario en el archivo sin borrar los anteriores
    private static void guardarDatosFichero(String dni, String nombre, String apellido, String fechaNac, String tlf) {
        BufferedWriter bw = null;

        try {
            // CLAVE PARA EL EXAMEN:
            // El segundo parámetro 'true' activa el modo APPEND.
            // Si ponemos 'true', escribe al final del archivo sin borrar los usuarios que ya existían.
            // Si no pusiéramos 'true', borraría todo cada vez que guardamos un usuario.
            bw = new BufferedWriter(new FileWriter(FICHERO, true));

            // Guardamos todos los datos en una sola línea usando ';' como separador
            bw.write(dni + ";" + nombre + ";" + apellido + ";" + fechaNac + ";" + tlf);
            bw.newLine(); // Salto de línea para el siguiente usuario que guardemos

        } catch (FileNotFoundException e) {
            System.err.println("No se pudo crear o abrir el fichero: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero: " + e.getMessage());
        } finally {
            // Cerramos siempre el BufferedWriter en el finally
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el fichero: " + e.getMessage());
            }
        }
    }

    // OPCIÓN 2: Lee y muestra todos los usuarios del fichero
    private static void mostrarTodosLosUsuarios() {
        // Comprobamos si el archivo existe antes de leerlo
        if (!comprobarExisteFichero(FICHERO)) {
            return;
        }

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(FICHERO));
            String linea;

            System.out.println("\n--- TODOS LOS USUARIOS ---");

            // Leemos línea a línea hasta llegar al final del archivo (null)
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

            System.out.println("--------------------------\n");

        } catch (FileNotFoundException e) {
            System.err.println("Fichero no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el fichero: " + e.getMessage());
            }
        }
    }

    // OPCIÓN 3: Muestra únicamente los primeros N usuarios leídos del fichero
    private static void mostrarLosNUsuarios(int numUsuarios) {
        if (!comprobarExisteFichero(FICHERO)) {
            return;
        }

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(FICHERO));
            String linea;
            int contadorUsuarios = 0; // Lleva la cuenta de cuántos usuarios hemos mostrado

            System.out.println("\n--- PRIMEROS " + numUsuarios + " USUARIOS ---");

            // Leemos mientras queden líneas Y el contador no haya llegado al número pedido N
            while ((linea = br.readLine()) != null && contadorUsuarios < numUsuarios) {
                System.out.println(linea);
                contadorUsuarios++; // Incrementamos en 1 tras mostrar cada usuario
            }

            System.out.println("--------------------------\n");

        } catch (FileNotFoundException e) {
            System.err.println("Fichero no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el fichero: " + e.getMessage());
            }
        }
    }

    // OPCIÓN 4: Busca un usuario por su DNI
    private static void buscarUsuarioPorDNI(String dniBuscado) {
        if (!comprobarExisteFichero(FICHERO)) {
            return;
        }

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(FICHERO));
            String linea;
            boolean encontrado = false; // Variable para saber si dimos con el usuario

            System.out.println("\n--- RESULTADO BÚSQUEDA ---");

            while ((linea = br.readLine()) != null) {
                // Como guardamos los datos separados por ';', con split(";") dividimos la línea en trozos
                // datos[0] será el DNI, datos[1] el nombre, etc.
                String[] datos = linea.split(";");

                // equalsIgnoreCase compara el texto ignorando mayúsculas y minúsculas
                if (datos.length > 0 && datos[0].trim().equalsIgnoreCase(dniBuscado.trim())) {
                    System.out.println("Usuario encontrado: " + linea);
                    encontrado = true;
                    break; // Al encontrarlo salimos del bucle con break para no seguir leyendo a lo tonto
                }
            }

            if (!encontrado) {
                System.out.println("No existe ningún usuario con el DNI: " + dniBuscado);
            }

            System.out.println("--------------------------\n");

        } catch (FileNotFoundException e) {
            System.err.println("Fichero no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el fichero: " + e.getMessage());
            }
        }
    }
}
