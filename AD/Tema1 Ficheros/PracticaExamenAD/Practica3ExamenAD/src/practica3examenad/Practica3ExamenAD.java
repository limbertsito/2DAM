/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica3examenad;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author limbe
 */
public class Practica3ExamenAD {

    final static File F = new File("src/EjemploObjetos.dat");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        escribir(F);
        leer(F);
    }

    private static void escribir(File F) {

        ObjectOutputStream oos = null;
        try {
            // TODO code application logic here
            System.out.println("ESSCRIBIENDO...");
            
            if(F.exists()){
                
                //SI ya exsite vamos a sobreescribirlo y le añadimos el header vacio
                
                oos = new MyObjectOutputStream(new FileOutputStream(F,true));
            }else{
                oos = new ObjectOutputStream(new FileOutputStream(F,true));
            }

            //CREamso el array que vamos a escribir con el objketo aluimno
            Alumno alumnos[] = {new Alumno("Limbert", 9), new Alumno("Fran", 6), new Alumno("Mario", 10), new Alumno("Unai", 9)};
            for (int i = 0; i < alumnos.length; i++) {

                //Escribimos el opbjeto alumno uno por uno accediento al array mediante su infdice
                oos.writeObject(alumnos[i]);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Error al encointrar el fichero : " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error IO: " + ex.getMessage());
        } finally {
            try {
                //cerramos el flujo
                //COntrolamos la excepcion ya que si no hya nada no lo cerraremos 
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException ex) {
                System.out.println("Error al cerrar el fichero: " + ex.getMessage());
            }
        }
    }

    private static void leer(File F) {
        ObjectInputStream ois = null;
        try {
            System.out.println("LEYENDO...");
            
            ois = new ObjectInputStream(new FileInputStream(F));

            while (true) {
                //Ssimeopre al leer tenemos que asiganlrlo a una variable pàra leerlo
                //Casteamso a objeto alumno 
                Alumno alumno = (Alumno) ois.readObject();
                
                System.out.println("Nombre: "+alumno.getNombre());
                System.out.println("Nota: " +alumno.getNota());
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Error al encontrar el fichero: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Ereroa al acceder a la clase: " + ex.getMessage());
        } catch (EOFException e) {
            System.out.println("Fin de la lectura");
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
                System.out.println("Error al cerrar el archivo");
            }
        } catch (IOException ex) {
            System.out.println("Error de IO: " + ex.getMessage());
        }

    }

}
