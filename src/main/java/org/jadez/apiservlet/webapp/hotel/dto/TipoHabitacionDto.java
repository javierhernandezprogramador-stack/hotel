package org.jadez.apiservlet.webapp.hotel.dto;

import org.jadez.apiservlet.webapp.hotel.entity.TipoHabitacion;

public class TipoHabitacionDto {
    private Long id;
    private String nombre;
    private Long estado;

    public TipoHabitacionDto() {
    }

    public TipoHabitacionDto(TipoHabitacion tipoHabitacion) {
        this.id = tipoHabitacion.getId();
        this.nombre = tipoHabitacion.getNombre();
        this.estado = tipoHabitacion.getEstado();
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
