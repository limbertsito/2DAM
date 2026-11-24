/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.ConsultaEmpleados;
import modelo.Empleado;
import vista.VistaEmpleados;
import java.sql.SQLException;

/**
 *
 * @author limbert
 */
public class ControladorEmpleados {
    
    //El Controladro gestioan el tradico entre al vista y el modelo
    private ConsultaEmpleados modelo;
    private VistaEmpleados vista;

    //Constructor
    public ControladorEmpleados(VistaEmpleados vista, ConsultaEmpleados modelo) {
        this.modelo = modelo;
        this.vista = vista;
    }
    
    // Metod principal para iniciar el oprograma
    public void iniciar(){
    
        int opcion = -1;
        
        do {
           //1 .-Mostramos el menu y pedimos la opcion
            opcion = vista.mostrarMenu();
            
            //Capturamos cualquier excepcion del modelo
            try{
                ConsultaEmpleados con=new ConsultaEmpleados();
                switch (opcion) {
                    case 1 -> {
                        
                        
                        List<Empleado> listaTodos=con.consultarTodos();
                        vista.mostrarEmpleados(listaTodos);
                    }
                    case 2 ->{
                    String dni = vista.pediDNI();
                    List<Empleado> listaDni= con.consultarPorDni(dni);
                    
                    vista.mostrarEmpleados(listaDni);
                    }
                    case 3 ->{
                        double sal= vista.pedirSalario();
                    List<Empleado> listaSuperior= con.consultarSalarioSuperior(sal);
                    vista.mostrarEmpleados(listaSuperior);
                    
                    }
                    case 4 ->{
                    double sal=vista.pedirSalario();
                    List<Empleado>listaMenorOIgual= con.consultarIgualOMenorSalario(sal);
                    vista.mostrarEmpleados(listaMenorOIgual);
                    }
                    case 0 ->{vista.mostrarMensaje("Saliendo...");}
                    
                    default -> vista.mostrarMensaje("Opcion inválida");
                }
            
            }catch(SQLException e){
            vista.mostrarMensaje("Error al conectar con la base de datos");
            }
        } while (opcion!=0);
    }
}
