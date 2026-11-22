
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package practica4examenad;
import java.util.Scanner;
/**
 *
 * @author limbe
 */
public class Alumno {
    private int idAlumno;
    private String nombre;
    private String apellidos;
    private String ciclo;
    private int curso;
    private double notaMedia;

    public Alumno(int idAlumno, String nombre, String apellidos, String ciclo, int curso, double notaMedia) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ciclo = ciclo;
        this.curso = curso;
        this.notaMedia = notaMedia;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
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

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    @Override
    public String toString() {
        return "Alumno{" + "idAlumno=" + idAlumno + ", nombre=" + nombre + ", apellidos=" + apellidos + ", ciclo=" + ciclo + ", curso=" + curso + ", notaMedia=" + notaMedia + '}';
    }
    

  
    
}
