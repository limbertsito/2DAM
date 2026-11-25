/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;
import  java.sql.*;
/**
 *
 * @author limbe
 */
public class Equipo {
    
    private int id_equipo;
    private String nombre;
    private String ciudad;
    private Date fecha_creacion;

    public Equipo(int id_equipo, String nombre, String ciudad, Date fecha_creacion) {
        this.id_equipo = id_equipo;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.fecha_creacion = fecha_creacion; //El enuciado dice que es opcional en el toString lo ajustamos
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

 @Override
    public String toString() {
        return "Equipo [ID=" + id_equipo + ", Nombre=" + nombre 
             + ", Ciudad=" + ciudad 
             + ", Fecha Creación=" + (fecha_creacion != null ? fecha_creacion : "Sin fecha") + "]";
    }
    
    
}
