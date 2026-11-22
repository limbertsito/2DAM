/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg2dam_ad_ud2_practica1_entregable_loveracuba_limbert;

import modelo.ConexionBBDD;

/**
 *
 * @author limbert.lovcub
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ConexionBBDD con = new ConexionBBDD();
        con.conectar();
    }
    
}
