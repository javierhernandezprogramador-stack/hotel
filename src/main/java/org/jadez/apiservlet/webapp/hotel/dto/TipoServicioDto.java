package org.jadez.apiservlet.webapp.hotel.dto;

import org.jadez.apiservlet.webapp.hotel.entity.TipoServicio;

public class TipoServicioDto {
    private Long id;
    private String nombre;
    private Long estado;

    public TipoServicioDto() {
    }

    public TipoServicioDto(TipoServicio tipoServicio) {
        this.id = tipoServicio.getId();
        this.nombre = tipoServicio.getNombre();
        this.estado = tipoServicio.getEstado();
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

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }
}
