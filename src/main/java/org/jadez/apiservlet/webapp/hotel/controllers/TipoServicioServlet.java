package org.jadez.apiservlet.webapp.hotel.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jadez.apiservlet.webapp.hotel.models.TipoHabitacion;
import org.jadez.apiservlet.webapp.hotel.models.TipoServicio;
import org.jadez.apiservlet.webapp.hotel.services.Service;
import org.jadez.apiservlet.webapp.hotel.services.TipoHabitacionServiceImpl;
import org.jadez.apiservlet.webapp.hotel.services.TipoServicioServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/tipoServicio")
public class TipoServicioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        Service<TipoServicio> service = new TipoServicioServiceImpl(conn);
        req.setAttribute("tipoServicios", service.listar());
        getServletContext().getRequestDispatcher("/TipoServicio/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        Service<TipoServicio> service = new TipoServicioServiceImpl(conn);

        String accion = req.getParameter("accion");

        Long id = parseLong(req.getParameter("id"));
        Long estado = parseLong(req.getParameter("estado"));
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");

        TipoServicio tipoServicio = construirTipoServicio(id, nombre, descripcion);

        //acciones
        switch (accion != null ? accion : "") {
            case "cambiar":
                cambiarTipoServicio(service, id, estado, req, resp);
                break;
            case "buscar":
                buscarTipoServicio(service, id, req, resp);
                break;
            case "crear":
            case "modificar":
                service.crear(tipoServicio);
                resp.sendRedirect(req.getContextPath() + "/tipoServicio");
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error acción no especificada");
        }
    }

    //obtiene un parametro, lo valida y retona su respuesta en Long
    private Long parseLong(String parametro) {
        try {
            return parametro != null ? Long.parseLong(parametro) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private TipoServicio construirTipoServicio(Long id, String nombre, String descripcion) {
        TipoServicio tipoServicio = new TipoServicio();
        tipoServicio.setId(id);
        tipoServicio.setNombre(nombre);
        tipoServicio.setEstado(1L);
        tipoServicio.setDescripcion(descripcion);
        return tipoServicio;
    }

    private void cambiarTipoServicio(Service<TipoServicio> service, Long id, Long estado, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<TipoServicio> optional = service.porId(id);
        if (optional.isPresent()) {
            service.updateEstado(id, estado);
            resp.sendRedirect(req.getContextPath() + "/tipoServicio");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el tipo de servicio a cambiar estado");
        }
    }

    private void buscarTipoServicio(Service<TipoServicio> service, Long id, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Optional<TipoServicio> optional = service.porId(id);
        if (optional.isPresent()) {
            req.setAttribute("tipoServicio", optional.get());
            getServletContext().getRequestDispatcher("/TipoServicio/editar.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el tipo de servicio a editar");
        }
    }
}
