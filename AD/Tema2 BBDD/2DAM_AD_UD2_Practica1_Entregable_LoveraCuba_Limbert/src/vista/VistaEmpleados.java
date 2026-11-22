/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.util.Scanner;

/**
 *
 * @author limbert.lovcub
 */
public class VistaEmpleados {
    private static Scanner sc = new Scanner(System.in);
    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }
    public void mostrarError(String error){
        System.err.println(error);
    }
//    public void mostrarLibros(ArrayList<Empleado> listaEmpleado{
//    
//        
//    }
    public void mostrarMenu(){
        System.out.println("1.Mostrar todos los empleados");
        System.out.println("2.consultar empleados por dni : ");
        System.out.println("3.Consultar empleados que tengan un salario superior al introducido por el usuario");
        System.out.println("4.Consultar empleados que tengan un salario igual o inferior al introducido por el usuario");
    }
    public int pedirEntero(String msg){
        while(true){
            System.out.println(msg);
            try{
                return Integer.parseInt(sc.nextLine().trim());
                
            }catch(NumberFormatException e){
                mostrarError("Entrada no válida(introduce un entero). ");
            }
        }
    }
}
