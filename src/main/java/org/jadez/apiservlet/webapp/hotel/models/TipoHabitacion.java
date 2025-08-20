package org.jadez.apiservlet.webapp.hotel.models;

public class TipoHabitacion {
    private Long id;
    private String nombre;
    private String descripcion;
    private Long estado;

    public TipoHabitacion() {
    }

    public TipoHabitacion(Long id, String nombre, String descripcion, Long estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
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

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }
}
