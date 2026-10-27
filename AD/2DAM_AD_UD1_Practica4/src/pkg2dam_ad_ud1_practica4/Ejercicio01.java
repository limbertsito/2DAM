package pkg2dam_ad_ud1_practica4;

import java.io.File;
import java.util.Scanner;

/**
 * Clase principal que implementa el menú interactivo para la Práctica 4 de Acceso a Datos.
 * Gestiona el departamento de recursos humanos de Venancio Corp mediante RandomAccessFile.
 * 
 * @author limbert.lovcub
 */
public class Ejercicio01 {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // Carpeta donde se guardará el fichero binario de acceso aleatorio
        File directorio = new File("datos");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        File ficheroEmpleados = new File(directorio, "empleados.dat");
        GestionEmpleados gestor = new GestionEmpleados(ficheroEmpleados);

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    gestor.listarEmpleados();
                    break;

                case 2:
                    System.out.println("\n--- CONSULTAR EMPLEADO ---");
                    String nifConsulta = leerNif("Introduzca el NIF a consultar (8 números y 1 letra): ");
                    gestor.consultarEmpleado(nifConsulta);
                    break;

                case 3:
                    System.out.println("\n--- INSERTAR EMPLEADO ---");
                    String nifNuevo = leerNif("NIF (8 números y 1 letra, máx 9 car): ");
                    String nombre = leerTexto("Nombre (máx 10 car): ", Empleado.LONG_NOMBRE);
                    String apellidos = leerTexto("Apellidos (máx 10 car): ", Empleado.LONG_APELLIDOS);
                    double salario = leerDoublePositivo("Salario (€): ");

                    Empleado nuevoEmpleado = new Empleado(nifNuevo, nombre, apellidos, salario);
                    gestor.insertarEmpleado(nuevoEmpleado);
                    break;

                case 4:
                    System.out.println("\n--- MODIFICAR SALARIO ---");
                    String nifMod = leerNif("Introduzca el NIF del empleado: ");
                    double nuevoSalario = leerDoublePositivo("Nuevo salario (€): ");
                    gestor.modificarSalario(nifMod, nuevoSalario);
                    break;

                case 5:
                    System.out.println("\n--- BORRADO LÓGICO DE EMPLEADO ---");
                    String nifBorrar = leerNif("Introduzca el NIF del empleado a eliminar: ");
                    gestor.borrarEmpleado(nifBorrar);
                    break;

                case 0:
                    System.out.println("\nSaliendo del sistema de gestión de Venancio Corp. ¡Hasta pronto!");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción entre 0 y 5.");
            }

        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n==============================================");
        System.out.println("   VENANCIO CORP - RECURSOS HUMANOS (UD1)     ");
        System.out.println("==============================================");
        System.out.println("1. Listado general de empleados");
        System.out.println("2. Consultar empleado por NIF");
        System.out.println("3. Insertar nuevo empleado");
        System.out.println("4. Modificar salario de empleado");
        System.out.println("5. Borrar empleado (lógico)");
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
     * Solicita una cadena de texto no vacía y recorta si supera la longitud máxima.
     */
    private static String leerTexto(String mensaje, int maxLongitud) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            if (!texto.isEmpty()) {
                if (texto.length() > maxLongitud) {
                    return texto.substring(0, maxLongitud);
                }
                return texto;
            }
            System.out.println("El campo no puede estar vacío.");
        }
    }

    /**
     * Lee un entero por teclado con control de excepciones.
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
