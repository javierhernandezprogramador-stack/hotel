package org.jadez.apiservlet.webapp.hotel.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jadez.apiservlet.webapp.hotel.config.MysqlConn;
import org.jadez.apiservlet.webapp.hotel.entity.Pago;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PagoCrudRepositoryimpl implements crudRepository<Pago>{
    private Connection conn;

    @Inject
    private EntityManager em;

    public PagoCrudRepositoryimpl(@MysqlConn Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Pago> listar() throws SQLException {
        return em.createQuery("SELECT p FROM Pago p").getResultList();
    }

    @Override
    public Pago crear(Pago pago) throws SQLException {
        if(pago.getId() != null) {
            em.merge(pago);
        }else {
            em.persist(pago);
        }

        return pago;
    }

    @Override
    public Pago porId(Long id) throws SQLException {
        return em.find(Pago.class, id);
    }

    @Override
    public void updateEstado(Long id, Long estado) throws SQLException {
        em.createQuery("UPDATE Pago p SET p.estado WHERE p.id = :id")
                .setParameter("estado", estado)
                .setParameter("id", id)
                .executeUpdate();
    }
}
