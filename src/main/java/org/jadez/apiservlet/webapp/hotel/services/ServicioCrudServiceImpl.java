package org.jadez.apiservlet.webapp.hotel.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.config.Service;
import org.jadez.apiservlet.webapp.hotel.entity.Servicio;
import org.jadez.apiservlet.webapp.hotel.repositories.CrudRepository;
import org.jadez.apiservlet.webapp.hotel.repositories.RepositoryJpa;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Stateless
public class ServicioCrudServiceImpl implements CrudService<Servicio> {

    @Inject
    @RepositoryJpa
    private CrudRepository<Servicio> crudRepository;

    @Override
    public List<Servicio> listar() {
        try {
            return crudRepository.listar();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Servicio crear(Servicio servicio) {
        try {
            return crudRepository.crear(servicio);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Servicio> porId(Long id) {
        try {
            return Optional.ofNullable(crudRepository.porId(id));
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void updateEstado(Long id, Long estado) {
        try {
            crudRepository.updateEstado(id, estado);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
