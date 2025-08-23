package org.jadez.apiservlet.webapp.hotel.services;

import java.util.List;

public interface crudServiceHabitacion<T> extends crudService {
    T crear(T t, List<T> lista);
}
