package org.jadez.apiservlet.webapp.hotel.services;

import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.config.Service;
import org.jadez.apiservlet.webapp.hotel.entity.Cliente;
import org.jadez.apiservlet.webapp.hotel.entity.Empleado;
import org.jadez.apiservlet.webapp.hotel.entity.Usuario;
import org.jadez.apiservlet.webapp.hotel.interceptors.Transactional;
import org.jadez.apiservlet.webapp.hotel.repositories.crudRepository;
import org.jadez.apiservlet.webapp.hotel.repositories.crudRepositoryUsuario;
import org.jadez.apiservlet.webapp.hotel.utils.GenerarPassword;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmpleadoCrudServiceImpl implements crudService<Empleado> {

    @Inject
    private crudRepositoryUsuario repositoryUsuario;

    @Inject
    private crudRepository<Empleado> crudRepositoryEmpleado;

    @Inject
    private GenerarPassword password;

    @Override
    public List<Empleado> listar() {
        try {
            return crudRepositoryEmpleado.listar();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Empleado crear(Empleado empleado) {
        try {
            if (empleado.getId() == null) {
                empleado.getUsuario().setPassword(password.generarPassword());
            }
            Usuario usuario = repositoryUsuario.crear(empleado.getUsuario());
            empleado.setUsuario(usuario);

            return crudRepositoryEmpleado.crear(empleado);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Empleado> porId(Long id) {
        try {
            return Optional.ofNullable(crudRepositoryEmpleado.porId(id));
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void updateEstado(Long id, Long estado) {//aqui necesito mandar el objeto
        try {
            crudRepositoryEmpleado.updateEstado(id, estado);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

}
