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
public class Empleado {
    
    // Atributos de empleados, cogeremos solo los que necesitamos
    private String dni;
    private String nombre;
    private String apellidos;
    private double salario;
    
    //Cremaso el contructor

    public Empleado(String dni, String nombre, String apellidos, double salario) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.salario = salario;
    }
    //getters y setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    /**
     * para mostrar la infoprmacion de los empleados
     * @return 
     */
    @Override
    public String toString() {
        return "Empleado{" + "dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", salario=" + salario + '}';
    }
    
    
}
