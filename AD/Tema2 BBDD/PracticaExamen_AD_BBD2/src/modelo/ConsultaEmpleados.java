/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author limbe
 */
public class ConsultaEmpleados {

    /**
     *
     * Metodo que nos deovlera 1 si la fila ha insertado bine y 0 sino
     *
     * a) Insertar un nuevo empleado. Se pedirán todos sus datos (dni, nombre,
     * apellidos, puesto, fecha de alta, salario, comisión y departamento). La
     * comisión es opcional: si el usuario no introduce nada, se guardará como
     * NULL.
     *
     * @param e
     * @return
     * @throws SQLException
     */
    public int insertarEmpleado(Empleado e) throws SQLException {

        //LA sentencia sql
        String sql = "INSERT INTO empleados (dni,nombre,apellidos,puesto,fecha_alta,salario,comision,id_departamento)"
                + "VALUES(?,?,?,?,?,?,?,?)";

        //La comision es opcional recordar
        ConexionBBDD bd = new ConexionBBDD();

        try (Connection con = bd.conectar(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, e.getDni());
            ps.setString(2, e.getNombre());
            ps.setString(3, e.getApellidos());
            ps.setString(4, e.getPuesto());
            ps.setDate(5, e.getFecha_alta());
            ps.setDouble(6, e.getSalario());

            //Majemaos el campo opcional
            //
            if (e.getComision() == null) {
                ps.setNull(7, java.sql.Types.DECIMAL);
            } else {
                ps.setDouble(7, e.getComision());
            }
            ps.setInt(8, e.getId_departamento());

            // Devuelve 1 si se ha insertado con éxito
            return ps.executeUpdate();
        }

    }

    /**
     * b) Modificar el salario de un empleado a partir de su dni.
     *
     * @param dni
     * @param nuevoSalario
     * @return
     * @throws SQLException
     */
    public int modificarSalario(String dni, double nuevoSalario) throws SQLException {

        //PReapramos la sentencia
        String sql = "UPDATE empleados SET salario = ? WHERE dni = ?";

        ConexionBBDD bd = new ConexionBBDD();

        try (Connection con = bd.conectar(); PreparedStatement ps = con.prepareStatement(sql);) {
            //aajustamos los parametros recibidos en los ?

            ps.setDouble(1, nuevoSalario);
            ps.setString(2, dni);

            //Devolvemos el numero de filas afectadas
            return ps.executeUpdate();
        }
    }

    /**
     * c) Eliminar un empleado a partir de su dni. Antes de borrarlo se
     * mostrarán sus datos y se pedirá confirmación al usuario (S/N).
     *
     * @param dni
     * @return
     * @throws SQLException
     */
    //Estara condicionado con el booleano que se tratara en el controlador para ver si se ejecuta o no
    public int eliminarEmpleados(String dni) throws SQLException {

        //Preparamos la sentencia de biorrado
        String sql = "DELETE FROM empleados WHERE dni = ?";

        ConexionBBDD bd = new ConexionBBDD();

        try (Connection con = bd.conectar(); PreparedStatement ps = con.prepareStatement(sql);) {
            //Metemos el parametro en el ?
            ps.setString(1, dni);

            //retornamos el numeor de filas afectadas
            return ps.executeUpdate();
        }
    }

    /**
     * d) Consultar los empleados junto con los datos de su departamento. Para
     * ello se realizará un INNER JOIN entre empleados y departamentos,
     * mostrando como mínimo: dni, nombre, apellidos, puesto, nombre del
     * departamento y localidad.
     *
     * @return
     * @throws SQLException
     */
    public List<EmpleadoConDepartamento> consultarEmpleadoDepartamento() throws SQLException {
        //Preparamos la lista que ahyq eu devolver
        List<EmpleadoConDepartamento> lista = new ArrayList<>();
        //Preparamos la sentencia
        //Hay que dejar espacios al final o al principio cuando concatenamos con +
        String sql = "SELECT e.dni,e.nombre,e.apellidos,e.puesto,e.fecha_alta,e.salario,e.comision,d.nombre AS nombre_dpto,d.localidad "
                + "FROM empleados e "
                + "INNER JOIN departamentos d ON d.id_departamento = e.id_departamento "
                + "ORDER BY e.nombre ";
        ConexionBBDD bd = new ConexionBBDD();

        try (Connection con = bd.conectar(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                //Si nos pidiesen el valor nulo
                // 1. Leemos el valor
                //    double comisionLeida = rs.getDouble("comision"); //variable auxiliar
                //    
                //2. Comprobamos si MySQL devolvió NULL
                //    Double comision = rs.wasNull() ? null : comisionLeida;
                //Creamos la clase  pivote que nos reiba el nuevo empleado con departamento para mostrarlo
                EmpleadoConDepartamento emp = new EmpleadoConDepartamento(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("puesto"),
                        rs.getString("nombre_dpto"), // Alias del SQL
                        rs.getString("localidad")
                );
                lista.add(emp);
            }
        }
        return lista;
    }

    /**
     * e) Cambiar de departamento a un empleado. Se pedirá el dni del empleado y
     * el nuevo id_departamento. Antes de realizar el UPDATE sobre la tabla
     * empleados, se comprobará mediante una consulta que el id_departamento
     * introducido existe en la tabla departamentos. Si no existe, no se
     * realizará la modificación y se informará al usuario.
     */
    public int cambiarDepartamentoEmpleado(String dni, int nuevo_id_departamento) throws SQLException {
        //LLamamos al metodo para comprobar si existe el neuvo departaemnto dentro de departamentos
        if (!comprobarDepartamento(nuevo_id_departamento)) {
           
            
            return 0;
        } else {
            //Preparamos la sentencia 
            String sql = "UPDATE empleados SET id_departamento = ?  WHERE dni = ?";

            ConexionBBDD bd = new ConexionBBDD();

            try (Connection con = bd.conectar();
                PreparedStatement ps = con.prepareStatement(sql);) {
                   
                //añadimos al  ? el valor recibido
                ps.setInt(1, nuevo_id_departamento);
                ps.setString(2, dni);
                //retornamos el numero de filas cambiadas
                return ps.executeUpdate();
            }
        }

    }

    public boolean comprobarDepartamento(int id_departamento_nuevo) throws SQLException {

        String sql = "SELECT id_departamento FROM departamentos WHERE id_departamento = ? ";

        ConexionBBDD bd = new ConexionBBDD();

        try (Connection con = bd.conectar(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setInt(1, id_departamento_nuevo);
            //dentro de try otra vez para el preparedStatemente
            try (ResultSet rs = ps.executeQuery()) {
                //devolvemos si lo ha encontrado o no
                return rs.next();
            }

        }
    }

}
