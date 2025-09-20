package org.jadez.apiservlet.webapp.hotel.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jadez.apiservlet.webapp.hotel.config.Repository;
import org.jadez.apiservlet.webapp.hotel.entity.TipoHabitacion;

import java.sql.*;
import java.util.List;

@RepositoryJpa
@Repository
public class TipoHabitacionCrudRepositoryImpl implements CrudRepository<TipoHabitacion> {
    @Inject
    private EntityManager em;

    @Override
    public List<TipoHabitacion> listar() throws SQLException {
        return em.createQuery("SELECT t FROM TipoHabitacion t", TipoHabitacion.class).getResultList();
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
        em.createQuery("UPDATE TipoHabitacion t SET t.estado : estado WHERE t.id = :id")
                .setParameter(":estado", estado)
                .setParameter(":id", id)
                .executeUpdate();
    }
}
