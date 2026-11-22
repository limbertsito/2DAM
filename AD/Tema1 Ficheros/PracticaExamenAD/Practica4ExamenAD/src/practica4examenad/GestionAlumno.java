/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package practica4examenad;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
public class GestionAlumno {
    private static final File CARPETA = new File("datos");
    private static final File FICHEROXML = new File(CARPETA+File.separator+"alumnos.xml");
    
    
    
    public static void guardarAlumnosXML(List<Alumno> listaAlumnos){
        
        if(!comprobarDirectorio()){
        return;
        }
        try{
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            
            Document documento = constructor.newDocument();
            
            //Crear la etiqueta raiz
            
            Element elementoRaiz = documento.createElement("alumnos");
            
            //situa a nivel superior
            documento.appendChild(elementoRaiz);
            
            System.out.println("DOcumento XML en memrtoria y etiqeuta raiz <alumnos> creada");
            
            for (Alumno listaAlumno : listaAlumnos) {
                
                //creamso alumno
                Element elementoAlumno = documento.createElement("alumno");
                
                //aasignamos el atibutp del id
                
                elementoAlumno.setAttribute("id",String.valueOf(listaAlumno.getIdAlumno()));
                
                //Añade el elemno <alumno> como hijo de la raiz <alumnonos>
                
                elementoRaiz.appendChild(elementoAlumno);
                
                //alñadimos cada campo usando el metodo auxiliar
                añadirElemento(documento, elementoAlumno, "nombre", listaAlumno.getNombre());
                añadirElemento(documento, elementoAlumno, "apellidos", listaAlumno.getApellidos());
                añadirElemento(documento, elementoAlumno, "ciclo",listaAlumno.getCiclo());
                añadirElemento(documento, elementoAlumno, "curso", String.valueOf(listaAlumno.getCurso()));
                añadirElemento(documento, elementoAlumno, "notaMedia", String.valueOf(listaAlumno.getNotaMedia()));
                
                
                
            }
            
            //creamos el objeto transformer
            
            TransformerFactory fabricaTransformador = TransformerFactory.newInstance();
            Transformer transformador = fabricaTransformador.newTransformer();
            
            //2 Formatear el XML con sangria pàra que quede estrcutruado y legible
            
            transformador.setOutputProperty(OutputKeys.INDENT,"yes");
            
            //3 definir el prigen de la memoria RAM y el destino para que quede estructurado y legible
            DOMSource origen = new DOMSource(documento);
            
            StreamResult destino = new StreamResult(FICHEROXML);
            
            //Ejecutar la transformacion
            
            transformador.transform(origen, destino);
            System.out.println("ALUMNOS GUARDADOS CORRECTAMENTE EN XML");
            
        } catch (Exception ex) {
            System.out.println("ERROR al crea al crear el documento: "+ex.getMessage());
        }
    }
    private static void añadirElemento(Document documento,Element elementoPadre,String nombreEtiqueta,String contenido){
        // 1. Crea una nueva etiqueta XML con el nombre recibido
        Element nuevoElemento = documento.createElement(nombreEtiqueta);
        
        // 2. Establece el texto interno de la nueva etiqueta
        nuevoElemento.setTextContent(contenido);
        
        // 3. Añade la nueva etiqueta como hija del elemento padre (appendChild)
        elementoPadre.appendChild(nuevoElemento);
        
    }
    public static List<Alumno>leerAlumnosXML(){
        List<Alumno> lista = new ArrayList<>();
        if(!comprobarDirectorio()){
            return lista;
        }
        File alumnosXML = new File("datos"+File.separator+"alumnos.xml");
        
        if(!alumnosXML.exists()){
            System.out.println("El archivo no existe aun");
            return lista;
        }
        try{
        // 1. Crear el parser DOM
        
        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabrica.newDocumentBuilder();
        
        //2 oparsear el archvio XML
        
        Document documento = constructor.parse(alumnosXML);
        documento.getDocumentElement().normalize();
        
        // 3 Obtener todos los elemaneto <Alumnop>
        NodeList listaNodos = documento.getElementsByTagName("alumno");
        
            for (int i = 0; i < listaNodos.getLength(); i++) {
                Node nodo = listaNodos.item(i);
                
                if(nodo.getNodeType() == Node.ELEMENT_NODE){
                Element elemento = (Element) nodo;
                
                //Extraer Atributos y etiqueta hijas
                int id = Integer.parseInt(elemento.getAttribute("id"));
                String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                String apellidos  = elemento.getElementsByTagName("apellidos").item(0).getTextContent();
                String ciclo = elemento.getElementsByTagName("ciclo").item(0).getTextContent();
                int curso= Integer.parseInt(elemento.getElementsByTagName("curso").item(0).getTextContent());
                double notaMedia= Double.parseDouble(elemento.getElementsByTagName("notaMedia").item(0).getTextContent());
                
                //Reconstruir el objeto Alumnop y añadirlo a la lista
                lista.add(new Alumno(id,nombre,apellidos,ciclo,curso,notaMedia));
                
                
                }
            }
        
        
        } catch (Exception ex) {
            System.getLogger(GestionAlumno.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return lista;
    }

    private static boolean comprobarDirectorio() {
        if(!CARPETA.exists()){
            System.out.println("La carpeta : "+CARPETA.getName()+ "no existe");
            System.out.println("CREANDOLA...");
            if(CARPETA.mkdir()){
                System.out.println("Carpeta creada correctamente");
            return true;
            }else{
                System.out.println("no se pudo crar la carpoeta.");
                return false;
            }
        }else if(!CARPETA.isDirectory()){
            System.err.println("Existye un archivo con ese nombre pero no es un direcotrieo");
        return false;
        }
        return true;
    }
    
}
