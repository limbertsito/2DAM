/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;
import java.util.Scanner;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author limbe
 */
public class ConsultaEmpleados {

    // 1. Consulta todos los empleados, sin aprametros usamos statements
    public List<Empleado> consultarTodos() throws SQLException {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT dni, nombre, apellidos, salario FROM empleados ORDER BY apellidos, nombre";

        ConexionBBDD bd = new ConexionBBDD();
        // El try(...) cierra solo la conexión, la sentencia y el resultado al terminar
        try (Connection conexion = bd.conectar(); Statement sentencia = conexion.createStatement(); ResultSet resultado = sentencia.executeQuery(sql)) {

            // Mientras haya filas en el resultado, las vamos leyendo una a una
            while (resultado.next()) {
                Empleado emp = new Empleado(
                        resultado.getString("dni"),
                        resultado.getString("nombre"),
                        resultado.getString("apellidos"),
                        resultado.getDouble("salario")
                );
                lista.add(emp); // Guardamos el empleado en la lista
            }
        }
        return lista;
    }

    // 2. Consulta un empleado por su NIF/DNI (lleva filtro '?', usamos PreparedStatement)
    public List<Empleado> consultarPorDni(String dni) throws SQLException {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT dni, nombre, apellidos, salario FROM empleados WHERE dni = ?";

        ConexionBBDD bd = new ConexionBBDD();
        try (Connection conexion = bd.conectar(); PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            // Cambiamos el primer '?' (posición 1) por el DNI que escribió el usuario
            sentencia.setString(1, dni);

            // Ejecutamos la consulta y recorremos el resultado
            try (ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    Empleado emp = new Empleado(
                            resultado.getString("dni"),
                            resultado.getString("nombre"),
                            resultado.getString("apellidos"),
                            resultado.getDouble("salario")
                    );
                    lista.add(emp);
                }
            }
        }
        return lista;
    }

    // 3. Consulta empleados con salario mayor al indicado por el usuario
    public List<Empleado> consultarSalarioSuperior(double salarioLimite) throws SQLException {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT dni, nombre, apellidos, salario FROM empleados WHERE salario > ? ORDER BY salario DESC";

        ConexionBBDD bd = new ConexionBBDD();
        try (Connection conexion = bd.conectar(); PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            // Cambiamos el '?' por el número decimal del salario
            sentencia.setDouble(1, salarioLimite);

            try (ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    Empleado emp = new Empleado(
                            resultado.getString("dni"),
                            resultado.getString("nombre"),
                            resultado.getString("apellidos"),
                            resultado.getDouble("salario")
                    );
                    lista.add(emp);
                }
            }
        }
        return lista;
    }

    // 4. Consulta empleados con salario igual o menor al indicado por el usuario
    public List<Empleado> consultarSalarioIgualOInferior(double salarioLimite) throws SQLException {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT dni, nombre, apellidos, salario FROM empleados WHERE salario <= ? ORDER BY salario DESC";

        ConexionBBDD bd = new ConexionBBDD();
        try (Connection conexion = bd.conectar(); PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setDouble(1, salarioLimite);

            try (ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    Empleado emp = new Empleado(
                            resultado.getString("dni"),
                            resultado.getString("nombre"),
                            resultado.getString("apellidos"),
                            resultado.getDouble("salario")
                    );
                    lista.add(emp);
                }
            }
        }
        return lista;
    }

}
