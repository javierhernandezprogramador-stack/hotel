package org.jadez.apiservlet.webapp.hotel.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.config.Service;
import org.jadez.apiservlet.webapp.hotel.entity.Rol;
import org.jadez.apiservlet.webapp.hotel.repositories.CrudRepositoryRol;
import org.jadez.apiservlet.webapp.hotel.repositories.RepositoryJpa;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Stateless
public class RolCrudServiceImpl implements CrudServiceRol {

    @Inject
    @RepositoryJpa
    private CrudRepositoryRol repository;

    @Override
    public Optional<Rol> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
