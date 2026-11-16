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
public class Vuelo implements Serializable{
    private static final long serialVersionUID = 1L;
    private String compañia;
    private String origen;
    private String destino;
    private double precio;

    public Vuelo(String compañia, String origen, String destino, double precio) {
        this.compañia = compañia;
        this.origen = origen;
        this.destino = destino;
        this.precio = precio;
    }

    public String getCompañia() {
        return compañia;
    }

    public void setCompañia(String compañia) {
        this.compañia = compañia;
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
        return "Vuelo{" + "compa\u00f1ia=" + compañia + ", origen=" + origen + ", destino=" + destino + ", precio=" + precio + '}';
    }
    
    
}
