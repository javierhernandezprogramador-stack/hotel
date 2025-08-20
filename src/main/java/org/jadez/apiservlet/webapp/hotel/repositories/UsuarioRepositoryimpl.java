package org.jadez.apiservlet.webapp.hotel.repositories;

import org.jadez.apiservlet.webapp.hotel.models.Rol;
import org.jadez.apiservlet.webapp.hotel.models.TipoHabitacion;
import org.jadez.apiservlet.webapp.hotel.models.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryimpl implements RepositoryUsuario{
    private Connection conn;

    public UsuarioRepositoryimpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        String sql = "SELECT u.*, r.nombre AS nombre_rol FROM usuario u INNER JOIN rol r ON u.rol = r.id";
        List<Usuario> usuarios = new ArrayList<>();

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while(rs.next()) {
                usuarios.add(getUsuario(rs));
            }
        }

        return usuarios;
    }

    //Esta esta siendo usada por service
    @Override
    public Usuario crear(Usuario usuario) throws SQLException {
        String sql = (usuario.getId() != null) ? "UPDATE usuario SET email = ? WHERE id = ?" : "INSERT INTO usuario(email, password, rol, estado) VALUES(?,?,?,?)";

        /*REVISAR LOS ESTADOS PORQUE ESTAN VACIOS Y ESO DA ERROR AL INSERTAR*/

        try(PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, usuario.getEmail());

            if(usuario.getId() != null) {
                stmt.setLong(2, usuario.getId());
            }else {
                stmt.setString(2, usuario.getPassword());
                stmt.setLong(3, usuario.getRol().getId());
                stmt.setLong(4, usuario.getEstado());
            }

            stmt.executeUpdate();
            usuario.setId(obtenerPrimaryKey(usuario, stmt));//revisar porque no funciona el rotornar id

        }
        return usuario;
    }

    @Override
    public Usuario porId(Long id) throws SQLException {
        Usuario usuario = null;
        String sql = "SELECT u.*, r.nombre AS nombre_rol FROM usuario u INNER JOIN rol r ON u.rol = r.id WHERE u.id = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    usuario = getUsuario(rs);
                }
            }
        }

        return usuario;
    }

    //esto deberia de ser utulizado en el service
    @Override
    public void updateEstado(Long id, Long estado) throws SQLException {
        String sql = "UPDATE usuario SET estado = ? WHERE id = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, estado);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    private static Long obtenerPrimaryKey(Usuario usuario, Statement stmt) throws SQLException{
        if (usuario.getId() == null) {
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return usuario.getId();
    }

    private static Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("id"));
        usuario.setEmail(rs.getString("email"));
        usuario.setPassword(rs.getString("password"));
        usuario.setRol(getRol(rs));
        return usuario;
    }

    private static Rol getRol(ResultSet rs) throws SQLException {
        Rol rol = new Rol();
        rol.setId(rs.getLong("rol"));
        rol.setNombre(rs.getString("nombre_rol"));
        return rol;
    }

    @Override
    public void cambiarPassword(Usuario usuario) throws SQLException {
        String sql = "Update usuario SET password = ? WHERE email = ?";

        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1,usuario.getPassword());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();
        }
    }
}
