package org.jadez.apiservlet.webapp.hotel.services;

import java.sql.SQLException;
import java.util.List;

public interface ServiceHabitacion<T> extends Service {
    T crear(T t, List<T> lista);
}
