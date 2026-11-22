/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package simulacroexamenadresuelto;

import java.io.BufferedReader;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author limbe
 */
public class SimulacroExamenADResuelto {

    final static Scanner SC = new Scanner(System.in);

    // Nombres base (en String) de la carpeta y de los distintos formatos de archivo
    final static String DIRECTORIO = "equipos";
    final static String DAT = "equipos.dat";
    final static String ARCHIVO_XML = "equipos.xml";

    // Objetos File que representan las rutas físicas reales en disco (combinando carpeta + nombre de archivo)
    final static File CARPETA_FILE = new File(DIRECTORIO);      // Objetos File que representan las rutas físicas reales en disco (combinando carpeta + nombre de archivo)
    final static File ARCHIVO_BINARIO = new File(CARPETA_FILE, DAT);   // Ruta portable: "equipos/equipos.dat"

    final static File FICHERO_TXT = new File(CARPETA_FILE, "fichero.txt");  // Ruta portable: "equipos/fichero.txt" (para importar con delimitador)
    final static File FICHERO_XML = new File(CARPETA_FILE, ARCHIVO_XML);    // Ruta portable: "equipos/equipos.xml" (para exportar con DOM)
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //1 .- preparar directorio
        prepararDirectorio();

        int opcion = -1;

        do {
            mostrarMenu();
            opcion = pedirEntero("Escoge una opción: ");

            switch (opcion) {
                case 1 -> {
                    ArrayList<Equipo> lista = leerEquiposDAT();
                    for (Equipo eq : lista) {
                        System.out.println(eq);
                    }
                }
                case 2 ->
                    insertarEquipo();
                case 3 ->
                    buscarEquipo();
                case 4 ->
                    modificarEquipo();
                case 5 ->
                    borrarEquipo();
                case 6 -> {
                    ArrayList<Equipo> importados = leerDesdeTextoConDelimitador(FICHERO_TXT, ";");
                    for (Equipo eq : importados) {
                        System.out.println("Leído de TXT: " + eq);
                    }
                }
                case 7 ->
                    exportarAXML();
                case 0 ->
                    System.out.println("Saliendo de la aplicación...");
                default ->
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("=======MENU=======");
        System.out.println("1 .- Listar Los equipos(Binario)"); //leer todos los registros del binario y mostrarlos
        System.out.println("2 .- Insertar Equipo"); //añadir un registro nuevo al final sin borrar los anteriores (append = true)
        System.out.println("3 .- Buscar Equipo");   //consultar/buscar un registro concreto por su ID o clave
        System.out.println("4 .- Modificar Equipo"); //cambiar un dato de un registro y actualizar el fichero (append = false)
        System.out.println("5 .- Borrar Equipo");   //eliminar un registro del fichero y actualizarlo (append = false)
        System.out.println("6 .- importarDesdeTexto( delimitador ';') "); //leer un .txt/.csv separado por ';' o ',' y pasarlo a una lista
        System.out.println("7 .- Exportar de binario a XML "); //pasar los datos del fichero binario a un archivo .xml usando DOM
        System.out.println("0 .- Salir");
    }

    private static void prepararDirectorio() {
        // 1. Comprobar y crear directorio si no existe

        if (!CARPETA_FILE.exists()) {

            if (CARPETA_FILE.mkdir()) {
                System.out.println("Directorio creado en :" + CARPETA_FILE.getAbsolutePath());
            }
        }

        // 2. Comprobar si existe el fichero binario o está vacío
        if (!ARCHIVO_BINARIO.exists() || ARCHIVO_BINARIO.length() == 0) {

            try {
                ARCHIVO_BINARIO.createNewFile();
                
                //CREAMOS LOS DATOS POR DEFECTO
                crearDatosPorDefecto(ARCHIVO_BINARIO.getPath());
            
            } catch (IOException ex) {
                System.err.println("Error E/S : " + ex.getMessage());
            }
        }
    }
    
    /**
     * Metodo para crear los datos por defecto en caso de que estuvbiese vacio
     *
     * @param path
     */
    private static void crearDatosPorDefecto(String path) {
        // Creamos un array estático en memoria con 3 equipos iniciales de prueba
        Equipo[] equiposIniciales = {
            new Equipo(5001, "Portatil", "Lenovo", "A12", 649.50),
            new Equipo(5002, "Sobremesa", "HP", "B03", 789.50),
            new Equipo(5003, "Monitor", "SamgSung", "A12", 159.00)
        };
        DataOutputStream dos = null;
        try {
            
            dos = new DataOutputStream(new FileOutputStream(ARCHIVO_BINARIO, false));
            //Esrcibimoos los datos
            for (Equipo e : equiposIniciales) {
                dos.writeInt(e.getNumEquipo());
                dos.writeUTF(e.getTipo());
                dos.writeUTF(e.getMarca());
                dos.writeUTF(e.getAula());
                dos.writeDouble(e.getPrecio());
            }
            // Forzamos el vaciado del buffer para asegurar que todos los bytes pasen al disco
            dos.flush();

        } catch (FileNotFoundException ex) {
            System.err.println("Error al ecnotnrar el archivo: " + ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Error E/S : " + ex.getMessage());
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException ex) {
                System.err.println("Error al cerrar ekl fichero: " + ex.getMessage());
            }
        }
    }

    // 2. Lectura secuencial del binario hacia ArrayList
    private static ArrayList<Equipo> leerEquiposDAT() {

        //Declaramso el arrayList para poder leerlo y meterlos dentro
        ArrayList<Equipo> equipos = new ArrayList();

        //Comprobamos que el archivo exista sino devolvemos la lista vacia
        if (!ARCHIVO_BINARIO.exists()) {
            return equipos;
        }
        DataInputStream dis = null;

        try {
            dis = new DataInputStream(new FileInputStream(ARCHIVO_BINARIO));

            //Leemos mediante un bucle infinito hasta el final del fichero
            while (true) {
                int num = dis.readInt();
                String tipo = dis.readUTF();
                String marca = dis.readUTF();
                String aula = dis.readUTF();
                double precio = dis.readDouble();

                //Añadimos los datos leidos , e instanciamos el objeto y lo añadimos  a la lista
                equipos.add(new Equipo(num, tipo, marca, aula, precio));
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Error no se ha encontrado el archivo: " + ex.getMessage());
        } catch (EOFException e) {
            System.out.println("Fin del archivo.");
        } catch (IOException ex) {
            System.out.println("Error de E/S : " + ex.getMessage());
        }
        finally {
            try {
                if(dis!=null){
                    dis.close();
                }

            } catch (IOException e) {
                System.err.println("Error al cerrar el fichero "+e.getMessage());
            }

        }
        // DEVOLVEMOS LA LISTA CON TODOS LOS OBJETOS RECUPERADOS
        return equipos;
    }

    //3. INSERCIÓN DE UN NUEVO EQUIPO EN EL BINARIO (Modo Append = true)
    private static void insertarEquipo() {

        // Pedimos el identificador numérico validando que sea un entero
        int num = pedirEntero("Dime el número de equipo: ");

        //Validación 1: El número de inventario debe ser estrictamente positivo
        if (num <= 0) {
            System.out.println("Error el numero debe ser mayor a 0");
            return;
        }

        // Validación 2: Comprobamos que no exista ya ese número en el fichero
        // Reutilizamos leerEquiposDAT() para obtener todos los equipos actuales
        ArrayList<Equipo> lista = leerEquiposDAT();

        for (Equipo equipo : lista) {
            if (equipo.getNumEquipo() == num) {
                System.out.println("Error Ya existe con el numero: " + num);
                return; //Si hay uno ya , nos salimos
            }
        }

        // Si pasa las validaciones, pedimos el resto de atributos por teclado
        System.out.println("Tipo de equipo: ");
        String tipo = SC.nextLine();

        System.out.println("Marca : ");
        String marca = SC.nextLine().trim();

        System.out.println("Aula: ");
        String aula = SC.nextLine();

        double precio = pedirDoble("Precio: ");

        // Abrimos FileOutputStream con el segundo parámetro en 'true' (MODO APPEND).
        // Esto coloca el puntero al final del fichero para añadir el nuevo equipo sin borrar los anteriores.
        DataOutputStream dos = null;

        try {
            dos = new DataOutputStream(new FileOutputStream(ARCHIVO_BINARIO, true));
            //Escribimos para insertar el nuevo equipo
            dos.writeInt(num);
            dos.writeUTF(tipo);
            dos.writeUTF(marca);
            dos.writeUTF(aula);
            dos.writeDouble(precio);
            System.out.println("Equipo añadido correctamente");
        } catch (IOException ex) {
            System.out.println("Error al guardar E/S :" + ex.getMessage());
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException ex) {
                System.out.println("Error al cerrar el fichero : " + ex.getMessage());
            }
        }

    }
    // 3.B BUSCAR EQUIPO POR NÚMERO
    private static void buscarEquipo() {
        int num = pedirEntero("Dime el número de equipo a buscar: ");
        ArrayList<Equipo> lista = leerEquiposDAT();

        for (Equipo equipo : lista) {
            if (equipo.getNumEquipo() == num) {
                System.out.println("Equipo encontrado: " + equipo);
                return;
            }
        }
        System.out.println("Equipo no encontrado.");
    }
    // 3.C MODIFICAR EQUIPO (Carga lista, modifica objeto y sobrescribe binario con false)
    private static void modificarEquipo() {
        int num = pedirEntero("Dime el número de equipo a modificar: ");
        ArrayList<Equipo> lista = leerEquiposDAT();

        for (Equipo equipo : lista) {
            if (equipo.getNumEquipo() == num) {
                System.out.println("Nueva aula: ");
                equipo.setAula(SC.nextLine().trim());
                equipo.setPrecio(pedirDoble("Nuevo precio: "));

                sobrescribirDAT(lista);
                System.out.println("Equipo modificado correctamente.");
                return;
            }
        }
        System.out.println("Equipo no encontrado.");
    }
    
    
    // 3.D BORRAR EQUIPO (Elimina de la lista y sobrescribe binario con false)
    private static void borrarEquipo() {
        int num = pedirEntero("Dime el número de equipo a borrar: ");
        ArrayList<Equipo> lista = leerEquiposDAT();

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNumEquipo() == num) {
                lista.remove(i);
                sobrescribirDAT(lista);
                System.out.println("Equipo borrado correctamente.");
                return;
            }
        }
        System.out.println("Equipo no encontrado.");
    }
    
    // Auxiliar para guardar toda la lista tras modificar o borrar (append = false)
    private static void sobrescribirDAT(ArrayList<Equipo> lista) {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(ARCHIVO_BINARIO, false));
            for (Equipo equipo : lista) {
                dos.writeInt(equipo.getNumEquipo());
                dos.writeUTF(equipo.getTipo());
                dos.writeUTF(equipo.getMarca());
                dos.writeUTF(equipo.getAula());
                dos.writeDouble(equipo.getPrecio());
            }
            dos.flush();
        } catch (IOException ex) {
            System.out.println("Error al sobrescribir binario: " + ex.getMessage());
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException ex) {
                System.out.println("Error al cerrar flujo: " + ex.getMessage());
            }
        }
    }
    
    //4. LEER FICHERO DE TEXTO CON DELIMITADOR Y VOLCAR A COLECCIÓN
    private static ArrayList<Equipo> leerDesdeTextoConDelimitador(File FICHERO_TXT, String delimitador) {

        ArrayList<Equipo> lista = new ArrayList<>();

        // 1. Declaramos el BufferedReader fuera del try inicializado a null
        // para poder acceder a él y cerrarlo dentro del bloque finally
        BufferedReader br = null;

        try {
            // 2. Instanciamos el flujo de caracteres envolviendo FileReader en BufferedReader
            br = new BufferedReader(new FileReader(FICHERO_TXT));
            String linea;

            // 3. Leemos línea a línea hasta que readLine() devuelva null (fin de fichero / EOF)
            while ((linea = br.readLine()) != null) {

                // Ignoramos líneas vacías para evitar errores al hacer split
                if (linea.trim().isEmpty()) {
                    continue;
                }

                // 4. Dividimos la línea por el delimitador (";" o ",") en un array de String
                String[] partes = linea.split(delimitador);

                // 5. Convertimos cada elemento del array al tipo correspondiente del constructor
                int num = Integer.parseInt(partes[0].trim());
                String tipo = partes[1].trim();
                String marca = partes[2].trim();
                String aula = partes[3].trim();
                double precio = Double.parseDouble(partes[4].trim().replace(",", "."));

                // 6. Creamos el objeto Equipo y lo añadimos a la lista
                lista.add(new Equipo(num, tipo, marca, aula, precio));
            }

        } catch (FileNotFoundException ex) {
            System.err.println("Error: No se encuentra el fichero : " + ex.getMessage());

        } catch (IOException ex) {
            System.err.println("Error de entrada/salida al leer:" + ex.getMessage());

        } catch (NumberFormatException ex) {
            System.err.println("Error al convertir un número en el fichero : " + ex.getMessage());

        } catch (ArrayIndexOutOfBoundsException ex) {
            // Se lanza si alguna línea tiene menos campos separados por el delimitador de los esperados
            System.err.println("Error: Faltan campos en alguna línea del fichero : " + ex.getMessage());

        } finally {

            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    System.err.println("Error al cerrar el BufferedReader :" + ex.getMessage());
                }
            }
        }
