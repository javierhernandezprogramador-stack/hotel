package org.jadez.apiservlet.webapp.hotel.services;

import org.jadez.apiservlet.webapp.hotel.entity.Usuario;

import java.util.Optional;

public interface CrudServiceUsuario {
    Optional<Usuario> porId(Long id);
}
