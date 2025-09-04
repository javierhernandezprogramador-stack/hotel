package org.jadez.apiservlet.webapp.hotel.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private double precio;
    private String descripcion;
    private Long estado;

    @Column(name = "tipo")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoServicio tipoServicio;

    @ManyToMany(mappedBy = "servicios")
    private List<Habitacion> habitaciones;

    public Servicio() {
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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

    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }
}
