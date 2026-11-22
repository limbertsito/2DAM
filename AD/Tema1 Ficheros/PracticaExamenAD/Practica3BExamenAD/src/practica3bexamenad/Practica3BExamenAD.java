/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica3bexamenad;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author limbe
 */
public class Practica3BExamenAD {

    final static File CARPETA = new File("datos");
    final static File VUELOS = new File(CARPETA, "vuelos.dat");
    final static File HOTEL = new File(CARPETA, "hotel.dat");
    final static File PAQUETE = new File(CARPETA, "carpeta.dat");
    final static Scanner SC = new Scanner(System.in);

    //Arrays
    static ArrayList<Vuelo> listaVuelos = new ArrayList<>();
    static ArrayList<Hotel> listaHoteles = new ArrayList<>();
    static ArrayList<Paquete> listaPaquetes = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int opcion = -1;
        do {
            mostrarMenu();
            opcion = pedirEntero();
            switch (opcion) {
                case 1 ->
                    GestorVuelos();
                case 2 ->
                    gestorHoteles();
                case 3 ->
                    getorPaquetes();
                case 0 -> {
                    System.out.println("SALIENDO...");
                }
                default -> {
                    System.out.println("Opcion invalida");
                }
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("MENU PRINCIPAL");
        System.out.println("1.- GESTION VUELOS ");
        System.out.println("2.- GESTION HOTEL");
        System.out.println("3.- GESTION PAQUETE");
        System.out.println("0.- SALIR");
    }

    private static void GestorVuelos() {

        int opcion = -1;
        do {
            System.out.println("MENU GESTOR VUELOS___________________");
            mostrarMenuGeneral();
            opcion = pedirEntero();

            switch (opcion) {
                case 1 ->
                    listarVuelos();
                case 2 ->
                    gestionCRUDVuelos();
                case 3 -> {
                    System.out.println("Saliendo..........");
                }
                default -> {
                    System.out.println("Opcion invalida");
                }
            }

        } while (opcion != 0);

    }

    private static void gestorHoteles() {
        System.out.println("MENU GESTOR HOTELES__________________");
        int opcion = -1;
        do {
            mostrarMenuGeneral();
            opcion = pedirEntero();
            switch (opcion) {
                case 1 -> {
                }
                case 2 -> {
                }
                case 3 -> {
                    System.out.println("Saliendo..........");
                }
                default -> {
                    System.out.println("Opcion invalida");
                }
            }
        } while (opcion != 0);

    }

    private static void getorPaquetes() {
        int opcion = -1;
        do {
            System.out.println("MENU GESTOR PAQUETES_________________");

            mostrarMenuGeneral();
            opcion = pedirEntero();
            switch (opcion) {
                case 1 -> {
                }

                case 2 -> {
                }
                case 3 -> {
                    System.out.println("Saliendo..........");
                }
                default -> {
                    System.out.println("Opcion invalida");
                }
            }
        } while (opcion != 0);

    }

    private static void mostrarMenuGeneral() {
        System.out.println("1.- LISTAR.");
        System.out.println("2.- INTRODUCIR,MODIFICAR O BORRAR");
        System.out.println("0.- SALIR.");
    }

    private static void listarVuelos() {
        listaVuelos.clear();
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(new FileInputStream(VUELOS));

            while (true) {
                Vuelo vuelo = (Vuelo) ois.readObject();
                listaVuelos.add(vuelo);
                System.out.println(vuelo);
            }

        } catch (EOFException e) {
            System.out.println("Finaal del archivo: " + e.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println("ERROR no se ha encontrado el archivo" + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("ERROR de IO: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("ERROR DE CLASE: " + ex.getMessage());
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
                System.err.println("Error al cerrar el archivo");
            }
        }
    }

    private static void mostrarMenuCRUD() {
        System.out.println("MENU CRUD");

        System.out.println("1.- INTRODUCIR");
        System.out.println("2.-MODIFICAR");
        System.out.println("3.- BORRAR");
        System.out.println("0.-SALIR");
    }

    private static int pedirEntero() {
        int opcion = -1;
        System.out.println("Introduce una opcion : ");

        try {
            opcion = Integer.parseInt(SC.nextLine());

        } catch (NumberFormatException e) {
            System.err.println("Error el numero no es entero");
        }
        return opcion;
    }

    private static void gestionCRUDVuelos() {
        int opcion = -1;
        do {
            System.out.println("SUBMENU CRUD VUELOS: ");
            mostrarMenuCRUD();
            opcion = pedirEntero();

            switch (opcion) {
                case 1 -> {
                    introducirVuelo();
                }
                case 2 -> {
                    modificarVuelo();
                }
                case 3 -> {
                    borrarVuelo();
                }
                case 0 -> {
                    System.out.println("SALIENDO...");
                }
                default -> {
                    System.out.println("OPCION INVALIDA");
                }
            }
        } while (opcion != 0);
    }

    private static void introducirVuelo() {
        prepararDirectorio(CARPETA);

        System.out.println("AÑADIENDO VUELO");
        System.out.println("Comapñia: ");
        String comp = SC.nextLine();
        System.out.println("Origen: ");
        String ori = SC.nextLine();
        System.out.println("Destino: ");
        String dest = SC.nextLine();

        double precio = 0;
        boolean precioValido = false;
        do {
            System.out.println("Precio:");
            try {
                precio = Double.parseDouble(SC.nextLine());
                precioValido = true;
            } catch (NumberFormatException e) {
                System.err.println("Error: introduce un número válido.");
            }
        } while (!precioValido);

        Vuelo v = new Vuelo(comp, ori, dest, precio);

        //guardamos en el fichero binario
        ObjectOutputStream oos = null;

        try {
            if (VUELOS.exists()) {
                oos = new MyObjectOutputStream(new FileOutputStream(VUELOS, true));
            } else {
                oos = new ObjectOutputStream(new FileOutputStream(VUELOS));
            }
            oos.writeObject(v);

            System.out.println("Vuelo guardado");

        } catch (FileNotFoundException ex) {
            System.err.println("Error al encontrar el archivo : " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error IO : " + ex.getMessage());
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                System.out.println("Erro al cerrar el archivo: " + ex.getMessage());
            }
        }
    }

    private static void modificarVuelo() {
        listarVuelos();
        if (listaVuelos.isEmpty()) {
            System.out.println("No hay vuelos gauradados");
            return;
        }
        System.out.println("Introduce la compañia del vuelo que quiere modificar");
        String buscado = SC.nextLine();
        boolean encontrado = false;
        for (int i = 0; i < listaVuelos.size(); i++) {
            if (buscado.equalsIgnoreCase(listaVuelos.get(i).getCompañia())) {

                encontrado = true;
                System.out.println("Introduce la compañia: ");
                listaVuelos.get(i).setCompañia(SC.nextLine());
                System.out.println("Introduce el destino");
                listaVuelos.get(i).setDestino(SC.nextLine());
                System.out.println("Intorduce el origen: ");
                listaVuelos.get(i).setOrigen(SC.nextLine());
                System.out.println("Introduce el precio: ");
                listaVuelos.get(i).setPrecio(SC.nextDouble());

                break;
            }
            if (!encontrado) {
                System.out.println("No se ah encontrado ningun vuelo de esa comap´ñia " + buscado);
                return;
            }

        }
        // 3. REESCRIBIR TODO EL FICHERO BINARIO CON LA LISTA ACTUALIZADA (append = false)
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(VUELOS, false))) {
            for (Vuelo v : listaVuelos) {
                oos.writeObject(v);
            }
            System.out.println(" Vuelo modificado y fichero actualizado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al actualizar el fichero: " + e.getMessage());
        }
    }

