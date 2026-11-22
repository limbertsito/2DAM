/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg2dam_ad_ud2_practica2;

import controlador.ControladorEmpleados;
import modelo.ConsultaEmpleados;
import vista.VistaEmpleados;

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
        // Creamos el modelo, la vista y el controlador
        ConsultaEmpleados modelo = new ConsultaEmpleados();
        VistaEmpleados vista = new VistaEmpleados();
        ControladorEmpleados controlador = new ControladorEmpleados(modelo, vista);

        // Arrancamos el programa
        controlador.iniciar();
    }
    
}
