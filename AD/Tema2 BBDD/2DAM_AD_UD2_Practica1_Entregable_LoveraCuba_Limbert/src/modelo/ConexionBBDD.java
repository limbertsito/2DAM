/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.Connection;
import java.sql.*;
/**
 *
 * @author limbert.lovcub
 */
public class ConexionBBDD {
    String URL = "jdbc:mysql://localhost:3306/biblioteca";
    String USER = "root";
    String PASS = "1234";
    
    //VistaEmpleados vista = new VistaEmpleados();
    
    private Connection con = null;
    
    public Connection conectar(){
        
        try {
            con = DriverManager.getConnection(URL,USER,PASS);
            System.out.println("Conectado con exito!!");
            return con;
            
            
        }catch (SQLException e){
        
            System.err.println(e.getMessage());
            return null;
        }
        
        
    }
    public void cerrar() throws SQLException{
        try{
            // &&: debe existir yt seguir abierta para cerrarla 
            if(con != null && !con.isClosed()){
            con.close();
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        finally{
            con.close();
        }
    }
   
}
