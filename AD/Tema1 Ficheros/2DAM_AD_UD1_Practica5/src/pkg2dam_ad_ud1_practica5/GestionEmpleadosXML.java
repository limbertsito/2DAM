package pkg2dam_ad_ud1_practica5;

import java.io.File;
import java.util.Locale;
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
 * Gestor encargado de la persistencia y operaciones CRUD sobre el fichero XML
 * utilizando el analizador DOM (Document Object Model) y transformadores XSLT.
 * Sigue la estructura y métodos auxiliares explicados en el temario (Ficheros Java IV).
 * 
 * @author limbert.lovcub
 */
public class GestionEmpleadosXML {

    private final File ficheroXML;

    public GestionEmpleadosXML(File ficheroXML) {
        this.ficheroXML = ficheroXML;
    }

    /**
     * Carga el documento XML existente en memoria o crea uno nuevo con la etiqueta raíz <empleados>.
     *
     * @return Objeto Document cargado y normalizado en memoria
     * @throws Exception Si ocurre un error en el procesador DOM o lectura
     */
    private Document cargarOCrearDocumento() throws Exception {
        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        DocumentBuilder constructor = fabrica.newDocumentBuilder();
        Document documento;

        if (ficheroXML.exists() && ficheroXML.length() > 0) {
            documento = constructor.parse(ficheroXML);
            documento.getDocumentElement().normalize();
        } else {
            // Crea un nuevo árbol DOM vacío en memoria y establece el elemento raíz
            documento = constructor.newDocument();
            Element elementoRaiz = documento.createElement("empleados");
            documento.appendChild(elementoRaiz);
            guardarDocumento(documento);
        }

        return documento;
    }

    /**
     * Guarda el árbol DOM presente en memoria en el archivo XML físico en disco con indentación legible.
     *
     * @param documento Documento DOM a persistir
     * @throws Exception Si ocurre un error durante la transformación o escritura
     */
    private void guardarDocumento(Document documento) throws Exception {
        TransformerFactory fabricaTransformador = TransformerFactory.newInstance();
        Transformer transformador = fabricaTransformador.newTransformer();

        // Activamos sangría y formato ordenado a 4 espacios
        transformador.setOutputProperty(OutputKeys.INDENT, "yes");
        transformador.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource origen = new DOMSource(documento);
        StreamResult destino = new StreamResult(ficheroXML);
        transformador.transform(origen, destino);
    }

    /**
     * Método auxiliar para crear y añadir una nueva etiqueta XML con texto dentro de un elemento padre.
     * Evita repetir código al crear los campos de cada empleado.
     */
    private void añadirElemento(Document documento, Element elementoPadre, String nombreEtiqueta, String contenido) {
        Element nuevoElemento = documento.createElement(nombreEtiqueta);
        nuevoElemento.setTextContent(contenido != null ? contenido.trim() : "");
        elementoPadre.appendChild(nuevoElemento);
    }

    /**
     * Método auxiliar que obtiene el contenido de texto dentro de una etiqueta hija.
     *
     * @param elementoPadre Elemento XML que contiene la etiqueta
     * @param nombreEtiqueta Nombre del elemento hijo a buscar
     * @return Texto contenido dentro de la etiqueta, o cadena vacía si no existe
     */
    private String obtenerTexto(Element elementoPadre, String nombreEtiqueta) {
        NodeList listaNodos = elementoPadre.getElementsByTagName(nombreEtiqueta);
        if (listaNodos != null && listaNodos.getLength() > 0) {
            Node nodo = listaNodos.item(0);
            return nodo.getTextContent().trim();
        }
        return "";
    }

