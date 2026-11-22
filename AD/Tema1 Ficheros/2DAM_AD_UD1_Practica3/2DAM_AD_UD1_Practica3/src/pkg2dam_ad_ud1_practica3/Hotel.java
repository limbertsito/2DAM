package pkg2dam_ad_ud1_practica3;

import java.io.Serializable;

/**
 * Clase que representa un Hotel ofertado en los paquetes de viaje.
 * Implementa Serializable para permitir su almacenamiento en ficheros binarios secuenciales.
 * 
 * @author limbert.lovcub
 */
public class Hotel implements Serializable {
    
    private static final long serialVersionUID = 7603663575377433614L;

    private String nombre;
    private String descripcion;
    private String ciudad;
    private double precioNoche;

    public Hotel() {
    }

    public Hotel(String nombre, String descripcion, String ciudad, double precioNoche) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        this.precioNoche = precioNoche;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(double precioNoche) {
        this.precioNoche = precioNoche;
    }

    @Override
    public String toString() {
        return "Hotel: " + nombre + 
               " | Ciudad: " + ciudad + 
               " | Descripción: " + descripcion + 
               " | Precio/noche: " + String.format("%.2f", precioNoche) + "€";   
    }
}
