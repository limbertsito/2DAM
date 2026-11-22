/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package practica3bexamenad;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Scanner;
/**
 *
 * @author limbe
 */
public class MyObjectOutputStream extends ObjectOutputStream{

    public MyObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    public MyObjectOutputStream() throws IOException {
    }
@Override
    protected void writeStreamHeader() throws IOException{
        //Este es el método encargadpo de crear la cabecera,
        //Lo dejamos vacio para que no haga nada
    }
}
