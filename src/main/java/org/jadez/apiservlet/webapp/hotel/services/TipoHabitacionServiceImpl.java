package org.jadez.apiservlet.webapp.hotel.services;

import org.jadez.apiservlet.webapp.hotel.models.TipoHabitacion;
import org.jadez.apiservlet.webapp.hotel.repositories.Repository;
import org.jadez.apiservlet.webapp.hotel.repositories.TipoHabitacionRepositoryImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TipoHabitacionServiceImpl implements Service<TipoHabitacion>{
    private Repository<TipoHabitacion> repository;

    public TipoHabitacionServiceImpl(Connection conn) {
        this.repository = new TipoHabitacionRepositoryImpl(conn);
    }

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
