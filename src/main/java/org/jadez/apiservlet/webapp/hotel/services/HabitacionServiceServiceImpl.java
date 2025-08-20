package org.jadez.apiservlet.webapp.hotel.services;

import org.jadez.apiservlet.webapp.hotel.models.Habitacion;
import org.jadez.apiservlet.webapp.hotel.models.HabitacionServicio;
import org.jadez.apiservlet.webapp.hotel.models.Servicio;
import org.jadez.apiservlet.webapp.hotel.repositories.HabitacionRepositoryImpl;
import org.jadez.apiservlet.webapp.hotel.repositories.HabitacionServicioRepositoryImpl;
import org.jadez.apiservlet.webapp.hotel.repositories.Repository;
import org.jadez.apiservlet.webapp.hotel.repositories.RepositoryHabitacionServicio;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HabitacionServiceServiceImpl implements ServiceHabitacion {
    private RepositoryHabitacionServicio habitacionServicioRepository;
    private Repository<Habitacion> habitacionRepository;

    public HabitacionServiceServiceImpl(Connection conn) {
        habitacionServicioRepository = new HabitacionServicioRepositoryImpl(conn);
        habitacionRepository = new HabitacionRepositoryImpl(conn);
    }


    @Override
    public List listar() {
        List<Habitacion> habitaciones;
        List<Servicio> servicios;
        List<HabitacionServicio> habitacionServicios = new ArrayList<>();

        try {
            habitaciones = habitacionRepository.listar();

            for (Habitacion habitacion : habitaciones) {
                servicios = habitacionServicioRepository.obtenerServicios(habitacion.getId());

                HabitacionServicio habitacionServicio = new HabitacionServicio();
                habitacionServicio.setHabitacion(habitacion);
                habitacionServicio.setServicios(servicios);

                habitacionServicios.add(habitacionServicio);
            }
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
        return habitacionServicios;
    }

    @Override
    public Object crear(Object o) {
        Habitacion habitacion = (Habitacion) o;

        try {
            return habitacionRepository.crear(habitacion);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional porId(Long id) {
        List<Servicio> servicios;
        HabitacionServicio habitacionServicio = new HabitacionServicio();

        try {
            Habitacion habitacion = habitacionRepository.porId(id);
            servicios = habitacionServicioRepository.obtenerServiciosPorHabitacion(habitacion);
            habitacionServicio.setHabitacion(habitacion);
            habitacionServicio.setServicios(servicios);
            return Optional.ofNullable(habitacionServicio);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void updateEstado(Long id, Long estado) {
        try {
            habitacionRepository.updateEstado(id, estado);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Object crear(Object o, List lista) {
        Habitacion habitacion = (Habitacion) o;
        List<Servicio> servicios = lista;

        try {

            //Si esta actualizando elimine los servicios viejos
            if(habitacion.getId() != null) {
                habitacionServicioRepository.eliminar(habitacion);
            }

            habitacion = (Habitacion) this.crear(habitacion);

            HabitacionServicio habitacionServicio = new HabitacionServicio();
            habitacionServicio.setHabitacion(habitacion);
            habitacionServicio.setServicios(servicios);
            return habitacionServicioRepository.crear(habitacionServicio);

        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

}
