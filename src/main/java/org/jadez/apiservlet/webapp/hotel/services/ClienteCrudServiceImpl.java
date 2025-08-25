package org.jadez.apiservlet.webapp.hotel.services;

import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.config.Service;
import org.jadez.apiservlet.webapp.hotel.interceptors.Transactional;
import org.jadez.apiservlet.webapp.hotel.models.Cliente;
import org.jadez.apiservlet.webapp.hotel.models.Usuario;
import org.jadez.apiservlet.webapp.hotel.repositories.ClienteCrudRepositoryImpl;
import org.jadez.apiservlet.webapp.hotel.repositories.crudRepository;
import org.jadez.apiservlet.webapp.hotel.repositories.crudRepositoryUsuario;
import org.jadez.apiservlet.webapp.hotel.repositories.UsuarioRepositoryimpl;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteCrudServiceImpl implements crudService<Cliente> {

    @Inject
    private crudRepositoryUsuario repositoryUsuario;

    @Inject
    private crudRepository<Cliente> crudRepositoryCliente;

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
                cliente.getUsuario().setPassword(generarPassword());
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

    private String generarPassword() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String symbols = "!@#$%^&*()-_=+[]{}|;:,.<>?";
        SecureRandom random = new SecureRandom();
        int length = 12;
        String all = upper + lower + digits + symbols;
        StringBuilder password = new StringBuilder();

        // Garantizar al menos un carácter de cada tipo
        password.append(upper.charAt(random.nextInt(upper.length())));
        password.append(lower.charAt(random.nextInt(lower.length())));
        password.append(digits.charAt(random.nextInt(digits.length())));
        password.append(symbols.charAt(random.nextInt(symbols.length())));

        // Completar el resto de la longitud
        for (int i = 4; i < length; i++) {
            password.append(all.charAt(random.nextInt(all.length())));
        }

        // Mezclar los caracteres para evitar patrón fijo
        return shuffle(password.toString(), random);
    }

    private String shuffle(String input, SecureRandom random) {
        char[] array = input.toCharArray();

        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return new String(array);
    }
}
