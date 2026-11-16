/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package practica3bexamenad;
import java.io.Serializable;
import java.util.Scanner;
/**
 *
 * @author limbe
 */
public class Hotel implements Serializable{
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String descripcion;
    private String ciudad;
    private double precioNoche;

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
        return "Hotel{" + "nombre=" + nombre + ", descripcion=" + descripcion + ", ciudad=" + ciudad + ", precioNoche=" + precioNoche + '}';
    }
    
    
}
