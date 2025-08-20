package org.jadez.apiservlet.webapp.hotel.services;

import org.jadez.apiservlet.webapp.hotel.models.TipoServicio;
import org.jadez.apiservlet.webapp.hotel.repositories.Repository;
import org.jadez.apiservlet.webapp.hotel.repositories.TipoServicioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TipoServicioServiceImpl implements Service<TipoServicio>{
    private Repository<TipoServicio> repository;

    public TipoServicioServiceImpl(Connection conn) {
        this.repository = new TipoServicioRepositoryImpl(conn);
    }

    @Override
    public List<TipoServicio> listar() {
        try {
            return repository.listar();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public TipoServicio crear(TipoServicio tipoServicio) {
        try {
            return repository.crear(tipoServicio);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }

    }

    @Override
    public Optional<TipoServicio> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void updateEstado(Long id, Long estado) {
        try {
            repository.updateEstado(id, estado);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
