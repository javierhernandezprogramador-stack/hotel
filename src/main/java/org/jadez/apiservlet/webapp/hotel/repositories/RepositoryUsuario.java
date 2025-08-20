package org.jadez.apiservlet.webapp.hotel.repositories;

import org.jadez.apiservlet.webapp.hotel.models.Usuario;

import java.sql.SQLException;

public interface RepositoryUsuario extends Repository<Usuario>{
    void cambiarPassword(Usuario t) throws SQLException;
}
