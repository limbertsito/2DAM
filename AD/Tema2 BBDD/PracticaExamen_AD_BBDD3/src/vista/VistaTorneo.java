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

    private Scanner sc = new Scanner(System.in);

    public int mostrarMenu() {

        System.out.println("======MENU TORNEO=====");
        System.out.println("1 .- Crear la base de datos.");
        System.out.println("2 .-Crear las tablas.");
        System.out.println("3 .-Cargar los datos iniciales");
        System.out.println("4 .-Modificar la estructura.");
        System.out.println("5 .-Borrar las tablas");
        System.out.println("0 .-Salir");

        try {
            System.out.println("Elige una opcion: ");
            //Devolvemos lo leido parseado
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            //retonamos -1 para trabjar con esto dentro del controlador
            System.out.println("Error el valor introducido tiene que ser entero");
            return -1;
        }

    }

    public void mostrarResultado(int filas, int fJugadores) {
        System.out.println("El numero de filas afectadas en la tabla Equipo es : " + filas);
        System.out.println("El numero de filas afectadas en  la tabla de jugaadores es "+fJugadores);
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    //Comprobacion antes de elimianar                                          
    public boolean pedirComprobacion() {

        while (true) {
            System.out.println("Estas seguro que quieres eliminar  ? (S/N)");

            String valor = sc.nextLine().trim();
            if (valor.equalsIgnoreCase("S")) {
                return true;
            } else if (valor.equalsIgnoreCase("N")) {

                return false;
            }
            System.out.println("Opcion invalida introduce S o N ");

        }
    }

    public void mostrarError(String err) {

        System.err.println(err);
    }

}
