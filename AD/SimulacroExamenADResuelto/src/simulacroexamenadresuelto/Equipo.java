/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package simulacroexamenadresuelto;
import java.util.Scanner;
/**
 *
 * @author limbe
 */
public class Equipo {
    
    //Atributos 
     private int numEquipo;
     private String tipo;
     private String marca;
     private String aula;
     private double precio;

        /**
         * constructor de la clase empleado
         * @param numEquipo
         * @param tipo
         * @param marca
         * @param aula
         * @param precio 
         */
    public Equipo(int numEquipo, String tipo, String marca, String aula, double precio) {
        this.numEquipo = numEquipo;
        this.tipo = tipo;
        this.marca = marca;
        this.aula = aula;
        this.precio = precio;
    }

    //Getters y setters
    public int getNumEquipo() {
        return numEquipo;
    }

    public void setNumEquipo(int numEquipo) {
        this.numEquipo = numEquipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Equipo{" + "numEquipo=" + numEquipo + ", tipo=" + tipo + ", marca=" + marca + ", aula=" + aula + ", precio=" + precio + '}';
    }
    
     
}