    /**
     * Busca y recupera el nodo Element correspondiente a un empleado identificado por su NIF.
     *
     * @param documento Árbol DOM del fichero
     * @param nif NIF del empleado a localizar
     * @return Element del empleado, o null si no se encuentra
     */
    private Element buscarElementoEmpleado(Document documento, String nif) {
        NodeList nodosEmpleados = documento.getElementsByTagName("empleado");
        for (int i = 0; i < nodosEmpleados.getLength(); i++) {
            Node nodo = nodosEmpleados.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) nodo;
                if (elemento.getAttribute("nif").equalsIgnoreCase(nif.trim())) {
                    return elemento;
                }
            }
        }
        return null;
    }

    /**
     * Inserta un nuevo empleado en el fichero XML verificando que no exista previamente.
     *
     * @param nuevo Empleado a insertar
     * @return true si la inserción fue correcta, false si ya existía o hubo error
     */
    public boolean insertarEmpleado(Empleado nuevo) {
        try {
            Document documento = cargarOCrearDocumento();

            // Comprobación previa de existencia
            if (buscarElementoEmpleado(documento, nuevo.getNif()) != null) {
                System.out.println("Aviso: Ya existe un empleado con el NIF " + nuevo.getNif() + " en el archivo XML.");
                return false;
            }

            // Creamos el elemento <empleado> y asignamos el atributo "nif"
            Element elementoEmpleado = documento.createElement("empleado");
            elementoEmpleado.setAttribute("nif", nuevo.getNif().trim().toUpperCase());

            // Añadimos los elementos hijos correspondientes a los campos
            añadirElemento(documento, elementoEmpleado, "nombre", nuevo.getNombre());
            añadirElemento(documento, elementoEmpleado, "apellidos", nuevo.getApellidos());
            añadirElemento(documento, elementoEmpleado, "salario", String.format(Locale.US, "%.2f", nuevo.getSalario()));

            // Añadimos el nuevo empleado a la etiqueta raíz <empleados>
            documento.getDocumentElement().appendChild(elementoEmpleado);

            // Guardamos el documento actualizado en el archivo XML
            guardarDocumento(documento);
            System.out.println("Empleado insertado y guardado correctamente en el fichero XML.");
            return true;

        } catch (Exception e) {
            System.err.println("Error al insertar empleado en XML: " + e.getMessage());
            return false;
        }
    }

    /**
     * Consulta y muestra la información de un empleado a partir de su NIF.
     *
     * @param nif NIF a consultar
     * @return Objeto Empleado encontrado, o null si no existe
     */
    public Empleado consultarEmpleado(String nif) {
        try {
            if (!ficheroXML.exists() || ficheroXML.length() == 0) {
                System.out.println("Aviso: El archivo XML no existe o está vacío.");
                return null;
            }

            Document documento = cargarOCrearDocumento();
            Element elemento = buscarElementoEmpleado(documento, nif);

            if (elemento == null) {
                System.out.println("Aviso: El empleado con NIF " + nif + " no existe.");
                return null;
            }

            String nifLeido = elemento.getAttribute("nif");
            String nombre = obtenerTexto(elemento, "nombre");
            String apellidos = obtenerTexto(elemento, "apellidos");
            double salario = Double.parseDouble(obtenerTexto(elemento, "salario"));

            Empleado emp = new Empleado(nifLeido, nombre, apellidos, salario);
            System.out.println("\n--- DATOS DEL EMPLEADO (DESDE XML) ---");
            System.out.println(emp);
            return emp;

        } catch (Exception e) {
            System.err.println("Error al consultar empleado en XML: " + e.getMessage());
            return null;
        }
    }

    /**
     * Modifica el salario de un empleado buscando por su NIF en el árbol DOM.
     *
     * @param nif NIF del empleado
     * @param nuevoSalario Nuevo valor del salario
     * @return true si se actualizó con éxito, false si no existe
     */
    public boolean modificarSalario(String nif, double nuevoSalario) {
        try {
            if (!ficheroXML.exists() || ficheroXML.length() == 0) {
                System.out.println("Aviso: El archivo XML no existe.");
                return false;
            }

            Document documento = cargarOCrearDocumento();
            Element elemento = buscarElementoEmpleado(documento, nif);

            if (elemento == null) {
                System.out.println("Aviso: No se puede modificar. El empleado con NIF " + nif + " no existe.");
                return false;
            }

            // Localizamos el nodo hijo <salario> y actualizamos su contenido
            NodeList listaSalario = elemento.getElementsByTagName("salario");
            if (listaSalario != null && listaSalario.getLength() > 0) {
                listaSalario.item(0).setTextContent(String.format(Locale.US, "%.2f", nuevoSalario));
            } else {
                añadirElemento(documento, elemento, "salario", String.format(Locale.US, "%.2f", nuevoSalario));
            }

            guardarDocumento(documento);
            System.out.println("Salario modificado correctamente a " + String.format("%.2f", nuevoSalario) + " € en el archivo XML.");
            return true;

        } catch (Exception e) {
            System.err.println("Error al modificar salario en XML: " + e.getMessage());
            return false;
        }
    }

    /**
     * Borra el nodo de un empleado en el árbol DOM y guarda el XML resultante.
     *
     * @param nif NIF del empleado a eliminar
     * @return true si se eliminó con éxito, false si no existe
     */
    public boolean borrarEmpleado(String nif) {
        try {
            if (!ficheroXML.exists() || ficheroXML.length() == 0) {
                System.out.println("Aviso: El archivo XML no existe.");
                return false;
            }

            Document documento = cargarOCrearDocumento();
            Element elemento = buscarElementoEmpleado(documento, nif);

            if (elemento == null) {
                System.out.println("Aviso: No se puede borrar. El empleado con NIF " + nif + " no existe.");
                return false;
            }

            // Eliminamos el elemento <empleado> del documento
            elemento.getParentNode().removeChild(elemento);

            guardarDocumento(documento);
            System.out.println("Empleado con NIF " + nif + " eliminado correctamente del archivo XML.");
            return true;

        } catch (Exception e) {
            System.err.println("Error al borrar empleado en XML: " + e.getMessage());
            return false;
        }
    }

    /**
     * Muestra todos los empleados registrados en el archivo XML recorriendo el árbol DOM.
     */
    public void listarEmpleados() {
        try {
            if (!ficheroXML.exists() || ficheroXML.length() == 0) {
                System.out.println("No hay empleados registrados en el archivo XML.");
                return;
            }

            Document documento = cargarOCrearDocumento();
            NodeList nodosEmpleados = documento.getElementsByTagName("empleado");

            if (nodosEmpleados.getLength() == 0) {
                System.out.println("El archivo XML no contiene registros de empleados.");
                return;
            }

            System.out.println("\n==========================================================================");
            System.out.println("          LISTADO GENERAL DE EMPLEADOS XML (DOM - VENANCIO CORP)          ");
            System.out.println("==========================================================================");

            for (int i = 0; i < nodosEmpleados.getLength(); i++) {
                Node nodo = nodosEmpleados.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;
                    String nif = elemento.getAttribute("nif");
                    String nombre = obtenerTexto(elemento, "nombre");
                    String apellidos = obtenerTexto(elemento, "apellidos");
                    double salario = Double.parseDouble(obtenerTexto(elemento, "salario"));

                    Empleado emp = new Empleado(nif, nombre, apellidos, salario);
                    System.out.println((i + 1) + ". " + emp);
                }
            }
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("Total de empleados en el XML: " + nodosEmpleados.getLength());

        } catch (Exception e) {
            System.err.println("Error al listar empleados desde XML: " + e.getMessage());
        }
    }
}