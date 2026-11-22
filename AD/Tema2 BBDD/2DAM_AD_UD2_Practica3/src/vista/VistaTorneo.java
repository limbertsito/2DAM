/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package vista;
import java.util.Scanner;
/**
 *
 * @author limbe
 */
public class VistaTorneo {

    private final Scanner teclado = new Scanner(System.in);
    // Mostramos ekl menu y deolvmeos el numeor elegido
    public int mostrarMenu() {
        System.out.println("\n=== GESTIÓN DEL TORNEO DE ESPORTS ===");
        System.out.println("1. Crear la base de datos 'torneo'");
        System.out.println("2. Crear las tablas 'equipos' y 'jugadores'");
        System.out.println("3. Cargar los datos iniciales");
        System.out.println("4. Modificar estructura (añadir columna email a jugadores)");
        System.out.println("5. Borrar las tablas");
        System.out.println("0. Salir");

        while (true) {
            System.out.print("Elige una opción: ");
            try {
                return Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Introduce un número válido.");
            }
        }
    }

    // Pide confirmación S/N antes de borrar las tablas
    public boolean pedirConfirmacion(String mensaje) {
        while (true) {
            System.out.print(mensaje + " (S/N): ");
            String respuesta = teclado.nextLine().trim().toUpperCase();
            if (respuesta.equals("S")) {
                return true;
            }
            if (respuesta.equals("N")) {
                return false;
            }
            System.out.println("Por favor, responde S o N.");
        }
    }

    // Muestra mensajes de información o error
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
