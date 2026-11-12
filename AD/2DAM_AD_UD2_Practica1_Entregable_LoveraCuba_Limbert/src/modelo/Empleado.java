/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author limbert.lovcub
 */
public class Empleado {
    
    private String dni;
    private double salario;
    private String nombre;
    private String apellidos;
    private String puesto;

    public Empleado(String dni, double salario, String nombre, String apellidos, String puesto) {
        this.dni = dni;
        this.salario = salario;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.puesto = puesto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
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

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public String toString() {
        return "Empleado{" + "dni=" + dni + ", salario=" + salario + ", nombre=" + nombre + ", apellidos=" + apellidos + ", puesto=" + puesto + '}';
    }
    
    
}
