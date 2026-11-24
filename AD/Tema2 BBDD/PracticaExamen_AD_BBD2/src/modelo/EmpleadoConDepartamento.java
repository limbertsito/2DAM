/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;
import java.util.Scanner;
/**
 *
 * @author limbe
 */
public class EmpleadoConDepartamento {

    
    private String dni;
    private String nombre;
    private String apellidos;
    private String puesto;
    private String nombreDepartamento;
    private String localidad;

    public EmpleadoConDepartamento(String dni, String nombre, String apellidos, String puesto, String nombreDepartamento, String localidad) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.puesto = puesto;
        this.nombreDepartamento = nombreDepartamento;
        this.localidad = localidad;
    }

    // Getters para que la vista los pueda pintar

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getPuesto() {
        return puesto;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public String getLocalidad() {
        return localidad;
    }

    @Override
    public String toString() {
        return "EmpleadoConDepartamento{" + "dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", puesto=" + puesto + ", nombreDepartamento=" + nombreDepartamento + ", localidad=" + localidad + '}';
    }
   
}
