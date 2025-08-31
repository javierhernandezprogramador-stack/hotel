package org.jadez.apiservlet.webapp.hotel.entity;

import java.util.ArrayList;
import java.util.List;

public class HabitacionServicio {
    private Long id;
    private Habitacion habitacion;
    private List<Servicio> servicios;

    public HabitacionServicio() {
        this.servicios = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }
}
