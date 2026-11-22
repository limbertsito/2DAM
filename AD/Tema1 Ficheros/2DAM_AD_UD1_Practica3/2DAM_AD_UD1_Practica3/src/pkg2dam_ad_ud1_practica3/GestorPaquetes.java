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
 * Gestor encargado de la persistencia y operaciones CRUD sobre Paquetes de viajes
 * utilizando ficheros binarios secuenciales (ObjectOutputStream / ObjectInputStream).
 * 
 * @author limbert.lovcub
 */
public class GestorPaquetes {
    
    private final File ficheroPaquetes;
    private final ArrayList<Paquete> listaPaquetes;

    public GestorPaquetes(File ficheroPaquetes) {
        this.ficheroPaquetes = ficheroPaquetes;
        this.listaPaquetes = leerFichero();
    }
    
    public ArrayList<Paquete> getListaPaquetes() {
        return listaPaquetes;
    }
    
    /**
     * Guarda la lista actual de paquetes en el fichero binario secuencial.
     * Utiliza try-with-resources para garantizar el cierre seguro de los flujos.
     */
    private void guardarFichero() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroPaquetes, false))) {
            for (Paquete p : listaPaquetes) {
                oos.writeObject(p);
            }
        } catch (IOException e) {
            System.err.println("Error al guardar paquetes en el fichero binario: " + e.getMessage());
        }
    }
    
    /**
     * Lee secuencialmente todos los objetos Paquete almacenados en el fichero binario.
     * Detecta el fin del archivo mediante la captura de EOFException.
     * 
     * @return Lista con los paquetes recuperados del fichero
     */
    private ArrayList<Paquete> leerFichero() {
        ArrayList<Paquete> lista = new ArrayList<>();
        if (!ficheroPaquetes.exists() || ficheroPaquetes.length() == 0) {
            return lista;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ficheroPaquetes))) {
            while (true) {
                Paquete p = (Paquete) ois.readObject();
                lista.add(p);
            }
        } catch (EOFException e) {
            // Fin natural del fichero binario secuencial
        } catch (ClassNotFoundException | IOException | ClassCastException e) {
            System.err.println("Aviso al leer paquetes del fichero binario: " + e.getMessage());
        }
        return lista;
    }
    
    /**
     * Muestra todos los paquetes de viaje disponibles numerados,
     * incluyendo fechas, vuelos, hotel, duración en días y precio total.
     */
    public void listarPaquetes() {
        if (listaPaquetes.isEmpty()) {
            System.out.println("No hay paquetes registrados.");
            return;
        }

        System.out.println("\n--- LISTA DE PAQUETES DE VIAJE ---");
        for (int i = 0; i < listaPaquetes.size(); i++) {
            System.out.println((i + 1) + ". " + listaPaquetes.get(i));
        }
    }
    
    /**
     * Añade un nuevo paquete a la lista y actualiza el fichero binario en disco.
     * 
     * @param paquete Objeto Paquete a insertar
     */
    public void insertarPaquete(Paquete paquete) {
        listaPaquetes.add(paquete);
        guardarFichero();
        System.out.println("Paquete insertado y guardado correctamente.");
    }
    
    /**
     * Modifica los datos de un paquete existente según su posición.
     * 
     * @param indice Posición en la lista (0-indexed)
     * @param nuevoPaquete Nuevo objeto Paquete
     * @return true si se modificó correctamente, false en caso contrario
     */
    public boolean modificarPaquete(int indice, Paquete nuevoPaquete) {
        if (indice >= 0 && indice < listaPaquetes.size()) {
            listaPaquetes.set(indice, nuevoPaquete);
            guardarFichero();
            System.out.println("Paquete modificado correctamente.");
            return true;
        }
        System.out.println("Índice no válido.");
        return false;
    }

    /**
     * Elimina un paquete existente según su posición.
     * 
     * @param indice Posición en la lista (0-indexed)
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean borrarPaquete(int indice) {
        if (indice >= 0 && indice < listaPaquetes.size()) {
            listaPaquetes.remove(indice);
            guardarFichero();
            System.out.println("Paquete eliminado correctamente.");
            return true;
        }
        System.out.println("Índice no válido.");
        return false;
    }
}
