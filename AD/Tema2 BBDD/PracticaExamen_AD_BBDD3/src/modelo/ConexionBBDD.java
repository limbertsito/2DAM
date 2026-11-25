/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;
import java.sql.*;
/**
 *
 * @author limbe
 */
public class ConexionBBDD {
// Nos conectamos al servidor local SIN indicar ninguna BBDD concreta
    private final String URL= "jdbc:mysql://localhost:3306";
    private final String USER = "root";
    private final String PASS = "";
    
    
    // Devuelve la conexión y lanza SQLException para que la gestione el Controlador
    public Connection conectar()throws SQLException{
       return DriverManager.getConnection(URL,USER,PASS);
    }
}
