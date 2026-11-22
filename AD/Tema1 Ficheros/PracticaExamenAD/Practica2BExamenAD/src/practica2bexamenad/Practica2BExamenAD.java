/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica2bexamenad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author limbe
 */
public class Practica2BExamenAD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner sc= new Scanner(System.in);
        ArrayList<Integer>listaEnteros= new ArrayList<>();
        int num ;
        do {
           
            System.out.println("Introduce un numero");
            num=sc.nextInt();
            listaEnteros.add(num);
        } while (num!=0);
        
        File ficheroNumeros = new File("src/numeros.txt");
        BufferedWriter bw = null;
        try{
            bw= new BufferedWriter(new FileWriter(ficheroNumeros));
            for (int i = 0; i < listaEnteros.size(); i++) {
                    int num1= listaEnteros.get(i);
                    
                    if(num1!=0){
                    bw.write(String.valueOf(num1));
                    bw.newLine();
                    
                    }
                    
            }
        }catch(IOException e){
            System.out.println("Error al escribir:" +e.getMessage());
        }finally{
            try {
                if(bw!=null)
                bw.close();
            } catch (IOException ex) {
                System.out.println("Erorr al cerrar : "+ex.getMessage());
            }
        }
        
        BufferedReader br= null;
        int suma = 0;
        try{
        
            br=new BufferedReader (new FileReader(ficheroNumeros));
            String numero = "";
            
            while ((numero=br.readLine())!=null){
            int nume= Integer.parseInt(numero);
            suma+=nume;
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado : "+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error : "+ ex.getMessage());
        }
        finally{
            try {
                if(br!=null)
                br.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar: "+ ex.getMessage());
            }
        }
        System.out.println("La suma total de los numeros es : "+ suma);
    }
    
}
