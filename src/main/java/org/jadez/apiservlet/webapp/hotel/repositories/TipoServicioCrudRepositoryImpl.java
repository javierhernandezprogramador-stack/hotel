package org.jadez.apiservlet.webapp.hotel.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jadez.apiservlet.webapp.hotel.config.Repository;
import org.jadez.apiservlet.webapp.hotel.entity.TipoServicio;

import java.sql.*;
import java.util.List;

@RepositoryJpa
@Repository
public class TipoServicioCrudRepositoryImpl implements CrudRepository<TipoServicio> {
    @Inject
    private EntityManager em;

    @Override
    public List<TipoServicio> listar() throws SQLException {
        return em.createQuery("SELECT t FROM TipoServicio t", TipoServicio.class).getResultList();
    }

    @Override
    public TipoServicio crear(TipoServicio tipoServicio) throws SQLException {

        if(tipoServicio.getId() != null) {
            em.merge(tipoServicio);
        }else {
            em.persist(tipoServicio);
        }

        return tipoServicio;
    }

    @Override
    public TipoServicio porId(Long id) throws SQLException {
        return em.find(TipoServicio.class, id);
    }

    @Override
    public void updateEstado(Long id, Long estado) throws SQLException {
        em.createQuery("UPDATE TipoServicio t SET t.estado = :estado WHERE t.id = :id")
                .setParameter("estado", estado)
                .setParameter("id", id)
                .executeUpdate();
    }
}
