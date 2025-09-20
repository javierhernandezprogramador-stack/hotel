package org.jadez.apiservlet.webapp.hotel.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jadez.apiservlet.webapp.hotel.config.Repository;
import org.jadez.apiservlet.webapp.hotel.entity.Cliente;

import java.sql.*;
import java.util.List;

@RepositoryJpa
@Repository
public class ClienteCrudRepositoryImpl implements CrudRepository<Cliente> {
    @Inject
    private EntityManager em;

    @Override
    public List<Cliente> listar() throws SQLException {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }

    @Override
    public Cliente crear(Cliente cliente) throws SQLException {
        if(cliente.getId() != null) {
            em.merge(cliente);
        }else {
            em.persist(cliente);
        }

        return cliente;
    }

    @Override
    public Cliente porId(Long id) throws SQLException {
        return em.find(Cliente.class, id);
    }

    @Override
    public void updateEstado(Long id, Long estado) throws SQLException {
        em.createQuery("UPDATE Cliente c SET c.estado = :estado WHERE c.id = :id")
                .setParameter("estado", estado)
                .setParameter("id", id)
                .executeUpdate();
    }
}
