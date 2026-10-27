package pkg2dam_ad_ud1_practica5;

import java.io.File;
import java.util.Scanner;

/**
 * Clase principal que implementa el menú interactivo para la Práctica 5 de Acceso a Datos.
 * Modifica la Práctica 4 para utilizar ficheros XML a través del protocolo DOM,
 * manteniendo inalterados el modelo y la vista según las directrices del enunciado.
 * 
 * @author limbert.lovcub
 */
public class Ejercicio01 {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Directorio donde se almacenará el fichero XML
        File directorio = new File("datos");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        File ficheroXML = new File(directorio, "empleados.xml");
        GestionEmpleadosXML gestor = new GestionEmpleadosXML(ficheroXML);

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    gestor.listarEmpleados();
                    break;

                case 2:
                    System.out.println("\n--- CONSULTAR EMPLEADO (DOM) ---");
                    String nifConsulta = leerNif("Introduzca el NIF a consultar (8 números y 1 letra): ");
                    gestor.consultarEmpleado(nifConsulta);
                    break;

                case 3:
                    System.out.println("\n--- INSERTAR EMPLEADO EN XML (DOM) ---");
                    String nifNuevo = leerNif("NIF (8 números y 1 letra): ");
                    String nombre = leerTexto("Nombre: ");
                    String apellidos = leerTexto("Apellidos: ");
                    double salario = leerDoublePositivo("Salario (€): ");

                    Empleado nuevoEmpleado = new Empleado(nifNuevo, nombre, apellidos, salario);
                    gestor.insertarEmpleado(nuevoEmpleado);
                    break;

                case 4:
                    System.out.println("\n--- MODIFICAR SALARIO EN XML (DOM) ---");
                    String nifMod = leerNif("Introduzca el NIF del empleado: ");
                    double nuevoSalario = leerDoublePositivo("Nuevo salario (€): ");
                    gestor.modificarSalario(nifMod, nuevoSalario);
                    break;

                case 5:
                    System.out.println("\n--- BORRAR EMPLEADO DEL XML (DOM) ---");
                    String nifBorrar = leerNif("Introduzca el NIF del empleado a eliminar: ");
                    gestor.borrarEmpleado(nifBorrar);
                    break;

                case 0:
                    System.out.println("\nSaliendo del sistema de gestión XML de Venancio Corp. ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción entre 0 y 5.");
            }

        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n==============================================");
        System.out.println("   VENANCIO CORP - GESTIÓN XML CON DOM (UD1)  ");
        System.out.println("==============================================");
        System.out.println("1. Listado general de empleados");
        System.out.println("2. Consultar empleado por NIF");
        System.out.println("3. Insertar nuevo empleado");
        System.out.println("4. Modificar salario de empleado");
        System.out.println("5. Borrar empleado");
        System.out.println("0. Salir");
        System.out.println("==============================================");
    }

    /**
     * Solicita y valida que el NIF cumpla el formato de 9 caracteres (8 números y 1 letra).
     */
    private static String leerNif(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String nif = sc.nextLine().trim().toUpperCase();
            if (nif.matches("^[0-9]{8}[A-Za-z]$")) {
                return nif;
            }
            System.out.println("Formato de NIF incorrecto. Debe contener exactamente 8 números y 1 letra (ej: 12345678A).");
        }
    }

    /**
     * Solicita una cadena de texto no vacía.
     */
    private static String leerTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("El campo no puede estar vacío.");
        }
    }

    /**
     * Lee un entero por teclado con control exhaustivo de excepciones.
     */
    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduzca un número entero válido.");
            }
        }
    }

    /**
     * Lee un valor double positivo con control de excepciones.
     */
    private static double leerDoublePositivo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double val = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
                if (val >= 0) {
                    return val;
                }
                System.out.println("Error: El valor no puede ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, introduzca un valor numérico decimal válido.");
            }
        }
    }
}