package org.jadez.apiservlet.webapp.hotel.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jadez.apiservlet.webapp.hotel.models.TipoHabitacion;
import org.jadez.apiservlet.webapp.hotel.services.Service;
import org.jadez.apiservlet.webapp.hotel.services.TipoHabitacionServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/tipoHabitacion")
public class TipoHabitacionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        Service<TipoHabitacion> service = new TipoHabitacionServiceImpl(conn);
        req.setAttribute("tipoHabitaciones", service.listar());
        getServletContext().getRequestDispatcher("/TipoHabitacion/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        Service<TipoHabitacion> service = new TipoHabitacionServiceImpl(conn);

        String accion = req.getParameter("accion");

        Long id = parseLong(req.getParameter("id"));
        Long estado = parseLong(req.getParameter("estado"));
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");

        TipoHabitacion tipoHabitacion = construirTipoHabitacion(id, nombre, descripcion);

        switch (accion != null ? accion : "") {
            case "cambiar":
                cambiarTipoHabitacion(service, id, estado, req, resp);
                break;
            case "buscar":
                buscarTipoHabitacion(service, id, req, resp);
                break;
            case "crear":
            case "modificar":
                service.crear(tipoHabitacion);
                resp.sendRedirect(req.getContextPath() + "/tipoHabitacion");
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error acción no especificada");
        }
    }

    private Long parseLong(String parametro) {
        try {
            return parametro != null ? Long.parseLong(parametro) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private TipoHabitacion construirTipoHabitacion(Long id, String nombre, String descripcion) {
        TipoHabitacion tipoHabitacion = new TipoHabitacion();
        tipoHabitacion.setId(id);
        tipoHabitacion.setNombre(nombre);
        tipoHabitacion.setDescripcion(descripcion);
        tipoHabitacion.setEstado(1L);
        return tipoHabitacion;
    }

    private void cambiarTipoHabitacion(Service<TipoHabitacion> service, Long id, Long estado, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<TipoHabitacion> optional = service.porId(id);
        if (optional.isPresent()) {
            service.updateEstado(id, estado);
            resp.sendRedirect(req.getContextPath() + "/tipoHabitacion");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el tipo de habitación a cambiar estado");
        }
    }

    private void buscarTipoHabitacion(Service<TipoHabitacion> service, Long id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<TipoHabitacion> optional = service.porId(id);
        if (optional.isPresent()) {
            req.setAttribute("tipoHabitacion", optional.get());
            getServletContext().getRequestDispatcher("/TipoHabitacion/editar.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el tipo de habitación a buscar");
        }
    }
}
