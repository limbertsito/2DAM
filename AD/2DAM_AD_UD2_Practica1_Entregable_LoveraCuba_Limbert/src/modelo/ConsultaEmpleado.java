/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import vista.VistaEmpleados;
import java.sql.*;
/**
 *
 * @author limbert.lovcub
 */
public class ConsultaEmpleado {
    
    VistaEmpleados vista = new VistaEmpleados();
    ConexionBBDD conexion = new ConexionBBDD();
    
    public ArrayList<Empleado> consultarTodosEmpleados(){
    
        ArrayList<Empleado> listaEmpleados = new ArrayList<>();
        
        Connection con = conexion.conectar();
        return listaEmpleados;
    }
}
