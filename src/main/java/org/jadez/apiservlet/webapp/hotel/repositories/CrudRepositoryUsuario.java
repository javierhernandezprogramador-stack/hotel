package org.jadez.apiservlet.webapp.hotel.repositories;

import org.jadez.apiservlet.webapp.hotel.entity.Usuario;

import java.sql.SQLException;

public interface CrudRepositoryUsuario extends CrudRepository<Usuario> {
    void cambiarPassword(Usuario t) throws SQLException;
}
