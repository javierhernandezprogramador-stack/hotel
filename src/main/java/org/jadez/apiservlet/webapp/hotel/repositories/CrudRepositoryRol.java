package org.jadez.apiservlet.webapp.hotel.repositories;

import org.jadez.apiservlet.webapp.hotel.entity.Rol;

import java.sql.SQLException;

public interface CrudRepositoryRol {
    Rol porId(Long id) throws SQLException;
}
