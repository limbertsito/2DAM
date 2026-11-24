/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo;
//EL DATE DE SQL NO ES EL MISMO QQUE JAVA
import java.sql.*;
/**
 *
 * @author limbe
 */
public class ConexionBBDD {
    
    private final String URL= "jdbc:mysql://localhost:3306/empresa";
    private final String USER = "root";
    private final String PASS="";
    
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
