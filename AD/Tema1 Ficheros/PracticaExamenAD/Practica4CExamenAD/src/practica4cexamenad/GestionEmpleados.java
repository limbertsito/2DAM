/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica4cexamenad;

import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author limbe
 */
public class GestionEmpleados {

    private static final File CARPETA = new File("datos");
    private static final File FICHERO_XML = new File(CARPETA, "empleados.xml");

    public static boolean comprobarDirectorioDatos() {
        if (!CARPETA.exists()) {
            if (CARPETA.mkdir()) {
                return true;
            } else {
                System.err.println("No se pudo crear la carpeta datos.");
                return false;
            }
        }
        return CARPETA.isDirectory();
    }

    public static void guardarXML(List< Empleados> lista) {
        if (!comprobarDirectorioDatos()) {
            return;
        }
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document documento = constructor.newDocument();
// 1. Crear etiqueta raíz y engancharla al documento
            Element elementoRaiz = documento.createElement("empleados");
            documento.appendChild(elementoRaiz);
// 2. Recorrer la lista de objetos y crear cada nodo hijo
            for (Empleados obj : lista) {

                Element elementoHijo = documento.createElement("empleado");
                elementoHijo.setAttribute("nif", String.valueOf(obj.getNif()));
                elementoRaiz.appendChild(elementoHijo);
// Añadir cada campo del objeto usando la función auxiliar
                anadirElemento(documento, elementoHijo, "nombre", obj.getNombre());
                anadirElemento(documento, elementoHijo, "apellidos", obj.getApellidos());
                anadirElemento(documento, elementoHijo, "sueldo", String.valueOf(obj.getSalario()));

            }
// 3. Transformar el árbol DOM de la RAM al archivo físico XML
            TransformerFactory fabricaTransformador = TransformerFactory.newInstance();
            Transformer transformador = fabricaTransformador.newTransformer();
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            transformador.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
                    "4");
            DOMSource origen = new DOMSource(documento);
            StreamResult destino = new StreamResult(FICHERO_XML);
            transformador.transform(origen, destino);
            System.out.println("Datos guardados correctamente en XML.");
        } catch (Exception excepcion) {
            System.err.println("Error al guardar el XML: " + excepcion.getMessage());
        }
    }

    public static List< Empleados> leerXML() {
        List< Empleados> lista = new ArrayList<>();
        if (!comprobarDirectorioDatos() || !FICHERO_XML.exists()) {
            return lista;
        }
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document documento = constructor.parse(FICHERO_XML);
            documento.getDocumentElement().normalize();
            NodeList nodos = documento.getElementsByTagName("empleado");
            for (int i = 0; i < nodos.getLength(); i++) {
                Node nodo = nodos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;
                    // Extraer atributo y etiquetas hijas adaptando tipos (int, double,String)
                  
                    
                    String nif = elemento.getAttribute("nif");
                    String nombre = obtenerTexto(elemento, "nombre");
                    String apellidos = obtenerTexto(elemento, "apellidos");
                    double sueldo = Double.parseDouble(obtenerTexto(elemento, "sueldo"));
                    Empleados obj = new Empleados(nif, nombre, apellidos, sueldo);
                    lista.add(obj);
                }
            }
        } catch (Exception excepcion) {
            System.err.println("Error al leer el archivo XML: " + excepcion.getMessage());
        }
        return lista;
    }

    // Auxiliar de ESCRITURA: crea una etiqueta con texto y la engancha al padre
    private static void anadirElemento(Document documento, Element elementoPadre,
            String nombreEtiqueta, String contenido) {
        Element nuevoElemento = documento.createElement(nombreEtiqueta);
        nuevoElemento.setTextContent(contenido);
        elementoPadre.appendChild(nuevoElemento);
    }

    private static String obtenerTexto(Element elementoPadre, String nombreEtiqueta) {
        NodeList listaNodos = elementoPadre.getElementsByTagName(nombreEtiqueta);
        if (listaNodos.getLength() > 0) {
            return listaNodos.item(0).getTextContent();
        }
        return "";
    }

}
