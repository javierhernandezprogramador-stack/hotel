package org.jadez.apiservlet.webapp.hotel.services;

import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.config.Service;
import org.jadez.apiservlet.webapp.hotel.interceptors.Transactional;
import org.jadez.apiservlet.webapp.hotel.entity.Habitacion;
import org.jadez.apiservlet.webapp.hotel.entity.HabitacionServicio;
import org.jadez.apiservlet.webapp.hotel.entity.Servicio;
import org.jadez.apiservlet.webapp.hotel.repositories.crudRepository;
import org.jadez.apiservlet.webapp.hotel.repositories.crudRepositoryHabitacionServicio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HabitacionServiceCrudServiceImpl implements crudServiceHabitacion {

    @Inject
    private crudRepositoryHabitacionServicio habitacionServicioRepository;

    @Inject
    private crudRepository<Habitacion> habitacionCrudRepository;

    @Override
    public List listar() {
        List<Habitacion> habitaciones;
        List<Servicio> servicios;
        List<HabitacionServicio> habitacionServicios = new ArrayList<>();

        try {
            habitaciones = habitacionCrudRepository.listar();

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
            return habitacionCrudRepository.crear(habitacion);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional porId(Long id) {
        List<Servicio> servicios;
        HabitacionServicio habitacionServicio = new HabitacionServicio();

        try {
            Habitacion habitacion = habitacionCrudRepository.porId(id);
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
            habitacionCrudRepository.updateEstado(id, estado);
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
