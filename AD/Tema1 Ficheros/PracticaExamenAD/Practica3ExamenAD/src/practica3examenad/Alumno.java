/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package practica3examenad;
import java.io.Serializable;
import java.util.Scanner;
/**
 *
 * @author limbe
 */
public class Alumno implements Serializable{

    private static long serialVersionUID=1L;
    private String nombre;
    private int nota;

    public Alumno(String nombre, int nota) {
        super();
        this.nombre = nombre;
        this.nota = nota;
    }

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
    
    
}