//RETORNAMOS LA LISTA
        return lista;
    }
    // 5. EXPORTAR DE BINARIO A XML MEDIANTE DOM
    private static void exportarAXML() {
        // [CAMBIAR]: Llama al método que te devuelva la lista de tu clase (Equipo, Vuelo, Alumno...)
        ArrayList<Equipo> equipos = leerEquiposDAT();
        try {
            // [FIJO - NO SE TOCA]: Prepara el constructor del árbol DOM vacío en memoria
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document documento = constructor.newDocument();
            
            // [CAMBIAR]: Nombre de la etiqueta raíz (en plural, ej: "equipos", "vuelos", "alumnos")
            Element elementoRaiz = documento.createElement("equipos");
            documento.appendChild(elementoRaiz);
            
            // [CAMBIAR]: Tipo de objeto y nombre de la lista que recorres
            for (Equipo equipo : equipos) {
                
                // [CAMBIAR]: Nombre de la etiqueta de cada registro individual (en singular, ej: "equipo")
                Element elementoHijo = documento.createElement("equipo");
                
                // [CAMBIAR]: Atributo clave de la etiqueta padre (nombre del atributo y getter convertido a String)
                // Si el enunciado NO pide atributo en la etiqueta padre, borra esta línea
                elementoHijo.setAttribute("numeroEquipo", String.valueOf(equipo.getNumEquipo()));
                elementoRaiz.appendChild(elementoHijo);
                
                
                // [CAMBIAR]: Etiquetas hijas. Pon una línea por cada atributo de tu clase.
                // Todo lo que no sea String (int, double, boolean) envuélvelo en String.valueOf(...)
                anadirElemento(documento, elementoHijo, "numeroEquipo", String.valueOf(equipo.getNumEquipo()));
                anadirElemento(documento, elementoHijo, "tipo", equipo.getTipo());
                anadirElemento(documento, elementoHijo, "marca", equipo.getMarca());
                anadirElemento(documento, elementoHijo, "aula", equipo.getAula());
                anadirElemento(documento, elementoHijo, "precio", String.valueOf(equipo.getPrecio()));
            }
            
            // [FIJO - NO SE TOCA]: Configura el transformador para guardar con sangría de 4 espacios
            TransformerFactory fabricaTransformador = TransformerFactory.newInstance();
            Transformer transformador = fabricaTransformador.newTransformer();
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            transformador.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            
            
            // [FIJO - NO SE TOCA]: Vuelca el árbol DOM de la RAM al archivo físico (solo cambia la variable FICHERO_XML si la llamas distinto)
            DOMSource origen = new DOMSource(documento);
            StreamResult destino = new StreamResult(FICHERO_XML);
            transformador.transform(origen, destino);

            System.out.println("Archivo XML creado correctamente en: " + FICHERO_XML.getPath());
        } catch (ParserConfigurationException | TransformerException ex) {
            System.err.println("Error al generar XML: " + ex.getMessage());
        }
    }
    
    //METODO AUXILIAR PARA AÑDIR LSO ELEMENOTS XXML
    // [FIJO - NO SE TOCA]: Método auxiliar de escritura XML. Sirve exactamente igual para cualquier clase
    private static void anadirElemento(Document documento, Element elementoPadre, String nombreEtiqueta, String contenido) {
        Element nuevoElemento = documento.createElement(nombreEtiqueta);
        nuevoElemento.setTextContent(contenido);
        elementoPadre.appendChild(nuevoElemento);
    }
    
    private static int pedirEntero(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(SC.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Introduce un número entero válido.");
            }
        }
    }

    private static double pedirDoble(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Double.parseDouble(SC.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Error: Introduce un número decimal válido.");
            }
        }
    }

}
