/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author limbert
 */
public class ConsultaEmpleados {

    // a) consultar todos los empleados
    //Como es fija usaremos statement
    public List<Empleado> consultarTodos() throws SQLException {

        //1 Preparamos la lista vacia para guardar los objetos Empleado
        List<Empleado> lista = new ArrayList<>();

        //Escribimos la sentencia que vamos a usar
        String sql = "SELECT dni,nombre,apellidos,salario FROM empleados ORDER BY apellidos,nombre";

        ConexionBBDD bd = new ConexionBBDD();

        /**
         * EL bloque try w resources abre conexion, crea el statement y ejecuta
         * la consulta, al temrinar se cierra en orden inverso automaticamente
         */
        try (Connection con = bd.conectar(); Statement sentencia = con.createStatement(); ResultSet resultado = sentencia.executeQuery(sql)) {

            //4.-  Recorremos el resutlado fila por fila
            while (resultado.next()) {

                //Extraemos el valor de cada columna de la fila actual en mysql
                String dni = resultado.getString("dni");
                String nombre = resultado.getString("nombre");
                String apellidos = resultado.getString("apellidos");
                double salario = resultado.getDouble("salario");

                //Instanciamos el objeto Empleado y lo añadimos a la lista
                Empleado emp = new Empleado(dni, nombre, apellidos, salario);

                //lo añadimos
                lista.add(emp);
            }
        }
        //5.- Devolvemos la lista llena de empleados al controlador
        return lista;
    }

    /**
     * Metodo que nos permitira Consultar un empleado por su dni
     *
     * @param dni Recibe el dni paara consultarlo
     * @return
     * @throws SQLException Lanzamos la excepcon en caso de no poder Realizar la
     * consulta
     */
    public List<Empleado> consultarPorDni(String dni) throws SQLException {

        List<Empleado> lista = new ArrayList<>();

        //el ? es el hueco dond insertaremos el dato que recibimos como parameto por el que buscamos
        String sql = "SELECT dni,nombre,apellidos,salario FROM empleados WHERE dni = ?";

        //instanciamos un objeto apra realizar la conexion
        ConexionBBDD bd = new ConexionBBDD();

        //Abrirmos la conexion segura con resources y preparamos la sentencia
        try (Connection con = bd.conectar(); PreparedStatement sentencia = con.prepareStatement(sql);) {

            //asignamos el parametro dni al marcador 1
            sentencia.setString(1, dni);

            //Ejecutamso la consultapasandole al resulSet
            try (ResultSet resultado = sentencia.executeQuery()) {

                while (resultado.next()) {

                    Empleado emp = new Empleado(
                            resultado.getString("dni"),
                            resultado.getString("nombre"),
                            resultado.getString("apellidos"),
                            resultado.getDouble("salario")
                    );
                    //añadimso los empleados
                    lista.add(emp);
                }

            }
        }
        return lista;
    }

    //Operación C : Consultar empleados con salario superior al indicado
    public List<Empleado> consultarSalarioSuperior(double salarioLimite) throws SQLException {

        //Preapramso la lsita que devolveremos 
        List<Empleado> lista = new ArrayList<>();

        //Preapramos la sentencia
        String sql = "SELECT dni,nombre,apellidos,salario FROM empleados WHERE salario > ?";

        //Instanciamos la conexion
        ConexionBBDD bd = new ConexionBBDD();

        try (Connection con = bd.conectar(); PreparedStatement sentencia = con.prepareStatement(sql);) {

            //Asiganmso el parametro al recibido el double salario
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

    //Operacion D: consultar empleados que tengan el salario igual o inferior 
    public List<Empleado> consultarIgualOMenorSalario(Double salario) throws SQLException {

        //Preparamos la lista 
        List<Empleado> lista = new ArrayList<>();

        //Preparamos a sentencia
        String sql = "SELECT dni,nombre,apellidos,salario FROM empleados WHERE salario <= ?";

        //instanciamos la conexion
        ConexionBBDD bd = new ConexionBBDD();

        try (Connection con = bd.conectar(); PreparedStatement sentencia = con.prepareStatement(sql)) {

            //Asignamso el valor recibido por parametro a la interrogacion
            sentencia.setDouble(1, salario);

            //Ejecutamos la sentencia aplicando ya el salio introducido en luagr de ?
            try (ResultSet resultado = sentencia.executeQuery()) {

                //Recorremos la base de datos filtrando y recogiendo los empleados que coincidan
                // con nuestra condicion
                while (resultado.next()) {

                    //Recoigemos el empleaod
                    Empleado emp = new Empleado(
                            resultado.getString("dni"),
                            resultado.getString("nombre"),
                            resultado.getString("apellidos"),
                            resultado.getDouble("salario")
                    );
                    lista.add(emp);
                }

            }
        }        //Retornamos la list
        return lista;

    }
}
