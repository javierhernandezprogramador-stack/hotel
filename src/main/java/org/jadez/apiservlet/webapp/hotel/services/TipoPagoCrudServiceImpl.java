package org.jadez.apiservlet.webapp.hotel.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.config.Service;
import org.jadez.apiservlet.webapp.hotel.entity.TipoPago;
import org.jadez.apiservlet.webapp.hotel.repositories.CrudRepository;
import org.jadez.apiservlet.webapp.hotel.repositories.RepositoryJpa;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Stateless
public class TipoPagoCrudServiceImpl implements CrudService<TipoPago> {

    @Inject
    @RepositoryJpa
    private CrudRepository<TipoPago> tipoPagocrudRepository;

    @Override
    public List<TipoPago> listar() {
        try {
            return tipoPagocrudRepository.listar();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public TipoPago crear(TipoPago tipoPago) {
        try {
            return tipoPagocrudRepository.crear(tipoPago);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<TipoPago> porId(Long id) {
        try {
            return Optional.ofNullable(tipoPagocrudRepository.porId(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEstado(Long id, Long estado) {
        try {
            tipoPagocrudRepository.updateEstado(id, estado);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
