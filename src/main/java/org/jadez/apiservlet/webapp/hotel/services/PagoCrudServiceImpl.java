package org.jadez.apiservlet.webapp.hotel.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.config.Service;
import org.jadez.apiservlet.webapp.hotel.entity.Pago;
import org.jadez.apiservlet.webapp.hotel.repositories.CrudRepository;
import org.jadez.apiservlet.webapp.hotel.repositories.RepositoryJpa;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Stateless
public class PagoCrudServiceImpl implements CrudService<Pago> {

    @Inject
    @RepositoryJpa
    private CrudRepository<Pago> pagocrudRepository;

    @Override
    public List<Pago> listar() {
        try {
            return pagocrudRepository.listar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Pago crear(Pago pago) {
        try {
            return pagocrudRepository.crear(pago);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Pago> porId(Long id) {
        try {
            return Optional.ofNullable(pagocrudRepository.porId(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEstado(Long id, Long estado) {
        try {
            pagocrudRepository.updateEstado(id, estado);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
