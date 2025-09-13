package org.jadez.apiservlet.webapp.hotel.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jadez.apiservlet.webapp.hotel.config.MysqlConn;
import org.jadez.apiservlet.webapp.hotel.entity.TipoPago;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TipoPagoCrudRepositoryImpl implements crudRepository<TipoPago>{

    private Connection conn;

    @Inject
    private EntityManager em;

    public TipoPagoCrudRepositoryImpl(@MysqlConn Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<TipoPago> listar() throws SQLException {
        return em.createQuery("SELECT t TipoPago t", TipoPago.class).getResultList();
    }

    @Override
    public TipoPago crear(TipoPago tipoPago) throws SQLException {

        if(tipoPago.getId() != null) {
            em.merge(tipoPago);
        }else {
            em.persist(tipoPago);
        }

        return tipoPago;
    }

    @Override
    public TipoPago porId(Long id) throws SQLException {
        return em.find(TipoPago.class, id);
    }

    @Override
    public void updateEstado(Long id, Long estado) throws SQLException {
        em.createQuery("UPDATE TipoPago t SET t.estado = :estado WHERE id = :id")
                .setParameter("estado", estado)
                .setParameter("id", id)
                .executeUpdate();
    }
}
