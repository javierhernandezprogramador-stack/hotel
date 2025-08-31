package org.jadez.apiservlet.webapp.hotel.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jadez.apiservlet.webapp.hotel.config.MysqlConn;
import org.jadez.apiservlet.webapp.hotel.config.Repository;
import org.jadez.apiservlet.webapp.hotel.entity.TipoHabitacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TipoHabitacionCrudRepositoryImpl implements crudRepository<TipoHabitacion> {
    private Connection conn;

    @Inject
    private EntityManager em;

    @Inject
    public TipoHabitacionCrudRepositoryImpl(@MysqlConn Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<TipoHabitacion> listar() throws SQLException {
        return em.createQuery("SELECT t FROM TipoHabitacion", TipoHabitacion.class).getResultList();
    }

    @Override
    public TipoHabitacion crear(TipoHabitacion tipoHabitacion) throws SQLException {

        if(tipoHabitacion.getId() != null) {
            em.merge(tipoHabitacion);
        }else {
            em.persist(tipoHabitacion);
        }

        return tipoHabitacion;
    }

    @Override
    public TipoHabitacion porId(Long id) throws SQLException {
        return em.find(TipoHabitacion.class, id);
    }

    @Override
    public void updateEstado(Long id, Long estado) throws SQLException {
        em.createQuery("UPDATE TipoHabitacion SET estado : estado WHERE id = :id")
                .setParameter(":estado", estado)
                .setParameter(":id", id)
                .executeUpdate();
    }
}
