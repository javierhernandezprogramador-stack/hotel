package org.jadez.apiservlet.webapp.hotel.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jadez.apiservlet.webapp.hotel.config.Repository;
import org.jadez.apiservlet.webapp.hotel.entity.Rol;

import java.sql.SQLException;

@RepositoryJpa
@Repository
public class RolRepositoryImpl implements CrudRepositoryRol {

    @Inject
    private EntityManager em;

    @Override
    public Rol porId(Long id) throws SQLException {
        return em.find(Rol.class, id);
    }
}
