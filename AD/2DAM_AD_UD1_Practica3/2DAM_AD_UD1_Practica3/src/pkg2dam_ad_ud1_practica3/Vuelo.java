package pkg2dam_ad_ud1_practica3;

import java.io.Serializable;

/**
 * Clase que representa un Vuelo disponible para los paquetes turísticos.
 * Implementa Serializable para permitir su almacenamiento en ficheros binarios secuenciales.
 * 
 * @author limbert.lovcub
 */
public class Vuelo implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String compania;
    private String origen;
    private String destino;
    private double precio;

    public Vuelo() {
    }

    public Vuelo(String compania, String origen, String destino, double precio) {
        this.compania = compania;
        this.origen = origen;
        this.destino = destino;
        this.precio = precio;
    }

    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Vuelo: " + compania + 
               " | Origen: " + origen + 
               " | Destino: " + destino + 
               " | Precio: " + String.format("%.2f", precio) + "€";   
    }
}
