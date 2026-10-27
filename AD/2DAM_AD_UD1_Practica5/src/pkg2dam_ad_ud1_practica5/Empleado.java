package pkg2dam_ad_ud1_practica5;

/**
 * Clase que representa la entidad Empleado en el sistema de Venancio Corp.
 * Mantiene intacto el modelo de la Práctica 4 según las indicaciones del enunciado.
 * 
 * @author limbert.lovcub
 */
public class Empleado {

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