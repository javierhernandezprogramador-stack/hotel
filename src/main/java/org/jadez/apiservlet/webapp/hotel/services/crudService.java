package org.jadez.apiservlet.webapp.hotel.services;

import java.util.List;
import java.util.Optional;

public interface crudService<T> {
    List<T> listar();

    T crear(T t);

    Optional<T> porId(Long id);

    void updateEstado(Long id, Long estado);

}
