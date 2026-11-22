/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package practica4cexamenad;
import java.util.Scanner;
/**
 *
 * @author limbe
 */
public class Empleados {
    
    private String nif;
    private String nombre;
    private String apellidos;
    private double salario;

    public Empleados(String nif, String nombre, String apellidos, double salario) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.salario = salario;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
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

    @Override
    public String toString() {
        return "Empleados{" + "nif=" + nif + ", nombre=" + nombre + ", apellidos=" + apellidos + ", salario=" + salario + '}';
    }
    
    
}
