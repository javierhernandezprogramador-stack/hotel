package org.jadez.apiservlet.webapp.hotel.repositories;

import org.jadez.apiservlet.webapp.hotel.models.Habitacion;
import org.jadez.apiservlet.webapp.hotel.models.Servicio;
import org.jadez.apiservlet.webapp.hotel.models.TipoServicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicioRepositoryImpl implements Repository<Servicio> {
    private Connection conn;

    public ServicioRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Servicio> listar() throws SQLException {
        List<Servicio> servicios = new ArrayList<>();
        String sql = "SELECT s.*, TRUNCATE(s.precio, 2) AS precio, t.nombre AS nombre_tipo_servicio, t.descripcion AS descripcion_tipo_servicio, t.estado AS estado_tipo_servicio FROM servicio s INNER JOIN tipo_servicio t ON s.tipo = t.id";

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                Servicio servicio = getServicio(rs);
                servicios.add(servicio);
            }
        }

        return servicios;
    }

    @Override
    public Servicio crear(Servicio servicio) throws SQLException {
        String sql = (servicio.getId() != null) ? "UPDATE servicio SET nombre = ?, precio = ?, descripcion = ?, estado = ?, tipo = ? WHERE id = ?" : "INSERT INTO servicio(nombre,precio,descripcion,estado,tipo) VALUES(?,?,?,?,?)";

        try(PreparedStatement stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, servicio.getNombre());
            stmt.setDouble(2, servicio.getPrecio());
            stmt.setString(3, servicio.getDescripcion());
            stmt.setLong(4, servicio.getEstado());
            stmt.setLong(5, servicio.getTipoServicio().getId());

            if(servicio.getId() != null) {
                stmt.setLong(6, servicio.getId());
            }
            stmt.executeUpdate();
            servicio.setId(obtenerPrimaryKey(servicio, stmt));
        }

        return servicio;
    }

    @Override
    public Servicio porId(Long id) throws SQLException {
        Servicio servicio = null;
        String sql = "SELECT s.*, TRUNCATE(s.precio, 2) AS precio, t.nombre AS nombre_tipo_servicio, t.descripcion AS descripcion_tipo_servicio, t.estado AS estado_tipo_servicio FROM servicio s INNER JOIN tipo_servicio t ON s.tipo = t.id WHERE s.id = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    servicio = getServicio(rs);
                }
            }
        }

        return servicio;
    }

    @Override
    public void updateEstado(Long id, Long estado) throws SQLException {
        String sql = "UPDATE servicio SET estado = ? WHERE id = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, estado);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    private static Servicio getServicio(ResultSet rs) throws SQLException {
        TipoServicio tipoServicio = getTipoServicio(rs);

        Servicio servicio = new Servicio();
        servicio.setId(rs.getLong("id"));
        servicio.setNombre(rs.getString("nombre"));
        servicio.setPrecio(rs.getDouble("precio"));
        servicio.setDescripcion(rs.getString("descripcion"));
        servicio.setEstado(rs.getLong("estado"));
        servicio.setTipoServicio(tipoServicio);
        return servicio;
    }

    private static TipoServicio getTipoServicio(ResultSet rs) throws SQLException {
        TipoServicio tipoServicio = new TipoServicio();
        tipoServicio.setId(rs.getLong("tipo"));
        tipoServicio.setNombre(rs.getString("nombre_tipo_servicio"));
        tipoServicio.setDescripcion(rs.getString("descripcion_tipo_Servicio"));
        tipoServicio.setEstado(rs.getLong("estado_tipo_servicio"));
        return tipoServicio;
    }

    private static Long obtenerPrimaryKey(Servicio servicio, Statement stmt) throws SQLException{
        if (servicio.getId() == null) {
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return servicio.getId();
    }
}
