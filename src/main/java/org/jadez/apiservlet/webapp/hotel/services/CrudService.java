package org.jadez.apiservlet.webapp.hotel.services;

import jakarta.ejb.Local;

import java.util.List;
import java.util.Optional;

@Local
public interface CrudService<T> {
    List<T> listar();

    T crear(T t);

    Optional<T> porId(Long id);

    void updateEstado(Long id, Long estado);

}
