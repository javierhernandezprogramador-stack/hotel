package org.jadez.apiservlet.webapp.hotel.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.config.Service;
import org.jadez.apiservlet.webapp.hotel.entity.Habitacion;
import org.jadez.apiservlet.webapp.hotel.repositories.CrudRepository;
import org.jadez.apiservlet.webapp.hotel.repositories.RepositoryJpa;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Stateless
public class HabitacionCrudServiceImpl implements CrudService<Habitacion> {
    @Inject
    @RepositoryJpa
    private CrudRepository<Habitacion> repository;

    @Override
    public List<Habitacion> listar() {
        try {
            return repository.listar();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Habitacion crear(Habitacion habitacion) {
        try {
            return repository.crear(habitacion);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Habitacion> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void updateEstado(Long id, Long estado) {

    }
}
