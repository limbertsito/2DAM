package pkg2dam_ad_ud1_practica4;

/**
 * Clase que representa la entidad Empleado en el sistema de recursos humanos de Venancio Corp.
 * No requiere Serializable ya que los campos se almacenan individualmente con RandomAccessFile.
 * 
 * @author limbert.lovcub
 */
public class Empleado {
    
    // Longitudes fijas de caracteres según las especificaciones de la práctica
    public static final int LONG_NIF = 9;         // 8 números y 1 letra
    public static final int LONG_NOMBRE = 10;      // 10 caracteres
    public static final int LONG_APELLIDOS = 10;   // 10 caracteres
    
    // Tamaño de cada registro en bytes:
    // NIF: 9 * 2 = 18 bytes
    // Nombre: 10 * 2 = 20 bytes
    // Apellidos: 10 * 2 = 20 bytes
    // Salario: double = 8 bytes
    // TOTAL: 18 + 20 + 20 + 8 = 66 bytes por registro
    public static final int TAM_REGISTRO = (LONG_NIF * 2) + (LONG_NOMBRE * 2) + (LONG_APELLIDOS * 2) + Double.BYTES;
    
    // Desplazamiento en bytes para acceder directamente al campo salario (modificación directa)
    public static final int DESP_SALARIO = (LONG_NIF * 2) + (LONG_NOMBRE * 2) + (LONG_APELLIDOS * 2); // 58 bytes
    
    // Marca para borrado lógico (NIF = -1)
    public static final String NIF_BORRADO = "-1";

    private String nif;
    private String nombre;
    private String apellidos;
    private double salario;

    public Empleado() {
    }

    public Empleado(String nif, String nombre, String apellidos, double salario) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.salario = salario;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return String.format("NIF: %-9s | Nombre: %-10s | Apellidos: %-10s | Salario: %10.2f €",
                nif, nombre, apellidos, salario);
    }
}
