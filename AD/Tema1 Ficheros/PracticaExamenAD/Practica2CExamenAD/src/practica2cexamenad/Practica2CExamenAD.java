/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica2cexamenad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author limbe
 */
public class Practica2CExamenAD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        System.out.println("Destinatario : ");
        String destinatario = sc.nextLine();

        System.out.println("El sauinto es : ");
        String asunto = sc.nextLine();

        System.out.println("Cuerpo del correo : ");
        String cuerpo = sc.nextLine();

        File archivo = new File("src/correo.txt");

        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(archivo));

            bw.write(destinatario);
            bw.newLine();
            bw.write(asunto);
            bw.newLine();
            bw.write(cuerpo);
            bw.newLine();
            bw.write(LocalDate.now().toString());
            bw.newLine();
            bw.write(LocalDateTime.now().toString());
            bw.newLine();

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException ex) {
                System.out.println("Erro al cerrar : " + ex.getMessage());
            }
        }

        BufferedReader br = null;

        try {

            br = new BufferedReader(new FileReader(archivo));
            String linea = "";

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Error al encontrar el archivo: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error IO :" + ex.getMessage());
        }finally{
            try {
                if(br!=null)
                br.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el ficheor : "+ex.getMessage());
            }
        }

    }

}
