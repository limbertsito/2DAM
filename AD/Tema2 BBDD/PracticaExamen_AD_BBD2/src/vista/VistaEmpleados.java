/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;
import modelo.ConsultaEmpleados;
import modelo.Empleado;
import modelo.EmpleadoConDepartamento;

/**
 *
 * @author limbe
 */
public class VistaEmpleados {

    //Escaner
    private Scanner sc = new Scanner(System.in);
    ConsultaEmpleados ce= new ConsultaEmpleados();
    public int mostrarMenu() {
        
        System.out.println("=======MENU EMPLEADOS======");
        System.out.println("1 .- Insertar un nuevo empleado");
        System.out.println("2 .-Modificar el salario de un empleado a partir de su dni.");
        System.out.println("3 .-Eliminar un empleado a partir de su dni");
        System.out.println("4 .-Consultar los empleados junto con los datos de su departamento. ");
        System.out.println("5 .-Cambiar de departamento a un empleado.");
        System.out.println("0 .- Salir");
        
        try {
            //Devolvemos lo leido parseado
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error en el valor introducido debe ser entero");
            //retonamos -1 para trabjar con esto dentro del controlador
            return -1;
        }
        
    }

    public Empleado pedirEmpleado() throws SQLException {
        
        String dni = pedirString("introduce el dni :");
        String nombre = pedirString("Introduce el nombre del empleado: ");
        String apellidos = pedirString("intreoduce los apellidos: ");
        String puesto = pedirString("introduce el puesto : ");
        Date fecha_alta = pedirFecha();
        double salario = pedirSalario();
        Double comision = pedirComision();
        
        int id_departamento = pedirIdDepartamento();
        
        Empleado e = new Empleado(dni, nombre, apellidos, puesto, fecha_alta, salario, comision, id_departamento);
        return e;
    }

    //emtodo para pedir strings generico
    public String pedirString(String msg) {
        System.out.println(msg);
        return sc.nextLine().trim();
    }

    // Método para pedir y validar la fecha de alta del empleado
    public Date pedirFecha() {
        
        while (true) {
            System.out.println("Introduce la fecha de alta (AAAA-MM-DD): ");
            String entrada = sc.nextLine().trim();
            
            try {

                // 1. Convertimos el texto a LocalDate (espera el formato YYYY-MM-DD)
                LocalDate fechaLocal = LocalDate.parse(entrada);

                // 2. Convertimos el LocalDate al Date que necesita java.sql para JDBC
                // con el return salimos
                return Date.valueOf(fechaLocal);
                
            } catch (DateTimeParseException e) {
                System.out.println("formato de fecha incorrecto");
            }
        }
    }
    
    public double pedirSalario() {
        
        while (true) {
            System.out.println("Introduce el salario : ");
            
            try {
                //Usamos el replace por si el usuario mete comas
                //REPLACE
                double valor = Double.parseDouble(sc.nextLine().trim().replace(",", "."));

                // 2. Comprobamos que sea una cifra positiva
                if (valor > 0) {
                    return valor;
                }
                System.out.println("El salrio debe ser mayor que 0");
            } catch (NumberFormatException e) {
                System.out.println("Error debe de ser un double");
            }
        }
        
    }
    
    public Double pedirComision() {
        while (true) {
            System.out.println("introduce la comision");
            String valor = sc.nextLine().trim();

            // 1. Si el usuario pulsa Enter sin escribir nada, es un NULL de SQL
            if (valor.isEmpty()) {
                return null;
            }

            // 2. Si ha escrito algo, intentamos convertirlo a Double
            try {
                return Double.parseDouble(valor.replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Error el valor debe de ser double");
            }
            
        }
    }
    
    public int pedirIdDepartamento() {
        
        while (true) {
            System.out.println("Introduce el id del deopartamento (int)");
            
            try {
                return Integer.parseInt(sc.nextLine().trim());
                
            } catch (NumberFormatException e) {
                System.out.println("Error el valor debe de ser un entero");
            }
        }
    }

    public void mostrarEmpleado(List<Empleado> lista) {
        
        for (Empleado empleado : lista) {
            System.out.println(empleado);
        }
    }

    public void mostrarEmpleadoDepartamento(List<EmpleadoConDepartamento> lista) {
        for (EmpleadoConDepartamento empleadoConDepartamento : lista) {
            System.out.println(empleadoConDepartamento);
        }
    }
    
    public void mostrarNumeroFilas(int filas) {
        if (filas != 0) {
            System.out.println("Se ha relizado correctamente");
            System.out.println("Numero de filas afectadas " + filas + " filas.");
        } else {
            System.out.println("No se ha realizado correctamente");
            System.out.println("Numero de filas afectadas " + filas + " filas.");
        }
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public boolean pedirComprobacion() {
        
        while (true) {
            System.out.println("Estas seguro que quieres eliminar al empleado ? (S/N)");
            
            String valor = sc.nextLine().trim();
            if (valor.equalsIgnoreCase("S")) {
                return true;
            } else if (valor.equalsIgnoreCase("N")) {
                
                return false;
            }
            System.out.println("Opcion invalida introduce S o N ");
            
        }
    }
}
