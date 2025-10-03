package org.jadez.apiservlet.webapp.hotel.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.config.Service;
import org.jadez.apiservlet.webapp.hotel.entity.TipoHabitacion;
import org.jadez.apiservlet.webapp.hotel.repositories.CrudRepository;
import org.jadez.apiservlet.webapp.hotel.repositories.RepositoryJpa;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Stateless
public class TipoHabitacionCrudServiceImpl implements CrudService<TipoHabitacion> {

    @Inject
    @RepositoryJpa
    private CrudRepository<TipoHabitacion> repository;

    @Override
    public List<TipoHabitacion> listar() {
        try {
            return repository.listar();
        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public TipoHabitacion crear(TipoHabitacion tipoHabitacion) {
        try {
            return repository.crear(tipoHabitacion);
        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<TipoHabitacion> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void updateEstado(Long id, Long estado) {
        try {
            repository.updateEstado(id, estado);
        }catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
