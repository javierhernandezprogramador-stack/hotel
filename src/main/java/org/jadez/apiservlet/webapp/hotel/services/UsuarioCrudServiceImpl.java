package org.jadez.apiservlet.webapp.hotel.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.config.Service;
import org.jadez.apiservlet.webapp.hotel.entity.Usuario;
import org.jadez.apiservlet.webapp.hotel.repositories.CrudRepositoryUsuario;
import org.jadez.apiservlet.webapp.hotel.repositories.RepositoryJpa;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Stateless
public class UsuarioCrudServiceImpl implements CrudServiceUsuario {

    @Inject
    @RepositoryJpa
    private CrudRepositoryUsuario repository;

    @Override
    public Optional<Usuario> porId(Long id) {
        try {
            return Optional.ofNullable(repository.porId(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
