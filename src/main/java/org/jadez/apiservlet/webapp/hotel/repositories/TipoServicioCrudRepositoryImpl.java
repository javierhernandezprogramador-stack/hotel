package org.jadez.apiservlet.webapp.hotel.repositories;

import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.config.MysqlConn;
import org.jadez.apiservlet.webapp.hotel.config.Repository;
import org.jadez.apiservlet.webapp.hotel.models.TipoServicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TipoServicioCrudRepositoryImpl implements crudRepository<TipoServicio> {
    private Connection conn;

    @Inject
    public TipoServicioCrudRepositoryImpl(@MysqlConn Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<TipoServicio> listar() throws SQLException {
        List<TipoServicio> tipoServicios = new ArrayList<>();
        String sql = "SELECT * FROM tipo_servicio";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {

            while (rs.next()) {
                tipoServicios.add(getTipoServicio(rs));
            }
        }

        return tipoServicios;
    }

    @Override
    public TipoServicio crear(TipoServicio tipoServicio) throws SQLException {
        String sql = (tipoServicio.getId() != null) ? "UPDATE tipo_servicio SET nombre = ?, estado = ?, descripcion = ? WHERE id = ?" : "INSERT INTO tipo_servicio(nombre, estado, descripcion) VALUES(?,?,?)";

        try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, tipoServicio.getNombre());
            stmt.setLong(2, tipoServicio.getEstado());
            stmt.setString(3, tipoServicio.getDescripcion());

            if(tipoServicio.getId() != null) {
                stmt.setLong(4, tipoServicio.getId());
            }
            stmt.executeUpdate();
            tipoServicio.setId(obtenerPrimaryKey(tipoServicio, stmt));
        }
        return tipoServicio;
    }

    @Override
    public TipoServicio porId(Long id) throws SQLException {
        TipoServicio tipoServicio = null;
        String sql = "SELECT * FROM tipo_servicio WHERE id = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    tipoServicio = getTipoServicio(rs);
                }
            }
        }

        return tipoServicio;
    }

    @Override
    public void updateEstado(Long id, Long estado) throws SQLException {
        String sql = "UPDATE tipo_servicio SET estado = ? WHERE id = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, estado);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    private static TipoServicio getTipoServicio(ResultSet rs) throws SQLException {
        TipoServicio tipoServicio = new TipoServicio();
        tipoServicio.setId(rs.getLong("id"));
        tipoServicio.setNombre(rs.getString("nombre"));
        tipoServicio.setEstado(rs.getLong("estado"));
        tipoServicio.setDescripcion(rs.getString("descripcion"));
        return tipoServicio;
    }

    private static Long obtenerPrimaryKey(TipoServicio tipoServicio, Statement stmt) throws SQLException{
        if (tipoServicio.getId() == null) {
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return tipoServicio.getId();
    }
}
