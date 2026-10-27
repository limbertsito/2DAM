package pkg2dam_ad_ud1_practica4;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Gestor encargado de las operaciones CRUD sobre el fichero de acceso aleatorio (RandomAccessFile).
 * Aplica registros de longitud fija y borrado lógico según el temario de Acceso a Datos.
 * 
 * @author limbert.lovcub
 */
public class GestionEmpleados {

    private final File fichero;

    public GestionEmpleados(File fichero) {
        this.fichero = fichero;
    }

    /**
     * Escribe una cadena de texto con longitud fija de caracteres en el fichero binario.
     * Si el texto supera la longitud se recorta; si es más corto, se rellena con espacios.
     *
     * @param raf Flujo RandomAccessFile
     * @param texto Cadena a escribir
     * @param longitud Número fijo de caracteres
     * @throws IOException Si ocurre un error de E/S
     */
    private void escribirCadenaFija(RandomAccessFile raf, String texto, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder(texto != null ? texto.trim() : "");
        if (sb.length() > longitud) {
            sb.setLength(longitud);
        }
        while (sb.length() < longitud) {
            sb.append(' ');
        }
        raf.writeChars(sb.toString());
    }

    /**
     * Lee un número determinado de caracteres de un registro y devuelve el texto sin espacios sobrantes.
     *
     * @param raf Flujo RandomAccessFile
     * @param longitud Número de caracteres a leer
     * @return Cadena leída limpia con trim()
     * @throws IOException Si ocurre un error de E/S
     */
    private String leerCadenaFija(RandomAccessFile raf, int longitud) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < longitud; i++) {
            sb.append(raf.readChar());
        }
        return sb.toString().trim();
    }

    /**
     * Escribe todos los campos de un empleado en la posición actual del puntero.
     *
     * @param raf Flujo RandomAccessFile
     * @param e Objeto Empleado a escribir
     * @throws IOException Si ocurre un error de E/S
     */
    private void escribirEmpleado(RandomAccessFile raf, Empleado e) throws IOException {
        escribirCadenaFija(raf, e.getNif(), Empleado.LONG_NIF);
        escribirCadenaFija(raf, e.getNombre(), Empleado.LONG_NOMBRE);
        escribirCadenaFija(raf, e.getApellidos(), Empleado.LONG_APELLIDOS);
        raf.writeDouble(e.getSalario());
    }

    /**
     * Lee un empleado completo a partir de la posición actual del puntero.
     *
     * @param raf Flujo RandomAccessFile
     * @return Objeto Empleado reconstruido
     * @throws IOException Si ocurre un error de E/S
     */
    private Empleado leerEmpleado(RandomAccessFile raf) throws IOException {
        String nif = leerCadenaFija(raf, Empleado.LONG_NIF);
        String nombre = leerCadenaFija(raf, Empleado.LONG_NOMBRE);
        String apellidos = leerCadenaFija(raf, Empleado.LONG_APELLIDOS);
        double salario = raf.readDouble();
        return new Empleado(nif, nombre, apellidos, salario);
    }

    /**
     * Busca la posición en bytes donde comienza el registro de un empleado por su NIF.
     *
     * @param raf Flujo RandomAccessFile
     * @param nifBuscado NIF del empleado
     * @return Posición en bytes del inicio del registro, o -1 si no existe
     * @throws IOException Si ocurre un error de E/S
     */
    private long buscarPosicionEmpleado(RandomAccessFile raf, String nifBuscado) throws IOException {
        raf.seek(0);
        while (raf.getFilePointer() + Empleado.TAM_REGISTRO <= raf.length()) {
            long posicion = raf.getFilePointer();
            String nifActual = leerCadenaFija(raf, Empleado.LONG_NIF);
            if (nifActual.equalsIgnoreCase(nifBuscado.trim())) {
                return posicion;
            }
            // Saltamos el resto del registro para comprobar el siguiente
            raf.seek(posicion + Empleado.TAM_REGISTRO);
        }
        return -1;
    }

    /**
     * Busca el primer hueco borrado lógicamente (donde NIF == "-1") para reutilizar espacio.
     *
     * @param raf Flujo RandomAccessFile
     * @return Posición en bytes del hueco reutilizable, o -1 si no hay registros borrados
     * @throws IOException Si ocurre un error de E/S
     */
    private long buscarHuecoBorrado(RandomAccessFile raf) throws IOException {
        raf.seek(0);
        while (raf.getFilePointer() + Empleado.TAM_REGISTRO <= raf.length()) {
            long posicion = raf.getFilePointer();
            String nifActual = leerCadenaFija(raf, Empleado.LONG_NIF);
            if (nifActual.equals(Empleado.NIF_BORRADO)) {
                return posicion;
            }
            raf.seek(posicion + Empleado.TAM_REGISTRO);
        }
        return -1;
    }

    /**
     * Inserción de un nuevo empleado comprobando previamente si el NIF ya existe.
     * Si no existe, reutiliza un hueco borrado si lo hay, o lo añade al final del archivo.
     *
     * @param nuevo Objeto Empleado a insertar
     * @return true si se insertó con éxito, false si el NIF ya existía
     */
    public boolean insertarEmpleado(Empleado nuevo) {
        try (RandomAccessFile raf = new RandomAccessFile(fichero, "rw")) {
            // Comprobación de existencia previa
            if (buscarPosicionEmpleado(raf, nuevo.getNif()) != -1) {
                System.out.println("Aviso: El empleado con NIF " + nuevo.getNif() + " ya existe en la base de datos.");
                return false;
            }

            // Reutilización de huecos o inserción al final
            long hueco = buscarHuecoBorrado(raf);
            if (hueco != -1) {
                raf.seek(hueco);
            } else {
                raf.seek(raf.length());
            }

            escribirEmpleado(raf, nuevo);
            System.out.println("Empleado insertado correctamente.");
            return true;
        } catch (IOException e) {
            System.err.println("Error al insertar empleado: " + e.getMessage());
            return false;
        }
    }

    /**
     * Consulta la información de un empleado por su NIF.
     *
     * @param nif NIF del empleado
     * @return Objeto Empleado si existe, o null si no existe
     */
    public Empleado consultarEmpleado(String nif) {
        if (!fichero.exists() || fichero.length() == 0) {
            System.out.println("Aviso: No hay empleados registrados en el sistema.");
            return null;
        }

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "r")) {
            long posicion = buscarPosicionEmpleado(raf, nif);
            if (posicion == -1) {
                System.out.println("Aviso: El empleado con NIF " + nif + " no existe.");
                return null;
            }

            raf.seek(posicion);
            Empleado emp = leerEmpleado(raf);
            System.out.println("\n--- DATOS DEL EMPLEADO ---");
            System.out.println(emp);
            return emp;
        } catch (IOException e) {
            System.err.println("Error al consultar empleado: " + e.getMessage());
            return null;
        }
    }

    /**
     * Modificación directa del salario de un empleado buscando por su NIF.
     * Realiza una sobrescritura directa de 8 bytes usando seek() sobre el campo salario.
     *
     * @param nif NIF del empleado
     * @param nuevoSalario Nuevo importe a asignar
     * @return true si se modificó correctamente, false si no existe
     */
    public boolean modificarSalario(String nif, double nuevoSalario) {
        if (!fichero.exists() || fichero.length() == 0) {
            System.out.println("Aviso: No hay empleados registrados.");
            return false;
        }

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "rw")) {
            long posicion = buscarPosicionEmpleado(raf, nif);
            if (posicion == -1) {
                System.out.println("Aviso: No se puede modificar. El empleado con NIF " + nif + " no existe.");
                return false;
            }

            // Desplazamiento directo al campo salario (58 bytes desde el inicio del registro)
            raf.seek(posicion + Empleado.DESP_SALARIO);
            raf.writeDouble(nuevoSalario);
            System.out.println("Salario modificado correctamente a " + String.format("%.2f", nuevoSalario) + " €.");
            return true;
        } catch (IOException e) {
            System.err.println("Error al modificar salario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Borrado lógico de un empleado situando su NIF a -1.
     *
     * @param nif NIF del empleado a borrar
     * @return true si fue borrado, false si no existe
     */
    public boolean borrarEmpleado(String nif) {
        if (!fichero.exists() || fichero.length() == 0) {
            System.out.println("Aviso: No hay empleados registrados.");
            return false;
        }

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "rw")) {
            long posicion = buscarPosicionEmpleado(raf, nif);
            if (posicion == -1) {
                System.out.println("Aviso: No se puede borrar. El empleado con NIF " + nif + " no existe.");
                return false;
            }

            // Sobrescritura directa del campo NIF con la marca de borrado "-1"
            raf.seek(posicion);
            escribirCadenaFija(raf, Empleado.NIF_BORRADO, Empleado.LONG_NIF);
            System.out.println("Empleado con NIF " + nif + " borrado lógicamente del sistema.");
            return true;
        } catch (IOException e) {
            System.err.println("Error al borrar empleado: " + e.getMessage());
            return false;
        }
    }

    /**
     * Listado general de todos los empleados activos (excluyendo los borrados lógicamente).
     */
    public void listarEmpleados() {
        if (!fichero.exists() || fichero.length() == 0) {
            System.out.println("No hay empleados registrados.");
            return;
        }

        try (RandomAccessFile raf = new RandomAccessFile(fichero, "r")) {
            raf.seek(0);
            int contador = 0;
            System.out.println("\n==========================================================================");
            System.out.println("               LISTADO GENERAL DE EMPLEADOS (VENANCIO CORP)               ");
            System.out.println("==========================================================================");

            while (raf.getFilePointer() + Empleado.TAM_REGISTRO <= raf.length()) {
                Empleado emp = leerEmpleado(raf);
                if (!emp.getNif().equals(Empleado.NIF_BORRADO)) {
                    contador++;
                    System.out.println(contador + ". " + emp);
                }
            }

            if (contador == 0) {
                System.out.println("Todos los registros existentes se encuentran eliminados lógicamente.");
            } else {
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Total de empleados activos: " + contador);
            }
        } catch (IOException e) {
            System.err.println("Error al listar empleados: " + e.getMessage());
        }
    }
}
