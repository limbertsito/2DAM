/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.SQLException;
import java.util.Scanner;
import modelo.GestorTorneo;
import vista.VistaTorneo;

/**
 *
 * @author limbe
 */
public class ControladorTorneo {

    //Importamos la vista y el modelo
    private GestorTorneo gestor;
    private VistaTorneo vista;

    //Constructor para instanciarlos desde el main
    public ControladorTorneo(GestorTorneo gestor, VistaTorneo vista) {
        this.gestor = gestor;
        this.vista = vista;
    }

    public void iniciar() {

        int opcion = -1;

        do {
            //1 .-Mostramos el menu y pedimos la opcion
            opcion = vista.mostrarMenu();

            //Capturamos cualquier excepcion del modelo
            try {

                switch (opcion) {
                    case 1 -> {
                        gestor.crearBaseDatos();
                    }
                    case 2 -> {
                        gestor.crearTablaEquipos();
                        gestor.crearTablaJugadores();

                    }

                    case 3 -> {
                        int fEquipo = gestor.insertarDatosInicialesEquipos();
                        int fJugadores = gestor.insertarInicialesJugadores();
                        vista.mostrarResultado(fEquipo, fJugadores);
                    }

                    case 4 -> {
                        gestor.añadirEmailJugadores();
                    }

                    case 5 -> {
                        boolean seguro = vista.pedirComprobacion();
                        if (seguro) {
                            gestor.borrarTablaJugadore();
                            gestor.borrarTablaEquipo();
                        }
                    }

                    case 0 -> {
                        vista.mostrarMensaje("Saliendo...");
                    }
                    default -> {
                        vista.mostrarError("Opcion invalida");
                    }
                }
            } catch (SQLException ex) {
                vista.mostrarError("Error al trabjar conn la base de datos : " + ex.getMessage());
            }

        } while (opcion != 0);
    }

}
