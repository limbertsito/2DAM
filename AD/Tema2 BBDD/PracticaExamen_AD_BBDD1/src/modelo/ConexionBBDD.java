/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.*;
/**
 *
 * @author limbert
 */
public class ConexionBBDD {
    //La url de la base de datos
    private final String URL = "jdbc:mysql://localhost:3306/empresa";
    private final String USER = "root"; //como es local usamos el usuario raiz
    private final String PASS= ""; //Contraseñ por defecto en xamp esta vacia
    
    /**
     * Este metodo
     * @return devuelve un objeto Connection lsito apra enviar sentencias SQL
     * @throws SQLException PAra lanzar el error en caso de error al conectar con el servidor
     */
    public Connection conectar() throws SQLException{
        
    // DriverManager.getConnection() busca el Driver de MySQL en las librerías 
    // del proyecto y establece el canal de comunicación.
    return DriverManager.getConnection(URL,USER,PASS);
    }
}
