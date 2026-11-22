/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.*;

/**
 *
 * @author limbe
 */
public class ConexionBBDD {
    
    // Datos para conectar con nuestra base de datos 'empresa' en local
    private final String URL = "jdbc:mysql://localhost:3306/empresa";
    private final String USER = "root";
    private final String PASS = ""; // En XAMPP la contraseña viene vacía por defecto
    
    // Abre la conexión y se la entrega a quien la pida
    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
