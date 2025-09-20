package org.jadez.apiservlet.webapp.hotel.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Habitacion {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_habitacion")
    private String numeroHabitacion;
    private String descripcion;
    private Integer capacidad;
    private double precio;
    private Long estado;
    private String piso;
    private Integer cama;
    private Integer bw;
    private String imagen;

    @ManyToOne(fetch = FetchType.LAZY)
    private TipoHabitacion tipoHabitacion;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "tbl_habitacion_servicio", joinColumns = @JoinColumn(name = "id_habitacion"),
    inverseJoinColumns = @JoinColumn(name = "id_servicio"),
    uniqueConstraints = @UniqueConstraint(columnNames = {"id_habitacion", "id_servicio"}))
    private List<Servicio> servicios;

    public Habitacion() {
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
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

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }
}
