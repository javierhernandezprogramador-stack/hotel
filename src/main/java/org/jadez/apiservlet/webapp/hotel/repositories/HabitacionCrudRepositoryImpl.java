package org.jadez.apiservlet.webapp.hotel.repositories;

import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.config.MysqlConn;
import org.jadez.apiservlet.webapp.hotel.config.Repository;
import org.jadez.apiservlet.webapp.hotel.models.Habitacion;
import org.jadez.apiservlet.webapp.hotel.models.TipoHabitacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HabitacionCrudRepositoryImpl implements crudRepository<Habitacion> {
    private Connection conn;

    @Inject
    public HabitacionCrudRepositoryImpl(@MysqlConn Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Habitacion> listar() throws SQLException {
        List<Habitacion> habitaciones = new ArrayList<>();
        String sql = "SELECT h.*, th.nombre AS nombre_tipo_habitacion FROM habitacion h INNER JOIN tipo_habitacion th ON th.id = h.tipo";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                habitaciones.add(getHabitacion(rs));
            }
        }
        return habitaciones;
    }

    @Override
    public Habitacion crear(Habitacion habitacion) throws SQLException {
        String sql = (habitacion.getId() != null) ? "UPDATE habitacion SET numero_habitacion = ?, descripcion = ?, capacidad = ?, precio = ?, estado = ?, piso = ?, cama = ?, bw = ?, tipo = ?, imagen = ? WHERE id = ?" : "INSERT INTO habitacion(numero_habitacion, descripcion, capacidad, precio, estado, piso, cama, bw, tipo, imagen) VALUES(?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, habitacion.getNumeroHabitacion());
            stmt.setString(2, habitacion.getDescripcion());
            stmt.setInt(3, habitacion.getCapacidad());
            stmt.setDouble(4, habitacion.getPrecio());
            stmt.setLong(5, habitacion.getEstado());
            stmt.setString(6, habitacion.getPiso());
            stmt.setInt(7, habitacion.getCama());
            stmt.setInt(8, habitacion.getBw());
            stmt.setLong(9, habitacion.getTipoHabitacion().getId());
            stmt.setString(10, habitacion.getImagen());

            if (habitacion.getId() != null) {
                stmt.setLong(11, habitacion.getId());
            }

            stmt.executeUpdate();
            habitacion.setId(obtenerPrimaryKey(habitacion, stmt));

        }

        return habitacion;
    }

    @Override
    public Habitacion porId(Long id) throws SQLException {
        Habitacion habitacion = null;
        String sql = "SELECT h.*, th.nombre AS nombre_tipo_habitacion FROM habitacion h INNER JOIN tipo_habitacion th ON th.id = h.tipo WHERE h.id = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    habitacion = getHabitacion(rs);
                }
            }
        }

        return habitacion;
    }

    @Override
    public void updateEstado(Long id, Long estado) throws SQLException {
        String sql = "UPDATE habitacion SET estado = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1 , estado);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    private static Habitacion getHabitacion(ResultSet rs) throws SQLException {
        TipoHabitacion tipoHabitacion = getTipoHabitacion(rs);

        Habitacion habitacion = new Habitacion();
        habitacion.setId(rs.getLong("id"));
        habitacion.setNumeroHabitacion(rs.getString("numero_habitacion"));
        habitacion.setDescripcion(rs.getString("descripcion"));
        habitacion.setCapacidad(rs.getInt("capacidad"));
        habitacion.setPrecio(rs.getDouble("precio"));
        habitacion.setEstado(rs.getLong("estado"));
        habitacion.setPiso(rs.getString("piso"));
        habitacion.setCama(rs.getInt("cama"));
        habitacion.setBw(rs.getInt("bw"));
        habitacion.setImagen(rs.getString("imagen"));

        habitacion.setTipoHabitacion(tipoHabitacion);

        return habitacion;
    }

    private static TipoHabitacion getTipoHabitacion(ResultSet rs) throws SQLException {
        TipoHabitacion tipoHabitacion = new TipoHabitacion();
        tipoHabitacion.setId(rs.getLong("tipo"));
        tipoHabitacion.setNombre(rs.getString("nombre_tipo_habitacion"));
        return tipoHabitacion;
    }

    private static Long obtenerPrimaryKey(Habitacion habitacion, Statement stmt) throws SQLException{
        if (habitacion.getId() == null) {
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return habitacion.getId();
    }

}
