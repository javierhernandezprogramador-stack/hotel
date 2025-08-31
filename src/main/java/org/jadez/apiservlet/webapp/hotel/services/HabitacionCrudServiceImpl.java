package org.jadez.apiservlet.webapp.hotel.services;

import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.config.Service;
import org.jadez.apiservlet.webapp.hotel.interceptors.Transactional;
import org.jadez.apiservlet.webapp.hotel.entity.Habitacion;
import org.jadez.apiservlet.webapp.hotel.repositories.crudRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HabitacionCrudServiceImpl implements crudService<Habitacion> {
    @Inject
    private crudRepository<Habitacion> crudRepository;

    @Override
    public List<Habitacion> listar() {
        try {
            return crudRepository.listar();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Habitacion crear(Habitacion habitacion) {
        try {
            return crudRepository.crear(habitacion);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Habitacion> porId(Long id) {
        return Optional.empty();
    }

    @Override
    public void updateEstado(Long id, Long estado) {

    }
}
