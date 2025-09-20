package org.jadez.apiservlet.webapp.hotel.services;

import jakarta.ejb.Local;

import java.util.List;

@Local
public interface CrudServiceHabitacion<T> extends CrudService {
    T crear(T t, List<T> lista);
}
