/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practicaexamen_ad_bbdd3;

import controlador.ControladorTorneo;
import modelo.GestorTorneo;
import vista.VistaTorneo;

/**
 *
 * @author limbe
 */
public class PracticaExamen_AD_BBDD3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        VistaTorneo vista = new VistaTorneo();
        GestorTorneo gestor = new GestorTorneo();
        ControladorTorneo controlador = new ControladorTorneo(gestor, vista);
        controlador.iniciar();
    }
    
}
