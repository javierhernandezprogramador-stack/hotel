package org.jadez.apiservlet.webapp.hotel.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.config.Service;
import org.jadez.apiservlet.webapp.hotel.entity.TipoServicio;
import org.jadez.apiservlet.webapp.hotel.repositories.CrudRepository;
import org.jadez.apiservlet.webapp.hotel.repositories.RepositoryJpa;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Stateless
public class TipoServicioCrudServiceImpl implements CrudService<TipoServicio> {

    @Inject
    @RepositoryJpa
    private CrudRepository<TipoServicio> crudRepository;

    @Override
    public List<TipoServicio> listar() {
        try {
            return crudRepository.listar();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public TipoServicio crear(TipoServicio tipoServicio) {
        try {
            return crudRepository.crear(tipoServicio);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public Optional<TipoServicio> porId(Long id) {
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
