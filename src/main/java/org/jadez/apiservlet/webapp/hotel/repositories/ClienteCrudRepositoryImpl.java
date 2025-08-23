package org.jadez.apiservlet.webapp.hotel.repositories;

import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.config.MysqlConn;
import org.jadez.apiservlet.webapp.hotel.config.Repository;
import org.jadez.apiservlet.webapp.hotel.models.Cliente;
import org.jadez.apiservlet.webapp.hotel.models.Rol;
import org.jadez.apiservlet.webapp.hotel.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteCrudRepositoryImpl implements crudRepository<Cliente> {
    private Connection conn;

    @Inject
    public ClienteCrudRepositoryImpl(@MysqlConn Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Cliente> listar() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT c.*, u.password AS usuario_password, u.email AS usuario_email, u.estado AS usuario_estado, r.id AS rol, r.nombre AS rol_nombre FROM cliente c INNER JOIN usuario u ON u.id = c.usuario INNER JOIN rol r ON r.id = u.rol";

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                clientes.add(getCliente(rs));
            }
        }

        return clientes;
    }

    @Override
    public Cliente crear(Cliente cliente) throws SQLException {
        String sql = (cliente.getId() != null) ? "UPDATE cliente SET nombre = ?, apellido = ?, telefono = ?, dui = ?, pasaporte = ?, fecha_nacimiento = ?, nacionalidad = ?, usuario = ?, estado = ?) WHERE id = ?" : "INSERT INTO cliente(nombre, apellido, telefono, dui, pasaporte, fecha_nacimiento, nacionalidad, usuario, estado) VALUES(?,?,?,?,?,?,?,?,?)";

        try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getDui());
            stmt.setString(5, cliente.getPasaporte());
            stmt.setDate(6, Date.valueOf(cliente.getFechaNacimiento()));
            stmt.setString(7, cliente.getNacionalidad());
            stmt.setLong(8, cliente.getUsuario().getId());
            stmt.setLong(9, cliente.getEstado());

            if(cliente.getId() != null) {
                stmt.setLong(10, cliente.getId());
            }
            stmt.executeUpdate();
            cliente.setId(obtenerPrimaryKey(cliente, stmt));
        }

        return cliente;
    }

    @Override
    public Cliente porId(Long id) throws SQLException {
        String sql = "SELECT c.*, u.password AS usuario_password, u.email AS usuario_email, u.estado AS usuario_estado, r.id AS rol, r.nombre AS rol_nombre FROM cliente c INNER JOIN usuario u ON u.id = c.usuario INNER JOIN rol r ON r.id = u.rol WHERE c.id = ?";
        Cliente cliente = null;

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    cliente = new Cliente();
                    cliente = getCliente(rs);
                }
            }
        }
        return cliente;
    }

    @Override
    public void updateEstado(Long id, Long estado) throws SQLException {
        String sql = "UPDATE cliente SET estado = ? WHERE id = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, estado);
            stmt.setLong(2 ,id);
            stmt.executeUpdate();
        }
    }

    private static Cliente getCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("id"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setApellido(rs.getString("apellido"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setDui(rs.getString("dui"));
        cliente.setPasaporte(rs.getString("pasaporte"));
        cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
        cliente.setNacionalidad(rs.getString("nacionalidad"));
        cliente.setEstado(rs.getLong("estado"));
        cliente.setUsuario(getUsuario(rs));
        return cliente;
    }

    private static Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("usuario"));
        usuario.setEmail(rs.getString("usuario_email"));
        usuario.setPassword(rs.getString("usuario_password"));
        usuario.setEstado(rs.getLong("estado"));
        usuario.setRol(getRol(rs));
        return usuario;
    }

    private static Rol getRol(ResultSet rs) throws SQLException {
        Rol rol = new Rol();
        rol.setId(rs.getLong("rol"));
        rol.setNombre(rs.getString("rol_nombre"));
        return rol;
    }

    private static Long obtenerPrimaryKey(Cliente cliente, Statement stmt) throws SQLException{
        if (cliente.getId() == null) {
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return cliente.getId();
    }
}
