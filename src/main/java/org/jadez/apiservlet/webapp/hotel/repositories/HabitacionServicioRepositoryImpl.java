package org.jadez.apiservlet.webapp.hotel.repositories;

import org.jadez.apiservlet.webapp.hotel.models.Habitacion;
import org.jadez.apiservlet.webapp.hotel.models.HabitacionServicio;
import org.jadez.apiservlet.webapp.hotel.models.Servicio;
import org.jadez.apiservlet.webapp.hotel.models.TipoServicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HabitacionServicioRepositoryImpl implements RepositoryHabitacionServicio {
    private Connection conn;

    public HabitacionServicioRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<HabitacionServicio> listar() throws SQLException {
        return List.of();
    }

    @Override
    public HabitacionServicio crear(HabitacionServicio habitacionServicio) throws SQLException {
        String sql = "INSERT INTO habitacion_servicio(servicio, habitacion) VALUES(?,?)";

        for (Servicio servicio : habitacionServicio.getServicios()) {

            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setLong(1, servicio.getId());
                stmt.setLong(2, habitacionServicio.getHabitacion().getId());
                stmt.executeUpdate();

                habitacionServicio.setId(obtenerPrimaryKey(habitacionServicio, stmt));
            }

        }


        return habitacionServicio;
    }

    @Override
    public HabitacionServicio porId(Long id) throws SQLException {
        return null;
    }

    @Override
    public void updateEstado(Long id, Long estado) throws SQLException {

    }

    private static Long obtenerPrimaryKey(HabitacionServicio habitacionServicio, Statement stmt) throws SQLException {
        if (habitacionServicio.getId() == null) {
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return null;
    }

    @Override
    public List<Servicio> obtenerServicios(Long habitacion) throws SQLException {
        List<Servicio> servicios = new ArrayList<>();
        String sql = "SELECT s.* FROM habitacion_servicio hs INNER JOIN servicio s ON hs.servicio = s.id WHERE habitacion = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, habitacion);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Servicio servicio = getServicio(rs);
                    servicios.add(servicio);
                }
            }
        }

        return servicios;
    }

    @Override
    public List<Servicio> obtenerServiciosPorHabitacion(Habitacion habitacion) throws SQLException {
        List<Servicio> servicios = new ArrayList<>();
        String sql = "SELECT s.*, hs.id AS pivote FROM habitacion_servicio hs INNER JOIN servicio s ON s.id = hs.servicio WHERE hs.habitacion = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, habitacion.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    servicios.add(getServicio(rs));
                }
            }
        }

        return servicios;
    }

    @Override
    public void eliminar(Habitacion habitacion) throws SQLException {
        String sql = "DELETE FROM habitacion_servicio WHERE habitacion = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, habitacion.getId());
            stmt.executeUpdate();
        }
    }


    private static Servicio getServicio(ResultSet rs) throws SQLException {
        //TipoServicio tipoServicio = getTipoServicio(rs);

        Servicio servicio = new Servicio();
        servicio.setId(rs.getLong("id"));
        servicio.setNombre(rs.getString("nombre"));
        servicio.setPrecio(rs.getDouble("precio"));
        servicio.setDescripcion(rs.getString("descripcion"));
        servicio.setEstado(rs.getLong("estado"));
        //servicio.setTipoServicio(tipoServicio);
        return servicio;
    }

    private static TipoServicio getTipoServicio(ResultSet rs) throws SQLException {
        TipoServicio tipoServicio = new TipoServicio();
        tipoServicio.setId(rs.getLong("tipo"));
        return tipoServicio;
    }

}
