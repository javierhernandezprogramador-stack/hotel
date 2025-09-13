package org.jadez.apiservlet.webapp.hotel.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jadez.apiservlet.webapp.hotel.config.MysqlConn;
import org.jadez.apiservlet.webapp.hotel.config.Repository;
import org.jadez.apiservlet.webapp.hotel.entity.Rol;
import org.jadez.apiservlet.webapp.hotel.entity.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepositoryimpl implements crudRepositoryUsuario {
    private Connection conn;

    @Inject
    private EntityManager em;

    @Inject
    public UsuarioRepositoryimpl(@MysqlConn Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        return em.createQuery("SELECT u FROM Usuario", Usuario.class).getResultList();
    }

    //Esta esta siendo usada por service
    @Override
    public Usuario crear(Usuario usuario) throws SQLException {
        if(usuario.getId() != null) {
            em.merge(usuario);
        }else {
            em.persist(usuario);
        }

        return usuario;
    }

    @Override
    public Usuario porId(Long id) throws SQLException {
        return em.find(Usuario.class, id);
    }

    //esto deberia de ser utulizado en el service
    @Override
    public void updateEstado(Long id, Long estado) throws SQLException {
        em.createQuery("UPDATE Usuario u SET u.estado = :estado WHERE u.id = :id")
                .setParameter("estado", estado)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void cambiarPassword(Usuario usuario) throws SQLException {
        em.createQuery("UPDATE Usuario u SET u.password = :password WHERE u.email = :email")
                .setParameter("password", usuario.getPassword())
                .setParameter("email", usuario.getEmail())
                .executeUpdate();
    }
}
