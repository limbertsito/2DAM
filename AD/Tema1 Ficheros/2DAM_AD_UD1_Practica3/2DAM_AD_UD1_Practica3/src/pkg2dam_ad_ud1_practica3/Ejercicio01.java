package pkg2dam_ad_ud1_practica3;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Clase principal que implementa el menú interactivo para MilViajesSalamanca.
 * Gestiona vuelos, hoteles y paquetes turísticos almacenados en ficheros binarios secuenciales.
 * Cumple con los requisitos de la Práctica 3 de Acceso a Datos (2DAM):
 *  - Ficheros binarios secuenciales.
 *  - Validación de fechas (fecha de inicio anterior a la de fin).
 *  - Listado completo con cálculo de días y precio total.
 *  - Control exhaustivo de excepciones y validación de entradas por teclado.
 * 
 * @author limbert.lovcub
 */
public class Ejercicio01 {
    
    // Scanner global para toda la aplicación. Se lee línea a línea para evitar problemas de buffer.
    private static final Scanner sc = new Scanner(System.in);
    
    /**
     * Punto de entrada principal a la aplicación.
     * 
     * @param args Argumentos de la línea de comandos
     */
    public static void main(String[] args) {
        // Directorio donde se almacenarán los ficheros binarios
        final String rutaDirectorio = "datos";
        File directorio = new File(rutaDirectorio);

        // Preparamos y verificamos la carpeta de almacenamiento
        if (!prepararDirectorio(directorio)) {
            System.err.println("No se puede iniciar el programa sin el directorio de datos.");
            return;
        }

        // Definimos las rutas de los ficheros binarios secuenciales (.dat)
        File ficheroHoteles = new File(directorio, "hoteles.dat");
        File ficheroVuelos = new File(directorio, "vuelos.dat");
        File ficheroPaquetes = new File(directorio, "paquetes.dat");

        // Inicializamos los gestores correspondientes cargando los datos existentes
        GestorHoteles gestorHoteles = new GestorHoteles(ficheroHoteles);
        GestorVuelos gestorVuelos = new GestorVuelos(ficheroVuelos);
        GestorPaquetes gestorPaquetes = new GestorPaquetes(ficheroPaquetes);

        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    menuVuelos(gestorVuelos);
                    break;
                case 2:
                    menuHoteles(gestorHoteles);
                    break;
                case 3:
                    menuPaquetes(gestorPaquetes, gestorVuelos, gestorHoteles);
                    break;
                case 4:
                    System.out.println("\nSaliendo de la aplicación. ¡Gracias por usar MilViajesSalamanca!");
                    break;
                default:
                    System.out.println("Opción no válida. Introduzca un número del 1 al 4.");
            }

        } while (opcion != 4);
    }

    /**
     * Muestra el menú principal de la aplicación.
     */
    public static void mostrarMenuPrincipal() {
        System.out.println("\n==================================");
        System.out.println("   MILVIAJES SALAMANCA - GESTIÓN  ");
        System.out.println("==================================");
        System.out.println("1. Gestionar Vuelos");
        System.out.println("2. Gestionar Hoteles");
        System.out.println("3. Gestionar Paquetes de Viajes");
        System.out.println("4. Salir");
    }
    
    /**
     * Comprueba si el directorio de datos existe y, si no, lo crea de forma segura.
     * 
     * @param directorio Objeto File del directorio
     * @return true si el directorio existe o se pudo crear, false en caso contrario
     */
    private static boolean prepararDirectorio(File directorio) {
        try {
            if (directorio.exists()) {
                if (!directorio.isDirectory()) {
                    System.err.println("La ruta existe pero no es un directorio válido: " + directorio.getPath());
                    return false;
                }
                return true;
            }

            if (directorio.mkdirs()) {
                System.out.println("Directorio creado correctamente: " + directorio.getAbsolutePath());
                return true;
            }
        } catch (Exception e) {
            System.err.println("Error al preparar el directorio de almacenamiento: " + e.getMessage());
        }
        return false;
    }

    // ==========================================================
    //                   GESTIÓN DE VUELOS
    // ==========================================================

    /**
     * Submenú para gestionar vuelos (listar, introducir, modificar y borrar).
     * 
     * @param gestorVuelos Instancia de GestorVuelos
     */
    private static void menuVuelos(GestorVuelos gestorVuelos) {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE VUELOS ---");
            System.out.println("1. Listar vuelos");
            System.out.println("2. Introducir vuelo");
            System.out.println("3. Modificar vuelo");
            System.out.println("4. Borrar vuelo");
            System.out.println("5. Volver al menú principal");
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    gestorVuelos.listarVuelos();
                    break;

                case 2:
                    System.out.println("\n-- Nuevo Vuelo --");
                    String compania = leerTextoNoVacio("Compañía aérea: ");
                    String origen = leerTextoNoVacio("Origen: ");
                    String destino = leerTextoNoVacio("Destino: ");
                    double precio = leerDoublePositivo("Precio (€): ");

                    Vuelo nuevoVuelo = new Vuelo(compania, origen, destino, precio);
                    gestorVuelos.insertarVuelo(nuevoVuelo);
                    break;

                case 3:
                    if (gestorVuelos.getListaVuelos().isEmpty()) {
                        System.out.println("No hay vuelos registrados para modificar.");
                        break;
                    }
                    gestorVuelos.listarVuelos();
                    int indiceMod = leerIndiceValido("Introduce el número del vuelo a modificar: ", gestorVuelos.getListaVuelos().size());

                    System.out.println("\n-- Modificar Vuelo #" + (indiceMod + 1) + " --");
                    String nCompania = leerTextoNoVacio("Nueva Compañía aérea: ");
                    String nOrigen = leerTextoNoVacio("Nuevo Origen: ");
                    String nDestino = leerTextoNoVacio("Nuevo Destino: ");
                    double nPrecio = leerDoublePositivo("Nuevo Precio (€): ");

                    Vuelo vueloModificado = new Vuelo(nCompania, nOrigen, nDestino, nPrecio);
                    gestorVuelos.modificarVuelo(indiceMod, vueloModificado);
                    break;

                case 4:
                    if (gestorVuelos.getListaVuelos().isEmpty()) {
                        System.out.println("No hay vuelos registrados para borrar.");
                        break;
                    }
                    gestorVuelos.listarVuelos();
                    int indiceBorrar = leerIndiceValido("Introduce el número del vuelo a borrar: ", gestorVuelos.getListaVuelos().size());
                    gestorVuelos.borrarVuelo(indiceBorrar);
                    break;

                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida. Elija un número entre 1 y 5.");
            }

        } while (opcion != 5);
    }

    // ==========================================================
    //                   GESTIÓN DE HOTELES
    // ==========================================================

    /**
     * Submenú para gestionar hoteles (listar, introducir, modificar y borrar).
     * 
     * @param gestorHoteles Instancia de GestorHoteles
     */
    private static void menuHoteles(GestorHoteles gestorHoteles) {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE HOTELES ---");
            System.out.println("1. Listar hoteles");
            System.out.println("2. Introducir hotel");
            System.out.println("3. Modificar hotel");
            System.out.println("4. Borrar hotel");
            System.out.println("5. Volver al menú principal");
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    gestorHoteles.listarHoteles();
                    break;

                case 2:
                    System.out.println("\n-- Nuevo Hotel --");
                    String nombre = leerTextoNoVacio("Nombre: ");
                    String desc = leerTextoNoVacio("Descripción: ");
                    String ciudad = leerTextoNoVacio("Ciudad: ");
                    double precio = leerDoublePositivo("Precio por noche (€): ");

                    Hotel nuevo = new Hotel(nombre, desc, ciudad, precio);
                    gestorHoteles.insertarHotel(nuevo);
                    break;

                case 3:
                    if (gestorHoteles.getListaHoteles().isEmpty()) {
                        System.out.println("No hay hoteles registrados para modificar.");
                        break;
                    }
                    gestorHoteles.listarHoteles();
                    int indiceMod = leerIndiceValido("Introduce el número del hotel a modificar: ", gestorHoteles.getListaHoteles().size());

                    System.out.println("\n-- Modificar Hotel #" + (indiceMod + 1) + " --");
                    String nuevoNombre = leerTextoNoVacio("Nuevo Nombre: ");
                    String nuevaDesc = leerTextoNoVacio("Nueva Descripción: ");
                    String nuevaCiudad = leerTextoNoVacio("Nueva Ciudad: ");
                    double nuevoPrecio = leerDoublePositivo("Nuevo Precio por noche (€): ");

                    Hotel modificado = new Hotel(nuevoNombre, nuevaDesc, nuevaCiudad, nuevoPrecio);
                    gestorHoteles.modificarHotel(indiceMod, modificado);
                    break;

                case 4:
                    if (gestorHoteles.getListaHoteles().isEmpty()) {
                        System.out.println("No hay hoteles registrados para borrar.");
                        break;
                    }
                    gestorHoteles.listarHoteles();
                    int indiceBorrar = leerIndiceValido("Introduce el número del hotel a borrar: ", gestorHoteles.getListaHoteles().size());
                    gestorHoteles.borrarHotel(indiceBorrar);
                    break;

                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida. Elija un número entre 1 y 5.");
            }

        } while (opcion != 5);
    }
    
    // ==========================================================
    //               GESTIÓN DE PAQUETES DE VIAJE
    // ==========================================================

    /**
     * Submenú para gestionar paquetes de viaje (listar, introducir, modificar y borrar).
     * Aplica las validaciones de fechas (inicio anterior a fin) y selección de vuelos y hotel.
     * 
     * @param gestorPaquetes Instancia de GestorPaquetes
     * @param gestorVuelos Instancia de GestorVuelos
     * @param gestorHoteles Instancia de GestorHoteles
     */
    private static void menuPaquetes(GestorPaquetes gestorPaquetes, GestorVuelos gestorVuelos, GestorHoteles gestorHoteles) {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE PAQUETES DE VIAJE ---");
            System.out.println("1. Listar paquetes");
            System.out.println("2. Introducir paquete");
            System.out.println("3. Modificar paquete");
            System.out.println("4. Borrar paquete");
            System.out.println("5. Volver al menú principal");
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    gestorPaquetes.listarPaquetes();
                    break;

                case 2:
                    // Verificación de disponibilidad previa de vuelos y hoteles
                    if (gestorVuelos.getListaVuelos().isEmpty()) {
                        System.out.println("Aviso: Debe registrar al menos un vuelo antes de crear un paquete de viaje.");
                        break;
                    }
                    if (gestorHoteles.getListaHoteles().isEmpty()) {
                        System.out.println("Aviso: Debe registrar al menos un hotel antes de crear un paquete de viaje.");
                        break;
                    }

                    System.out.println("\n-- Nuevo Paquete de Viaje --");
                    String nombre = leerTextoNoVacio("Nombre identificativo del paquete (ej. 'Primavera en las Dehesas'): ");

                    // Lectura y validación obligatoria de fechas: fecha de inicio anterior a fin
                    LocalDate fechaInicio = leerFecha("Fecha de inicio (YYYY-MM-DD): ");
                    LocalDate fechaFin = leerFecha("Fecha de fin (YYYY-MM-DD): ");

                    while (!fechaInicio.isBefore(fechaFin)) {
                        System.err.println("Error: La fecha de inicio debe ser estrictamente anterior a la de fin.");
                        fechaInicio = leerFecha("Fecha de inicio (YYYY-MM-DD): ");
                        fechaFin = leerFecha("Fecha de fin (YYYY-MM-DD): ");
                    }

                    // Selección del Vuelo de Ida
                    System.out.println("\nSeleccione el Vuelo de IDA:");
                    gestorVuelos.listarVuelos();
                    int idxIda = leerIndiceValido("Número del vuelo de ida: ", gestorVuelos.getListaVuelos().size());
                    Vuelo vueloIda = gestorVuelos.getListaVuelos().get(idxIda);

                    // Selección del Vuelo de Vuelta
                    System.out.println("\nSeleccione el Vuelo de VUELTA:");
                    gestorVuelos.listarVuelos();
                    int idxVuelta = leerIndiceValido("Número del vuelo de vuelta: ", gestorVuelos.getListaVuelos().size());
                    Vuelo vueloVuelta = gestorVuelos.getListaVuelos().get(idxVuelta);

                    // Selección del Hotel de estancia
                    System.out.println("\nSeleccione el HOTEL de estancia:");
                    gestorHoteles.listarHoteles();
                    int idxHotel = leerIndiceValido("Número del hotel: ", gestorHoteles.getListaHoteles().size());
                    Hotel hotel = gestorHoteles.getListaHoteles().get(idxHotel);

                    // Creación e inserción del nuevo paquete
                    Paquete nuevoPaquete = new Paquete(nombre, fechaInicio, fechaFin, vueloIda, vueloVuelta, hotel);
                    gestorPaquetes.insertarPaquete(nuevoPaquete);
                    break;

                case 3:
                    if (gestorPaquetes.getListaPaquetes().isEmpty()) {
                        System.out.println("No hay paquetes registrados para modificar.");
                        break;
                    }

                    gestorPaquetes.listarPaquetes();
                    int indiceMod = leerIndiceValido("Introduce el número del paquete a modificar: ", gestorPaquetes.getListaPaquetes().size());

                    System.out.println("\n-- Modificar Paquete #" + (indiceMod + 1) + " --");
                    String nNombre = leerTextoNoVacio("Nuevo Nombre identificativo: ");

                    LocalDate nInicio = leerFecha("Nueva fecha de inicio (YYYY-MM-DD): ");
                    LocalDate nFin = leerFecha("Nueva fecha de fin (YYYY-MM-DD): ");

                    while (!nInicio.isBefore(nFin)) {
                        System.err.println("Error: La fecha de inicio debe ser estrictamente anterior a la de fin.");
                        nInicio = leerFecha("Nueva fecha de inicio (YYYY-MM-DD): ");
                        nFin = leerFecha("Nueva fecha de fin (YYYY-MM-DD): ");
                    }

                    System.out.println("\nSeleccione el nuevo Vuelo de IDA:");
                    gestorVuelos.listarVuelos();
                    int nIdxIda = leerIndiceValido("Número del vuelo de ida: ", gestorVuelos.getListaVuelos().size());
                    Vuelo nVueloIda = gestorVuelos.getListaVuelos().get(nIdxIda);

                    System.out.println("\nSeleccione el nuevo Vuelo de VUELTA:");
                    gestorVuelos.listarVuelos();
                    int nIdxVuelta = leerIndiceValido("Número del vuelo de vuelta: ", gestorVuelos.getListaVuelos().size());
                    Vuelo nVueloVuelta = gestorVuelos.getListaVuelos().get(nIdxVuelta);

                    System.out.println("\nSeleccione el nuevo HOTEL:");
                    gestorHoteles.listarHoteles();
                    int nIdxHotel = leerIndiceValido("Número del hotel: ", gestorHoteles.getListaHoteles().size());
                    Hotel nHotel = gestorHoteles.getListaHoteles().get(nIdxHotel);

                    Paquete paqueteModificado = new Paquete(nNombre, nInicio, nFin, nVueloIda, nVueloVuelta, nHotel);
                    gestorPaquetes.modificarPaquete(indiceMod, paqueteModificado);
                    break;

                case 4:
                    if (gestorPaquetes.getListaPaquetes().isEmpty()) {
                        System.out.println("No hay paquetes registrados para borrar.");
                        break;
                    }
                    gestorPaquetes.listarPaquetes();
                    int indiceBorrar = leerIndiceValido("Introduce el número del paquete a borrar: ", gestorPaquetes.getListaPaquetes().size());
                    gestorPaquetes.borrarPaquete(indiceBorrar);
                    break;

                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida. Elija un número entre 1 y 5.");
            }

        } while (opcion != 5);
    }

    // ==========================================================
    //               MÉTODOS DE VALIDACIÓN Y ENTRADA
    // ==========================================================

    /**
     * Solicita una fecha por consola y valida su formato estricto ISO (YYYY-MM-DD).
     * 
     * @param mensaje Mensaje a mostrar al usuario
     * @return Objeto LocalDate válido
     */
    private static LocalDate leerFecha(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();
            try {
                return LocalDate.parse(entrada);
            } catch (DateTimeParseException e) {
                System.out.println("Error: Formato de fecha incorrecto. Debe ser YYYY-MM-DD (ejemplo: 2026-07-15).");
            }
        }
    }

    /**
     * Solicita un índice numérico al usuario (1-indexed) y lo traduce a índice de lista (0-indexed).
     * Repite la petición si el índice no está dentro del rango permitido.
     * 
     * @param mensaje Mensaje a mostrar al usuario
     * @param totalElementos Cantidad de elementos en la lista
     * @return Índice válido (0 a totalElementos - 1)
     */
    private static int leerIndiceValido(String mensaje, int totalElementos) {
        while (true) {
            int seleccionado = leerEntero(mensaje) - 1;
            if (seleccionado >= 0 && seleccionado < totalElementos) {
                return seleccionado;
            }
            System.out.println("Error: Opción fuera de rango. Debe introducir un número entre 1 y " + totalElementos + ".");
        }
    }
    
    /**
     * Lee un texto que no puede estar vacío ni contener solo espacios.
     * 
     * @param mensaje Mensaje a mostrar al usuario
     * @return Texto no vacío
     */
    private static String leerTextoNoVacio(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Error: Este campo no puede dejarse vacío.");
        }
    }

    /**
     * Solicita un número decimal (double) mayor o igual a cero, capturando NumberFormatException.
     * 
     * @param mensaje Mensaje a mostrar al usuario
     * @return Valor double válido mayor o igual a 0
     */
    private static double leerDoublePositivo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
                if (valor >= 0) {
                    return valor;
                }
                System.out.println("Error: El valor no puede ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe introducir un número decimal válido (ejemplo: 120.50).");
            }
        }
    }

    /**
     * Solicita un número entero por consola, capturando NumberFormatException.
     * 
     * @param mensaje Mensaje a mostrar al usuario
     * @return Número entero introducido
     */
    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe introducir un número entero válido.");
            }
        }
    }
}
