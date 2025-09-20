package org.jadez.apiservlet.webapp.hotel.repositories;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository<T> {

    List<T> listar() throws SQLException;

    T crear(T t) throws SQLException;

    T porId(Long id) throws SQLException;

    void updateEstado(Long id, Long estado) throws SQLException;
}
