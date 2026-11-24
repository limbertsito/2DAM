/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author limbert
 */
public class Empleado {
    
    //Atributos de la clase empleado que consultaremos mas tarde
    //Recogeremos los que leugo trabajremos con ellos
    private String dni;
    private String nombre;
    private String apellidos;
    private double salario;

    /**
     * Constructor que nos valdra para instanciar a los empleados
     * @param dni
     * @param nombre
     * @param apellidos
     * @param salario 
     */
    public Empleado(String dni, String nombre, String apellidos, double salario) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.salario = salario;
    }

    //Getters y setters para poder trabajar con los atributos
    
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
    
    
    
          
}
