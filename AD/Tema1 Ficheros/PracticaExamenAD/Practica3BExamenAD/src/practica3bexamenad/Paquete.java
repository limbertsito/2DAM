/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package practica3bexamenad;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
/**
 *
 * @author limbe
 */
public class Paquete implements Serializable{
private static final long serialVersionUID = 1L;
    private String nombre;
    private LocalDate inicio;
    private LocalDate fin;
    private Vuelo ida;
    private Vuelo vuelta;
    private Hotel estancia;

    public Paquete(String nombre, LocalDate inicio, LocalDate fin, Vuelo ida, Vuelo vuelta, Hotel estancia) {
        this.nombre = nombre;
        this.inicio = inicio;
        this.fin = fin;
        this.ida = ida;
        this.vuelta = vuelta;
        this.estancia = estancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public Vuelo getIda() {
        return ida;
    }

    public void setIda(Vuelo ida) {
        this.ida = ida;
    }

    public Vuelo getVuelta() {
        return vuelta;
    }

    public void setVuelta(Vuelo vuelta) {
        this.vuelta = vuelta;
    }

    public Hotel getEstancia() {
        return estancia;
    }

    public void setEstancia(Hotel estancia) {
        this.estancia = estancia;
    }
    public long duracionDias(){
        return java.time.temporal.ChronoUnit.DAYS.between(inicio, inicio);
    }
    
    public double getPrecioTotal(){
        long dias = duracionDias();
    return ida.getPrecio()+vuelta.getPrecio()+(estancia.getPrecioNoche()*dias);
    }
    @Override
    public String toString() {
        return "Paquete{" + "nombre=" + nombre + ", inicio=" + inicio + ", fin=" + fin + ", ida=" + ida + ", vuelta=" + vuelta + ", estancia=" + estancia + ", Precio total: "+getPrecioTotal()+", Duracion en dias "+duracionDias()+'}';
    }
    
    
}
