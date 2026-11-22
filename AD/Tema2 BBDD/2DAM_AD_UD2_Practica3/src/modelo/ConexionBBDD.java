/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;
import java.util.Scanner;
import java.sql.*;
/**
 *
 * @author limbe
 */
public class ConexionBBDD {
    
    private final String URL = "jdbc:mysql://localhost:3306/";
    private final String USER = "root";
    private final String PASS = ""; // En XAMPP suele estar vacía
    
    // Abre y devuelve la conexión con el servidor MySQL
    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
