/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controlador;
import java.util.Scanner;
import modelo.GestorTorneo;
import vista.VistaTorneo;
import java.sql.SQLException;
/**
 *
 * @author limbe
 */
public class ControladorTorneo {
    
    private final GestorTorneo modelo;
    private final VistaTorneo vista;

    public ControladorTorneo(GestorTorneo modelo, VistaTorneo vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    public void iniciar() {
        int opcion;
        do {
            opcion = vista.mostrarMenu();

            try {
                switch (opcion) {
                    case 1:
                        modelo.crearBaseDatos();
                        vista.mostrarMensaje("Base de datos 'torneo' creada (si no existía) y seleccionada correctamente.");
                        break;

                    case 2:
                        modelo.crearTablas();
                        vista.mostrarMensaje("Tablas 'equipos' y 'jugadores' creadas correctamente.");
                        break;

                    case 3:
                        int[] filas = modelo.cargarDatosIniciales();
                        vista.mostrarMensaje("Carga de datos completada correctamente.");
                        vista.mostrarMensaje(" -> Filas insertadas en 'equipos': " + filas[0]);
                        vista.mostrarMensaje(" -> Filas insertadas en 'jugadores': " + filas[1]);
                        break;

                    case 4:
                        modelo.anadirColumnaEmail();
                        vista.mostrarMensaje("Columna 'email' añadida correctamente a la tabla 'jugadores'.");
                        break;

                    case 5:
                        if (vista.pedirConfirmacion("¿Seguro que deseas borrar todas las tablas?")) {
                            modelo.borrarTablas();
                            vista.mostrarMensaje("Tablas borradas correctamente.");
                        } else {
                            vista.mostrarMensaje("Operación cancelada por el usuario.");
                        }
                        break;

                    case 0:
                        modelo.cerrarConexion();
                        vista.mostrarMensaje("Saliendo de la aplicación...");
                        break;

                    default:
                        vista.mostrarMensaje("Opción incorrecta. Por favor, elige una opción del menú.");
                }
            } catch (SQLException e) {
                // Capturamos cualquier fallo (ej. crear tablas sin BD, duplicados, columna email ya existente...)
                vista.mostrarMensaje("Error en la operación SQL: " + e.getMessage());
            }

        } while (opcion != 0);
    }
}
