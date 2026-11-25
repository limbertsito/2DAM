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
public class Jugador {
    
    private String nick;
    private String nombre;
    private int edad;
    private String rol;
    private int puntos;
    private int id_equipo;
    
    private String email;//Como nos pide añadir una  columna email la dejamos ya marcada
    
    public Jugador(String nick, String nombre, int edad, String rol, int puntos, int id_equipo) {
        this.nick = nick;
        this.nombre = nombre;
        this.edad = edad;
        this.rol = rol;
        this.puntos = puntos;
        this.id_equipo = id_equipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }
    //Si el mail aun no esta añadido es decir es null no se mostrara
  @Override
    public String toString() {
        return "Jugador [Nick=" + nick + ", Nombre=" + nombre + ", Edad=" + edad 
             + ", Rol=" + rol + ", Puntos=" + puntos + ", ID Equipo=" + id_equipo 
             + (email != null ? ", Email=" + email : "") + "]";
    }
    
}
