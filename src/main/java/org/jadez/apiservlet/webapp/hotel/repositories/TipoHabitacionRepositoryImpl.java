package org.jadez.apiservlet.webapp.hotel.repositories;

import org.jadez.apiservlet.webapp.hotel.models.Habitacion;
import org.jadez.apiservlet.webapp.hotel.models.TipoHabitacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoHabitacionRepositoryImpl implements Repository<TipoHabitacion> {
    private Connection conn;

    public TipoHabitacionRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<TipoHabitacion> listar() throws SQLException {
        List<TipoHabitacion> tipoHabitaciones = new ArrayList<>();
        String sql = "SELECT * FROM tipo_habitacion";

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                tipoHabitaciones.add(getTipoHabitacion(rs));
            }
        }

        return tipoHabitaciones;
    }

    @Override
    public TipoHabitacion crear(TipoHabitacion tipoHabitacion) throws SQLException {
        String sql = (tipoHabitacion.getId() != null) ? "UPDATE tipo_habitacion SET nombre = ?, descripcion = ?, estado = ? WHERE id = ?" : "INSERT INTO tipo_habitacion(nombre, descripcion, estado) VALUES(?,?,?)";

        try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tipoHabitacion.getNombre());
            stmt.setString(2, tipoHabitacion.getDescripcion());
            stmt.setLong(3, tipoHabitacion.getEstado());

            if(tipoHabitacion.getId() != null) {
                stmt.setLong(4, tipoHabitacion.getId());
            }
            stmt.executeUpdate();
            tipoHabitacion.setId(obtenerPrimaryKey(tipoHabitacion, stmt));
        }
        return tipoHabitacion;
    }

    @Override
    public TipoHabitacion porId(Long id) throws SQLException {
        TipoHabitacion tipoHabitacion = null;
        String sql = "SELECT * FROM tipo_habitacion WHERE id = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    tipoHabitacion = getTipoHabitacion(rs);
                }
            }
        }

        return tipoHabitacion;
    }

    @Override
    public void updateEstado(Long id, Long estado) throws SQLException {
        String sql = "UPDATE tipo_habitacion SET estado = ? WHERE id = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, estado);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    private static TipoHabitacion getTipoHabitacion(ResultSet rs) throws SQLException {
        TipoHabitacion tipoHabitacion = new TipoHabitacion();
        tipoHabitacion.setId(rs.getLong("id"));
        tipoHabitacion.setNombre(rs.getString("nombre"));
        tipoHabitacion.setDescripcion(rs.getString("descripcion"));
        tipoHabitacion.setEstado(rs.getLong("estado"));
        return tipoHabitacion;
    }

    private static Long obtenerPrimaryKey(TipoHabitacion tipoHabitacion, Statement stmt) throws SQLException{
        if (tipoHabitacion.getId() == null) {
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return tipoHabitacion.getId();
    }
}
