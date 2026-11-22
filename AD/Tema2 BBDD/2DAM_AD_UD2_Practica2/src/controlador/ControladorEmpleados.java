/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controlador;
import java.util.Scanner;
import java.sql.*;
import java.util.List;
import modelo.ConsultaEmpleados;
import modelo.Empleado;
import vista.VistaEmpleados;
/**
 *
 * @author limbe
 */
public class ControladorEmpleados {
private final ConsultaEmpleados modelo;
    private final VistaEmpleados vista;

    // Recibe el modelo y la vista cuando se crea desde el Main
    public ControladorEmpleados(ConsultaEmpleados modelo, VistaEmpleados vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    // Bucle principal que mantiene el menú funcionando hasta pulsar 0
    public void iniciar() {
        int opcion;
        do {
            opcion = vista.mostrarMenu();

            try {
                switch (opcion) {
                    case 1:
                        // Pedimos todos al modelo y se los pasamos a la vista para pintarlos
                        List<Empleado> todos = modelo.consultarTodos();
                        vista.mostrarEmpleados(todos);
                        break;
                    case 2:
                        // Pedimos el DNI a la vista, buscamos en el modelo y mostramos
                        String dni = vista.pedirDni();
                        List<Empleado> porDni = modelo.consultarPorDni(dni);
                        vista.mostrarEmpleados(porDni);
                        break;
                    case 3:
                        double salMayor = vista.pedirSalario();
                        List<Empleado> superiores = modelo.consultarSalarioSuperior(salMayor);
                        vista.mostrarEmpleados(superiores);
                        break;
                    case 4:
                        double salMenor = vista.pedirSalario();
                        List<Empleado> inferiores = modelo.consultarSalarioIgualOInferior(salMenor);
                        vista.mostrarEmpleados(inferiores);
                        break;
                    case 0:
                        vista.mostrarMensaje("Saliendo del programa...");
                        break;
                    default:
                        vista.mostrarMensaje("Opción incorrecta, prueba otra vez.");
                }
            } catch (SQLException e) {
                //si algo fallaa lo motramos
                vista.mostrarMensaje("Error con la base de datos: " + e.getMessage());
            }

        } while (opcion != 0);
    }

}
