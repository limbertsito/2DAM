/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica4cexamenad;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author limbe
 */
public class Practica4CExamenAD {
final static Scanner SC  =new Scanner (System.in);
static  List<Empleados> listaEmpleados = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here


        if (!GestionEmpleados.comprobarDirectorioDatos()) {
            return;
        }
        int opcion = -1;
        do {
            mostrarMenu();
            opcion = pedirEntero();
            switch (opcion) {
                case 1 -> {insertar();}
                case 2 -> {modificar();}
                case 3 -> {borrar();}
                case 4 -> {listar();}
                case 0 -> {System.out.println("SALIENDO...");}
                default -> {System.out.println("Opcion invalida");}
            }
        } while (opcion != 0);

    }

    private static void mostrarMenu() {
        System.out.println("MENU");
        System.out.println("1 .- insertar");
        System.out.println("2 .- modificar ");
        System.out.println("3 .- borrar ");
        System.out.println("4 .- consultar");
        System.out.println("0 .- SALIR");
    }

    private static int pedirEntero() {
        int opcion= -1;
        
        try{
            System.out.println("Introduce una opción");
            opcion= Integer.parseInt(SC.nextLine());
            
        }catch(NumberFormatException e){
            System.err.println("No es un entero :");
        }
        return opcion;
    }

    private static void insertar() {
        List<Empleados>lista= GestionEmpleados.leerXML();
         
        
        System.out.println("nif :");
        String nif = SC.nextLine();
        
        
        for (Empleados empleados : lista) {
            if(empleados.getNif().equalsIgnoreCase(nif)){
                System.err.println("Ya existe un empleado con ese nif");
                return;
            }
        }
        
        System.out.println("Nombre: ");
        String nombre = SC.nextLine();
        
        System.out.println("Apellidos: ");
        String apellidos = SC.nextLine();
        
       
        double sueldo = pedirDouble();
        Empleados e = new Empleados(nif,nombre,apellidos,sueldo);
        lista.add(e);
        GestionEmpleados.guardarXML(lista);
    }

    private static double pedirDouble() {
       while (true) {
        System.out.print("Sueldo: ");
        try {
            // Reemplazamos coma por punto por si el usuario escribe 1500,50
            String entrada = SC.nextLine().trim().replace(',', '.');
            double sueldo = Double.parseDouble(entrada);
            if (sueldo >= 0) {
                return sueldo;
            }
            System.out.println("El sueldo no puede ser negativo.");
        } catch (NumberFormatException e) {
            System.out.println("Error: introduce un valor numérico válido.");
        }
    }
        
         
        
    }

    private static void modificar() {
        System.out.println("Introduce el nif del empleado que deseas modificar");
        String nif = SC.nextLine().trim();
        
        List<Empleados>lista  = GestionEmpleados.leerXML();
        
        if(lista.isEmpty()){
            System.out.println("La lista esta vacia no se puede buscar");
            return;
        }
        for (Empleados empleados : lista) {
            if(empleados.getNif().equalsIgnoreCase(nif)){
                System.out.println("Introduce el nuevo Nombre ");
                empleados.setNombre(SC.nextLine());
                System.out.println("introuce nuevos aopellidos");
                empleados.setApellidos(SC.nextLine());
                System.out.println("Intorduce el nuevo sueldo :");
                empleados.setSalario(pedirDouble());
                GestionEmpleados.guardarXML(lista);
                
                System.out.println("Actuaklizado datos");
                return;
            }else {
                System.out.println("No hay coincidencias con ese nif");
            }
        }
        
        
    }

    private static void borrar() {
        List <Empleados>lista=GestionEmpleados.leerXML();
        
        System.out.println("Introduce el nif del empleado que deseas eliminar");
        String nif = SC.nextLine();
        if(lista.isEmpty()){
            System.err.println("La lista está vacìa no se puede buscar equivaklencias");
        }
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getNif().equalsIgnoreCase(nif)){
            lista.remove(i);
                System.out.println("See ha eliminado con exito");
                
                GestionEmpleados.guardarXML(lista);
                System.out.println("Seha guaradado la neuva lista sin el elemnto borrado");
                return;
            }
            System.out.println("No existe el nif buscado");
        }
        
    }

    private static void listar() {
        List <Empleados> lista = GestionEmpleados.leerXML();
        
        if(lista.isEmpty()){
            System.err.println("No se puede leer porque está vacío");
            return;
        }
        System.out.println("Mostrando empleados ");
        for (Empleados empleados : lista) {
            System.out.println(empleados);
        }
        
    }
    
}
