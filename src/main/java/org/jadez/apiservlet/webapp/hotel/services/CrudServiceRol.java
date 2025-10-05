package org.jadez.apiservlet.webapp.hotel.services;

import org.jadez.apiservlet.webapp.hotel.entity.Rol;

import java.util.Optional;

public interface CrudServiceRol {
    Optional<Rol> porId(Long id);
}
