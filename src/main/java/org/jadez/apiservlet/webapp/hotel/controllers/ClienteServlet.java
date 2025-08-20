package org.jadez.apiservlet.webapp.hotel.controllers;

import com.mysql.cj.xdevapi.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jadez.apiservlet.webapp.hotel.models.*;
import org.jadez.apiservlet.webapp.hotel.services.ClienteServiceImpl;
import org.jadez.apiservlet.webapp.hotel.services.Service;
import org.jadez.apiservlet.webapp.hotel.services.ServicioServiceImpl;
import org.jadez.apiservlet.webapp.hotel.services.TipoServicioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.Optional;

@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        Service<Cliente> service = new ClienteServiceImpl(conn);
        req.setAttribute("clientes", service.listar());
        getServletContext().getRequestDispatcher("/Cliente/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        Service<Cliente> service = new ClienteServiceImpl(conn);

        String accion = req.getParameter("accion");
        
        Long id = parseLong(req.getParameter("id"));
        Long idUsuario = parseLong(req.getParameter("id_usuario"));
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        String telefono = req.getParameter("telefono");
        String dui = req.getParameter("dui");
        String pasaporte = req.getParameter("pasaporte");
        LocalDate fechaNacimiento = parseLocalDate(req.getParameter("fecha_nacimiento"));
        String nacionalidad = req.getParameter("nacionalidad");
        String email = req.getParameter("email");

        Usuario usuario = new Usuario();
        usuario.setId(idUsuario);
        usuario.setEmail(email);
        usuario.setEstado(1L);

        Cliente cliente = construirCliente(id, nombre,apellido, telefono, dui, pasaporte, fechaNacimiento, nacionalidad, usuario);

        switch (accion != null ? accion : "") {
            case "cambiar":
                //cambiarEstado(service, id, estado, req, resp);
                break;
            case "buscar":
                buscarCliente(service, id, req, resp);
                break;
            case "crear":
            case "modificar":
                service.crear(cliente);
                resp.sendRedirect(req.getContextPath() + "/cliente");
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error acción no especificada");
        }
    }

    private Long parseLong(String parametro) {
        try {
            return parametro != null  ? Long.parseLong(parametro) : null;
        }catch (NumberFormatException e) {
            return null;
        }
    }
    
    private LocalDate parseLocalDate(String parametro) {
        try {
            return parametro != null ? LocalDate.parse(parametro) : null;
        }catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Cliente construirCliente(Long id, String nombre, String apellido, String telefono, String dui, String pasaporte, LocalDate fechaNacimiento, String nacionalidad, Usuario usuario) {

        Rol rol = new Rol();
        rol.setId(3L);

        usuario.setRol(rol);

        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setTelefono(telefono);
        cliente.setDui(dui);
        cliente.setPasaporte(pasaporte);
        cliente.setFechaNacimiento(fechaNacimiento);
        cliente.setNacionalidad(nacionalidad);
        cliente.setEstado(1L);
        cliente.setUsuario(usuario);

        return cliente;
    }

    private void cambiarEstado(Service<Servicio> service, Long id, Long estado, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<Servicio> optional = service.porId(id);
        if (optional.isPresent()) {
            service.updateEstado(id, estado);
            resp.sendRedirect(req.getContextPath() + "/servicio");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el servicio a cambiar estado");
        }
    }

    private void buscarCliente(Service<Cliente> clienteService, Long id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Optional<Cliente> optional = clienteService.porId(id);

        if (optional.isPresent()) {
            req.setAttribute("cliente", optional.get());
            getServletContext().getRequestDispatcher("/Cliente/editar.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el cliente a buscar");
        }
    }
}
