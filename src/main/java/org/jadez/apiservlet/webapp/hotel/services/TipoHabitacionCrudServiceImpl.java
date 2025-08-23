package org.jadez.apiservlet.webapp.hotel.services;

import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.config.Service;
import org.jadez.apiservlet.webapp.hotel.models.TipoHabitacion;
import org.jadez.apiservlet.webapp.hotel.repositories.crudRepository;
import org.jadez.apiservlet.webapp.hotel.repositories.TipoHabitacionCrudRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class TipoHabitacionCrudServiceImpl implements crudService<TipoHabitacion> {

    @Inject
    private crudRepository<TipoHabitacion> crudRepository;

    @Override
    public List<TipoHabitacion> listar() {
        try {
            return crudRepository.listar();
        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public TipoHabitacion crear(TipoHabitacion tipoHabitacion) {
        try {
            return crudRepository.crear(tipoHabitacion);
        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<TipoHabitacion> porId(Long id) {
        try {
            return Optional.ofNullable(crudRepository.porId(id));
        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void updateEstado(Long id, Long estado) {
        try {
            crudRepository.updateEstado(id, estado);
        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
