/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.sql.*;
        /**
 *
 * @author limbe
 */
public class Empleado {
    
    //atributos de la classe , caundo se nos pida insertar automaticamente usaremos rtodos
    private String dni;
    private String nombre;
    private String apellidos;
    private String puesto;
    private Date fecha_alta;
    private double salario;
    //Si el dato ppuede ser nullo tenemso que llamar a la aclase no al dato primitivo 
    //Y auq ela clase si admite nulos
    private Double comision;
    private int id_departamento;

    //Constructor para instanciar empelados
    public Empleado(String dni, String nombre, String apellidos, String puesto, Date fecha_alta, double salario, Double comision, int id_departamento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.puesto = puesto;
        this.fecha_alta = fecha_alta;
        this.salario = salario;
        this.comision = comision;
        this.id_departamento = id_departamento;
    }
    //getters y ssetters para trabjar con los atrbutpos
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

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Double getComision() {
        return comision;
    }

    public void setComision(Double comision) {
        this.comision = comision;
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    @Override
    public String toString() {
        return "Empleado{" + "dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", puesto=" + puesto + ", fecha_alta=" + fecha_alta + ", salario=" + salario + ", comision=" + comision + ", id_departamento=" + id_departamento + '}';
    }
    
    
}
