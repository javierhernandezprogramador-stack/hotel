package org.jadez.apiservlet.webapp.hotel.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jadez.apiservlet.webapp.hotel.config.Repository;
import org.jadez.apiservlet.webapp.hotel.entity.Servicio;

import java.sql.*;
import java.util.List;

@RepositoryJpa
@Repository
public class ServicioCrudRepositoryImpl implements CrudRepository<Servicio> {
    @Inject
    private EntityManager em;

    @Override
    public List<Servicio> listar() throws SQLException {
        return em.createQuery("SELECT s FROM Servicio s", Servicio.class).getResultList();
    }

    @Override
    public Servicio crear(Servicio servicio) throws SQLException {

        if(servicio.getId() != null) {
            em.merge(servicio);
        }else {
            em.persist(servicio);
        }

        return servicio;
    }

    @Override
    public Servicio porId(Long id) throws SQLException {
        return em.find(Servicio.class, id);
    }

    @Override
    public void updateEstado(Long id, Long estado) throws SQLException {
        em.createQuery("UPDATE Servicio s SET s.estado = :estado WHERE s.id = :id")
                .setParameter(":estado", estado)
                .setParameter(":id", id)
                .executeUpdate();
    }
}
