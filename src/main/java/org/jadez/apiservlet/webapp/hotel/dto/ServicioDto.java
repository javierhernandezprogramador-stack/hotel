package org.jadez.apiservlet.webapp.hotel.dto;

import org.jadez.apiservlet.webapp.hotel.entity.Servicio;

public class ServicioDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private double precio;
    private Long estado;
    private TipoServicioDto tipoServicioDto;

    public ServicioDto() {
    }

    public ServicioDto(Servicio servicio) {
        this.id = servicio.getId();
        this.nombre = servicio.getNombre();
        this.descripcion = servicio.getDescripcion();
        this.precio = servicio.getPrecio();
        this.estado = servicio.getEstado();

        if (servicio.getTipoServicio() != null) {
            this.tipoServicioDto = new TipoServicioDto(servicio.getTipoServicio());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public TipoServicioDto getTipoServicioDto() {
        return tipoServicioDto;
    }

    public void setTipoServicioDto(TipoServicioDto tipoServicioDto) {
        this.tipoServicioDto = tipoServicioDto;
    }
}
