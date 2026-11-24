/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practicaexamen_ad_bbdd1;

import controlador.ControladorEmpleados;
import modelo.ConsultaEmpleados;
import vista.VistaEmpleados;

/**
 *
 * @author limbert
 */
public class PracticaExamen_AD_BBDD1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        VistaEmpleados vista = new VistaEmpleados();
        ConsultaEmpleados consulta = new ConsultaEmpleados();
        ControladorEmpleados controlador = new ControladorEmpleados(vista,consulta);
        
        controlador.iniciar();
    }
    
}
