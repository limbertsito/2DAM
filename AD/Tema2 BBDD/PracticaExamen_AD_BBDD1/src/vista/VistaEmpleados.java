/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.util.List;
import java.util.Scanner;
import modelo.Empleado;

/**
 *
 * @author limbert
 */
public class VistaEmpleados {
    //Declaramos el escaner
    private Scanner sc=new Scanner(System.in);
    
    //Mostramos el menu con las opciones
    //Y devolvemos el entero
    public int mostrarMenu(){
        System.out.println("=========MENU EMPLEADOS===========");
        System.out.println("1 .- Consultar todos los empleados");
        System.out.println("2 .-Consultar empleados por nif/dni");
        System.out.println("3 .- Consultar empleados que tenga un salario superior al introducido por el usuario");
        System.out.println("4 .-Consultar empleados que tengan un salario igual o inferior al introducido por el usuario.");
        System.out.println("0 .- Salir");
        
        try {
            return Integer.parseInt(sc.nextLine().trim());
            
        }catch(NumberFormatException e){
            //Si introduce un texto que no se ppuede parsear devolvemos un -1
            return -1; 
        }
    }
    //Metod para pedir el dni
    public String pediDNI(){
        System.out.println("Introduce tu dni: ");
        return sc.nextLine().trim();
    }
    //metood que se encargara que el valor introducido sea un double
    public double pedirSalario(){
        
        while(true){
           mostrarMensaje("Introduve el salario por el que quieres limitar :");
            
            try{
                //reeemplazamos las comas por puntos si el usuario mete comas en luagr d epuntos
                double valor = Double.parseDouble(sc.nextLine().trim().replace(",", "."));
                
                //comprobamso que el valor sea mayor a 0 antes de devolverlo
                if(valor>=0){
                    return valor;
                }
            }catch(NumberFormatException e){
            //Si falla la conversion , repite el bucle 
            }
            mostrarMensaje("Por favor, introdue un importe de valor numerico válido: ");
        }
    }
    
    //Pinta la lista de empleados devuelta 
    public void mostrarEmpleados(List<Empleado> lista){
        //Comprobamos que la lsita no este vacia
        if(lista.isEmpty()){
            mostrarMensaje("No se han encontrado que coincida con la busqueda");
            return;
        }
        System.out.println("\n------------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-20s %-10s\n", "DNI", "NOMBRE", "APELLIDOS", "SALARIO");
        System.out.println("------------------------------------------------------------------");
        
        for (Empleado empleado : lista) {
            
            System.out.printf("%-10s %-15s %-20s %-10.2f €\n ",
            empleado.getDni(),
            empleado.getNombre(),
            empleado.getApellidos(),
            empleado.getSalario()
            );
            
        }
    }
    
    //Muestra mensajes informativos o de error
    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }
    
    
}
