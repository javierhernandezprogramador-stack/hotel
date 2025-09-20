package org.jadez.apiservlet.webapp.hotel.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.config.Service;
import org.jadez.apiservlet.webapp.hotel.entity.Cliente;
import org.jadez.apiservlet.webapp.hotel.entity.Usuario;
import org.jadez.apiservlet.webapp.hotel.repositories.CrudRepository;
import org.jadez.apiservlet.webapp.hotel.repositories.CrudRepositoryUsuario;
import org.jadez.apiservlet.webapp.hotel.repositories.RepositoryJpa;
import org.jadez.apiservlet.webapp.hotel.utils.GenerarPassword;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Stateless
public class ClienteCrudServiceImpl implements CrudService<Cliente> {

    @Inject
    @RepositoryJpa
    private CrudRepositoryUsuario repositoryUsuario;

    @Inject
    @RepositoryJpa
    private CrudRepository<Cliente> crudRepositoryCliente;

    @Inject
    private GenerarPassword password;

    @Override
    public List<Cliente> listar() {
        try {
            return crudRepositoryCliente.listar();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Cliente crear(Cliente cliente) {
        try {
            if (cliente.getId() == null) {
                cliente.getUsuario().setPassword(password.generarPassword());
            }
            Usuario usuario = repositoryUsuario.crear(cliente.getUsuario());
            cliente.setUsuario(usuario);

            return crudRepositoryCliente.crear(cliente);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Cliente> porId(Long id) {
        try {
            return Optional.ofNullable(crudRepositoryCliente.porId(id));
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void updateEstado(Long id, Long estado) {//aqui necesito mandar el objeto
        try {
            crudRepositoryCliente.updateEstado(id, estado);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
