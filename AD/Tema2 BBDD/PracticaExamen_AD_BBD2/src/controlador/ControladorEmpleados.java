/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controlador;
import java.sql.*;
import java.util.List;
import modelo.ConsultaEmpleados;
import modelo.Empleado;
import modelo.EmpleadoConDepartamento;
import vista.VistaEmpleados;
/**
 *
 * @author limbe
 */
public class ControladorEmpleados {

    //Importamos la vista y el modelo
    private ConsultaEmpleados modelo;
    private VistaEmpleados vista;

    //Constructor para instanciarlos desde el main
    public ControladorEmpleados(ConsultaEmpleados modelo, VistaEmpleados vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    
    public void inciar(){
    
        int opcion = -1;
        
        do {
             //1 .-Mostramos el menu y pedimos la opcion
            opcion = vista.mostrarMenu();
            
             //Capturamos cualquier excepcion del modelo
             try{
                 ConsultaEmpleados consulta= new ConsultaEmpleados();
                 switch (opcion) {
                     case 1 -> {
                         Empleado emp= vista.pedirEmpleado();
                         int filas= consulta.insertarEmpleado(emp);
                         vista.mostrarNumeroFilas(filas);
                     }
                     
                     case 2 -> {
                         String dni=vista.pedirString("introduce tu DNI:");
                         double salario= vista.pedirSalario();
                         int filas = consulta.modificarSalario(dni, salario);
                         vista.mostrarNumeroFilas(filas);
                     }
                     
                     case 3 -> {
                         String dni = vista.pedirString("introduce tu DNI");
                         boolean comprobacion= vista.pedirComprobacion();
                         int filas ;
                         if(!comprobacion){
                         vista.mostrarNumeroFilas(filas=0);
                         }else{
                         filas=consulta.eliminarEmpleados(dni);
                         vista.mostrarNumeroFilas(filas);
                         }
                     }
                     
                     case 4 -> {
                         List<EmpleadoConDepartamento>lista= consulta.consultarEmpleadoDepartamento();
                         vista.mostrarEmpleadoDepartamento(lista);
                     }
                     
                     case 5 -> {
                         String dni = vista.pedirString("introduce el dni del empkeado que quiieres cambiar el departamento");
                         int id_departamentoNuevo=vista.pedirIdDepartamento();
                         int filas = consulta.cambiarDepartamentoEmpleado(dni, id_departamentoNuevo);
                         vista.mostrarNumeroFilas(filas);
                     }
                     
                     case 0 -> {vista.mostrarMensaje("Saliendo...");}
                     default -> {vista.mostrarMensaje("La entrada no es una opcion valida.");}
                 }
                 
             }catch(SQLException e){
                 System.out.println("Error al trabajar con la base de datos :"+e.getMessage());
             }
        
        
        } while (opcion!=0);
    
    
    }
    
}