    private static void borrarVuelo() {
        listarVuelos();
        if (listaVuelos.isEmpty()) {
            System.out.println("No se han podido borrar Vuelos ya que la lista esta vacia");
            return;
        }
        System.out.println("Introduce la compañia del vuelo que quieres borrar: ");
        String buscado = SC.nextLine();
        boolean encontrado = false;

        for (int i = 0; i < listaVuelos.size(); i++) {
            if (listaVuelos.get(i).getCompañia().equalsIgnoreCase(buscado)) {
                encontrado = true;
                System.out.println("=========Encontrado el vuielo a eliminar");
                listaVuelos.remove(i);
                break;
            }
            if (!encontrado) {
                System.out.println("No se ha encontrado el vuelo con la compañia " + buscado);
                return;
            }

        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(VUELOS, false))) {
            for (Vuelo listaVuelo : listaVuelos) {
                oos.writeObject(listaVuelo);
            }
            System.out.println("Se ha eliminado el vuelo del listado de vuelos");
        } catch (FileNotFoundException ex) {
            System.err.println("error ak encontrar el file : " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error E/S : " + ex.getMessage());
        }
    }

    private static void prepararDirectorio(File CARPETA) {
        if (!CARPETA.exists()) {
            System.out.println("El fichero no  existe." + CARPETA.getAbsolutePath());

            if (CARPETA.mkdir()) {
                System.out.println("Carpeta creada correctamente");
            } else {
                System.out.println("No se pudo crear la carpeta");
            }
        } else if (!CARPETA.isDirectory()) {
            System.out.println("Existye un archivo con ese nopmbre pero NO es un diRectoruio");
        } else {
            System.out.println("El directorio : " + CARPETA.getName() + "Ya existe y esta listo");
        }
    }

}
