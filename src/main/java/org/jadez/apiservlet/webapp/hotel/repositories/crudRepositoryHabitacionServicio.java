package org.jadez.apiservlet.webapp.hotel.repositories;

import org.jadez.apiservlet.webapp.hotel.models.Habitacion;
import org.jadez.apiservlet.webapp.hotel.models.HabitacionServicio;
import org.jadez.apiservlet.webapp.hotel.models.Servicio;

import java.sql.SQLException;
import java.util.List;

public interface crudRepositoryHabitacionServicio extends crudRepository<HabitacionServicio> {
    List<Servicio> obtenerServicios(Long habitacion) throws SQLException;
    List<Servicio> obtenerServiciosPorHabitacion(Habitacion habitacion) throws SQLException;
    void eliminar(Habitacion habitacion) throws SQLException;
}
