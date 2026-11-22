/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg2dam_ad_ud2_practica3;

import controlador.ControladorTorneo;
import modelo.GestorTorneo;
import vista.VistaTorneo;

/**
 *
 * @author limbe
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        GestorTorneo modelo = new GestorTorneo();
        VistaTorneo vista = new VistaTorneo();
        ControladorTorneo controlador = new ControladorTorneo(modelo, vista);

        controlador.iniciar();
    }
    
    
}
