package org.jadez.apiservlet.webapp.hotel.services;

import org.jadez.apiservlet.webapp.hotel.models.Servicio;
import org.jadez.apiservlet.webapp.hotel.repositories.Repository;
import org.jadez.apiservlet.webapp.hotel.repositories.ServicioRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ServicioServiceImpl implements Service<Servicio>{
    private Repository<Servicio> repository;

    public ServicioServiceImpl(Connection conn) {
        repository = new ServicioRepositoryImpl(conn);
    }

    @Override
    public List<Servicio> listar() {
        try {
            return repository.listar();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Servicio crear(Servicio servicio) {
        try {
            return repository.crear(servicio);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Servicio> porId(Long id) {
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
