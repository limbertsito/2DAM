/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.DriverManager;
import java.sql.*;

/**
 *
 * @author limbert.lovcub
 */
public class ConexionDB {

    String URL = "jdbc:mysql://localhost:3306/biblioteca";
    String USER = "root";
    String PASS = "1234";

    private Connection con = null;

    public Connection conectar() {
        try {
            con = DriverManager.getConnection(URL, USER, PASS);
            return con;
        } catch (SQLException e) {
            System.err.println("No se realiza la conexion");
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void comprobarConexion() {
        if (con != null) {
            System.out.println("Conexion realizada");
        } else {
            System.out.println("No se puede conectar");
        }
    }
    public void cerrar(){
   
        try{
        if(con != null && !con.isClosed()){
            con.close();
        }
        }catch(SQLException e){
            System.out.println("Error al conectar: "+e.getMessage());
            
        }
    }
}
