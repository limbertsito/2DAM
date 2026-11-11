/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ad_tema2_practica1;

import modelo.ConexionDB;

/**
 *
 * @author limbert.lovcub
 */
public class AD_Tema2_Practica1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ConexionDB con = new ConexionDB();
        con.conectar();
        con.comprobarConexion();
    }
    
}
