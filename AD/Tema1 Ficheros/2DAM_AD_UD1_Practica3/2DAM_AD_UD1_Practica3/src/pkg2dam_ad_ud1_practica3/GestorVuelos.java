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
 * Gestor encargado de la persistencia y operaciones CRUD sobre Vuelos
 * utilizando ficheros binarios secuenciales (ObjectOutputStream / ObjectInputStream).
 * 
 * @author limbert.lovcub
 */
public class GestorVuelos {
    
    private final File ficheroVuelos;
    private final ArrayList<Vuelo> listaVuelos;

    public GestorVuelos(File ficheroVuelos) {
        this.ficheroVuelos = ficheroVuelos;
        this.listaVuelos = leerFichero();
    }
    
    public ArrayList<Vuelo> getListaVuelos() {
        return listaVuelos;
    }
    
    /**
     * Guarda la lista actual de vuelos en el fichero binario secuencial.
     * Utiliza try-with-resources para garantizar el cierre seguro de los flujos.
     */
    private void guardarFichero() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroVuelos, false))) {
            for (Vuelo v : listaVuelos) {
                oos.writeObject(v);
            }
        } catch (IOException e) {
            System.err.println("Error al guardar vuelos en el fichero binario: " + e.getMessage());
        }
    }
    
    /**
     * Lee secuencialmente todos los objetos Vuelo almacenados en el fichero binario.
     * Detecta el fin del archivo mediante la captura de EOFException.
     * 
     * @return Lista con los vuelos recuperados del fichero
     */
    private ArrayList<Vuelo> leerFichero() {
        ArrayList<Vuelo> lista = new ArrayList<>();
        if (!ficheroVuelos.exists() || ficheroVuelos.length() == 0) {
            return lista;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroVuelos))) {
            while (true) {
                Vuelo v = (Vuelo) ois.readObject();
                lista.add(v);
            }
        } catch (EOFException e) {
            // Fin natural del fichero binario secuencial
        } catch (ClassNotFoundException | IOException | ClassCastException e) {
            System.err.println("Aviso al leer vuelos del fichero binario: " + e.getMessage());
        }
        return lista;
    }
    
    /**
     * Muestra todos los vuelos disponibles numerados.
     */
    public void listarVuelos() {
        if (listaVuelos.isEmpty()) {
            System.out.println("No hay vuelos registrados.");
            return;
        }

        System.out.println("\n--- LISTA DE VUELOS ---");
        for (int i = 0; i < listaVuelos.size(); i++) {
            System.out.println((i + 1) + ". " + listaVuelos.get(i));
        }
    }
    
    /**
     * Añade un nuevo vuelo a la lista y actualiza el fichero binario en disco.
     * 
     * @param vuelo Objeto Vuelo a insertar
     */
    public void insertarVuelo(Vuelo vuelo) {
        listaVuelos.add(vuelo);
        guardarFichero();
        System.out.println("Vuelo insertado y guardado correctamente.");
    }
    
    /**
     * Modifica los datos de un vuelo existente según su posición.
     * 
     * @param indice Posición en la lista (0-indexed)
     * @param nuevoVuelo Nuevo objeto Vuelo
     * @return true si se modificó correctamente, false en caso contrario
     */
    public boolean modificarVuelo(int indice, Vuelo nuevoVuelo) {
        if (indice >= 0 && indice < listaVuelos.size()) {
            listaVuelos.set(indice, nuevoVuelo);
            guardarFichero();
            System.out.println("Vuelo modificado correctamente.");
            return true;
        }
        System.out.println("Índice no válido.");
        return false;
    }

    /**
     * Elimina un vuelo existente según su posición.
     * 
     * @param indice Posición en la lista (0-indexed)
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean borrarVuelo(int indice) {
        if (indice >= 0 && indice < listaVuelos.size()) {
            listaVuelos.remove(indice);
            guardarFichero();
            System.out.println("Vuelo eliminado correctamente.");
            return true;
        }
        System.out.println("Índice no válido.");
        return false;
    }
}
