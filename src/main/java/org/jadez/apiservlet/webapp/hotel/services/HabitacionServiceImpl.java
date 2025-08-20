package org.jadez.apiservlet.webapp.hotel.services;

import org.jadez.apiservlet.webapp.hotel.models.Habitacion;
import org.jadez.apiservlet.webapp.hotel.repositories.HabitacionRepositoryImpl;
import org.jadez.apiservlet.webapp.hotel.repositories.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class HabitacionServiceImpl implements Service<Habitacion>{
    private Repository<Habitacion> repository;

    public HabitacionServiceImpl(Connection conn) {
        repository = new HabitacionRepositoryImpl(conn);
    }

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
        return Optional.empty();
    }

    @Override
    public void updateEstado(Long id, Long estado) {

    }
}
