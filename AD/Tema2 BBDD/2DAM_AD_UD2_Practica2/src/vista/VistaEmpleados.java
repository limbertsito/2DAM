/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package vista;
import java.util.Scanner;
import java.sql.*;
import java.util.List;
import modelo.Empleado;
/**
 *
 * @author limbe
 */
public class VistaEmpleados {
    
    private final Scanner teclado = new Scanner(System.in);
    
    /**
     * Mostramos el menu y
     * @return devolvemos el numeor queelige el usuario
     */
    public int mostrarMenu() {
        System.out.println("\n=== MENÚ GESTIÓN DE EMPLEADOS ===");
        System.out.println("1. Consultar todos los empleados");
        System.out.println("2. Consultar empleado por NIF/DNI");
        System.out.println("3. Consultar empleados con salario superior a un importe");
        System.out.println("4. Consultar empleados con salario igual o inferior a un importe");
        System.out.println("0. Salir");

        // Repetimos hasta que escriba un número entero válido
        while (true) {
            System.out.print("Elige una opción: ");
            try {
                // Leemos toda la linea con nextLine() 
                //para no tener que limpiar el buffer y sse quede atascasdo el enter
                return Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Tienes que escribir un número.");
            }
        }
    }

    // Pedimos el dni y lo limpiamos y en maysuucla
    public String pedirDni() {
        System.out.print("Introduce el NIF/DNI: ");
        return teclado.nextLine().trim().toUpperCase();
    }

    // pedimos el salariop el positivo
    public double pedirSalario() {
        while (true) {
            System.out.print("Introduce el salario: ");
            try {
                // Cambiamos coma por punto por si el usuario escribe decimales con coma (ej: 1500,50)
                double valor = Double.parseDouble(teclado.nextLine().trim().replace(',', '.'));
                if (valor >= 0) {
                    return valor;
                }
                System.out.println("El salario no puede ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Error: Escribe un número válido.");
            }
        }
    }

    // Recorre la lista de empleados recibida y los muestra por consola
    public void mostrarEmpleados(List<Empleado> lista) {
        if (lista.isEmpty()) {
            System.out.println("No se han encontrado empleados.");
            return;
        }

        System.out.println("\n--- EMPLEADOS ENCONTRADOS ---");
        for (Empleado emp : lista) {
            System.out.println("DNI: " + emp.getDni()
                    + " | Nombre: " + emp.getNombre() + " " + emp.getApellidos()
                    + " | Salario: " + emp.getSalario() + " €");
        }
    }

    // Muestra mensajes sueltos (avisos, despedida o errores)
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
