/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package ejercicio_componentes_di;
import java.util.Scanner;
/**
 *
 * @author limbe
 */
public class Empresa {
    String nif;
    String nombre;
    String telefono;
    String email;

    public Empresa(String nif, String nombre, String telefono, String email) {
        this.nif = nif;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNif() {
        return nif;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }
    
@Override
public String toString() {
    return this.nombre;
}
}
