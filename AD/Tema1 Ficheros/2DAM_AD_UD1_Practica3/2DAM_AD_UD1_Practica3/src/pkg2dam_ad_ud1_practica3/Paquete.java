package pkg2dam_ad_ud1_practica3;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Clase que representa un paquete de viaje de MilViajesSalamanca.
 * Se compone de fechas de viaje, vuelo de ida, vuelo de vuelta y hotel de estancia.
 * Implementa Serializable para persistencia en ficheros binarios secuenciales.
 * 
 * @author limbert.lovcub
 */
public class Paquete implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Vuelo vueloIda;
    private Vuelo vueloVuelta;
    private Hotel hotelEstancia;

    public Paquete() {
    }

    public Paquete(String nombre, LocalDate fechaInicio, LocalDate fechaFin, 
                   Vuelo vueloIda, Vuelo vueloVuelta, Hotel hotelEstancia) {
        if (fechaInicio != null && fechaFin != null && !fechaInicio.isBefore(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio del viaje debe ser anterior a la fecha de fin.");
        }
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.vueloIda = vueloIda;
        this.vueloVuelta = vueloVuelta;
        this.hotelEstancia = hotelEstancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        if (fechaInicio != null && this.fechaFin != null && !fechaInicio.isBefore(this.fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha de fin.");
        }
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        if (this.fechaInicio != null && fechaFin != null && !this.fechaInicio.isBefore(fechaFin)) {
            throw new IllegalArgumentException("La fecha de fin debe ser posterior a la fecha de inicio.");
        }
        this.fechaFin = fechaFin;
    }

    public Vuelo getVueloIda() {
        return vueloIda;
    }

    public void setVueloIda(Vuelo vueloIda) {
        this.vueloIda = vueloIda;
    }

    public Vuelo getVueloVuelta() {
        return vueloVuelta;
    }

    public void setVueloVuelta(Vuelo vueloVuelta) {
        this.vueloVuelta = vueloVuelta;
    }

    public Hotel getHotelEstancia() {
        return hotelEstancia;
    }

    public void setHotelEstancia(Hotel hotelEstancia) {
        this.hotelEstancia = hotelEstancia;
    }

    /**
     * Calcula la duración de la estancia en días entre la fecha de inicio y la fecha de fin.
     * @return Número de días de estancia
     */
    public long getDuracionDias() {
        if (this.fechaInicio == null || this.fechaFin == null) {
            return 0;
        }
        return ChronoUnit.DAYS.between(this.fechaInicio, this.fechaFin);
    }
    
    /**
     * Calcula el precio total del paquete sumando el precio de ida,
     * el precio de vuelta y el coste del hotel según los días de estancia.
     * @return Precio total en euros
     */
    public double getPrecioTotalPaquete() {
        double precioIda = (vueloIda != null) ? vueloIda.getPrecio() : 0.0;
        double precioVuelta = (vueloVuelta != null) ? vueloVuelta.getPrecio() : 0.0;
        double precioNocheHotel = (hotelEstancia != null) ? hotelEstancia.getPrecioNoche() : 0.0;
        long dias = getDuracionDias();
        return precioIda + precioVuelta + (precioNocheHotel * dias);
    }
    
    /**
     * Método auxiliar para mantener compatibilidad con llamadas anteriores.
     * @return Precio total
     */
    public double getPrecioTotalPaqute() {
        return getPrecioTotalPaquete();
    }

    @Override
    public String toString() {
        return "Paquete: \"" + nombre + "\"\n" +
               "   - Fechas: del " + fechaInicio + " al " + fechaFin + " (" + getDuracionDias() + " días de estancia)\n" +
               "   - Vuelo de Ida: " + vueloIda + "\n" +
               "   - Vuelo de Vuelta: " + vueloVuelta + "\n" +
               "   - Alojamiento: " + hotelEstancia + "\n" +
               "   - PRECIO TOTAL DEL PAQUETE: " + String.format("%.2f", getPrecioTotalPaquete()) + "€";
    }
}
