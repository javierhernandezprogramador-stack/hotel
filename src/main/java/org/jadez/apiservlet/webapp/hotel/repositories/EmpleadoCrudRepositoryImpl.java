package org.jadez.apiservlet.webapp.hotel.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jadez.apiservlet.webapp.hotel.config.MysqlConn;
import org.jadez.apiservlet.webapp.hotel.entity.Empleado;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EmpleadoCrudRepositoryImpl implements crudRepository<Empleado>{

    private Connection conn;

    @Inject
    private EntityManager em;

    public EmpleadoCrudRepositoryImpl(@MysqlConn Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Empleado> listar() throws SQLException {
        return em.createQuery("SELECT e FROM Empleado e", Empleado.class).getResultList();
    }

    @Override
    public Empleado crear(Empleado empleado) throws SQLException {
        if(empleado.getId() != null) {
            em.merge(empleado);
        }else {
            em.persist(empleado);
        }

        return empleado;
    }

    @Override
    public Empleado porId(Long id) throws SQLException {
        return em.find(Empleado.class, id);
    }

    @Override
    public void updateEstado(Long id, Long estado) throws SQLException {
        em.createQuery("UPDATE Empleado e SET e.estado = :estado WHERE e.id = :id")
                .setParameter("estado", estado)
                .setParameter("id", id)
                .executeUpdate();
    }
}
