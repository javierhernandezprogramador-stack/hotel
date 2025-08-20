package org.jadez.apiservlet.webapp.hotel.models;

public class TipoServicio {
    private Long id;
    private String nombre;
    private Long estado;
    private String descripcion;

    public TipoServicio() {
    }

    public TipoServicio(Long id, String nombre, Long estado, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
        this.descripcion = descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
