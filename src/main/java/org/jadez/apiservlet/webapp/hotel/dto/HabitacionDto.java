package org.jadez.apiservlet.webapp.hotel.dto;

import org.jadez.apiservlet.webapp.hotel.entity.Habitacion;
import org.jadez.apiservlet.webapp.hotel.entity.Servicio;

import java.util.List;

public class HabitacionDto {
    private Long id;
    private String numeroHabitacion;
    private String descripcion;
    private Integer capacidad;
    private double precio;
    private Long estado;
    private String piso;
    private Integer cama;
    private Integer bw;
    private TipoHabitacionDto tipoHabitacionDto;
    List<ServicioDto> serviciosDto ;

    public HabitacionDto() {
    }

    public HabitacionDto(Habitacion habitacion) {
        this.id = habitacion.getId();
        this.numeroHabitacion = habitacion.getNumeroHabitacion();
        this.descripcion = habitacion.getDescripcion();
        this.capacidad = habitacion.getCapacidad();
        this.precio = habitacion.getPrecio();
        this.estado = habitacion.getEstado();
        this.piso = habitacion.getPiso();
        this.cama = habitacion.getCama();
        this.bw = habitacion.getBw();

        if(habitacion.getTipoHabitacion() != null) {
            this.tipoHabitacionDto = new TipoHabitacionDto(habitacion.getTipoHabitacion());
        }

        this.serviciosDto = habitacion.getServicios().stream().map(ServicioDto::new).toList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(String numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public Integer getCama() {
        return cama;
    }

    public void setCama(Integer cama) {
        this.cama = cama;
    }

    public Integer getBw() {
        return bw;
    }

    public void setBw(Integer bw) {
        this.bw = bw;
    }

    public TipoHabitacionDto getTipoHabitacionDto() {
        return tipoHabitacionDto;
    }

    public void setTipoHabitacionDto(TipoHabitacionDto tipoHabitacionDto) {
        this.tipoHabitacionDto = tipoHabitacionDto;
    }

    public List<ServicioDto> getServiciosDto() {
        return serviciosDto;
    }

    public void setServiciosDto(List<ServicioDto> serviciosDto) {
        this.serviciosDto = serviciosDto;
    }
}
