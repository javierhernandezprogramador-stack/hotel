package org.jadez.apiservlet.webapp.hotel.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Service <T> {
    List<T> listar();

    T crear(T t);

    Optional<T> porId(Long id);

    void updateEstado(Long id, Long estado);

}
