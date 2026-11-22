package pkg2dam_ad_ud1_practica3;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Gestor encargado de la persistencia y operaciones CRUD sobre Hoteles
 * utilizando ficheros binarios secuenciales (ObjectOutputStream / ObjectInputStream).
 * 
 * @author limbert.lovcub
 */
public class GestorHoteles {
    
    private final File ficheroHoteles;
    private final ArrayList<Hotel> listaHoteles;

    public GestorHoteles(File ficheroHoteles) {
        this.ficheroHoteles = ficheroHoteles;
        this.listaHoteles = leerFichero();
    }
    
    public ArrayList<Hotel> getListaHoteles() {
        return listaHoteles;
    }
    
    /**
     * Guarda la lista actual de hoteles en el fichero binario secuencial.
     * Utiliza try-with-resources para garantizar el cierre seguro de los flujos.
     */
    private void guardarFichero() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroHoteles, false))) {
            for (Hotel h : listaHoteles) {
                oos.writeObject(h);
            }
        } catch (IOException e) {
            System.err.println("Error al guardar hoteles en el fichero binario: " + e.getMessage());
        }
    }
    
    /**
     * Lee secuencialmente todos los objetos Hotel almacenados en el fichero binario.
     * Detecta el fin del archivo mediante la captura de EOFException.
     * 
     * @return Lista con los hoteles recuperados del fichero
     */
    private ArrayList<Hotel> leerFichero() {
        ArrayList<Hotel> lista = new ArrayList<>();
        if (!ficheroHoteles.exists() || ficheroHoteles.length() == 0) {
            return lista;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroHoteles))) {
            while (true) {
                Hotel h = (Hotel) ois.readObject();
                lista.add(h);
            }
        } catch (EOFException e) {
            // Fin natural del fichero binario secuencial
        } catch (ClassNotFoundException | IOException | ClassCastException e) {
            System.err.println("Aviso al leer hoteles del fichero binario: " + e.getMessage());
        }
        return lista;
    }
    
    /**
     * Muestra todos los hoteles disponibles numerados.
     */
    public void listarHoteles() {
        if (listaHoteles.isEmpty()) {
            System.out.println("No hay hoteles registrados.");
            return;
        }

        System.out.println("\n--- LISTA DE HOTELES ---");
        for (int i = 0; i < listaHoteles.size(); i++) {
            System.out.println((i + 1) + ". " + listaHoteles.get(i));
        }
    }
    
    /**
     * Añade un nuevo hotel a la lista y actualiza el fichero binario en disco.
     * 
     * @param hotel Objeto Hotel a insertar
     */
    public void insertarHotel(Hotel hotel) {
        listaHoteles.add(hotel);
        guardarFichero();
        System.out.println("Hotel insertado y guardado correctamente.");
    }
    
    /**
     * Modifica los datos de un hotel existente según su posición.
     * 
     * @param indice Posición en la lista (0-indexed)
     * @param nuevoHotel Nuevo objeto Hotel
     * @return true si se modificó correctamente, false en caso contrario
     */
    public boolean modificarHotel(int indice, Hotel nuevoHotel) {
        if (indice >= 0 && indice < listaHoteles.size()) {
            listaHoteles.set(indice, nuevoHotel);
            guardarFichero();
            System.out.println("Hotel modificado correctamente.");
            return true;
        }
        System.out.println("Índice no válido.");
        return false;
    }

    /**
     * Elimina un hotel existente según su posición.
     * 
     * @param indice Posición en la lista (0-indexed)
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean borrarHotel(int indice) {
        if (indice >= 0 && indice < listaHoteles.size()) {
            listaHoteles.remove(indice);
            guardarFichero();
            System.out.println("Hotel eliminado correctamente.");
            return true;
        }
        System.out.println("Índice no válido.");
        return false;
    }
}
