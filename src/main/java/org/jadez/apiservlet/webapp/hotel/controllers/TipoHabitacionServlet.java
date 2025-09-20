package org.jadez.apiservlet.webapp.hotel.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jadez.apiservlet.webapp.hotel.entity.TipoHabitacion;
import org.jadez.apiservlet.webapp.hotel.services.CrudService;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/tipoHabitacion")
public class TipoHabitacionServlet extends HttpServlet {

    @Inject
    CrudService<TipoHabitacion> serviceTipoHabitacion;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tipoHabitaciones", serviceTipoHabitacion.listar());
        getServletContext().getRequestDispatcher("/TipoHabitacion/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        Long id = parseLong(req.getParameter("id"));
        Long estado = parseLong(req.getParameter("estado"));
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");

        TipoHabitacion tipoHabitacion = construirTipoHabitacion(id, nombre, descripcion);

        switch (accion != null ? accion : "") {
            case "cambiar":
                cambiarTipoHabitacion(serviceTipoHabitacion, id, estado, req, resp);
                break;
            case "buscar":
                buscarTipoHabitacion(serviceTipoHabitacion, id, req, resp);
                break;
            case "crear":
            case "modificar":
                serviceTipoHabitacion.crear(tipoHabitacion);
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

    private void cambiarTipoHabitacion(CrudService<TipoHabitacion> crudService, Long id, Long estado, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<TipoHabitacion> optional = crudService.porId(id);
        if (optional.isPresent()) {
            crudService.updateEstado(id, estado);
            resp.sendRedirect(req.getContextPath() + "/tipoHabitacion");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el tipo de habitación a cambiar estado");
        }
    }

    private void buscarTipoHabitacion(CrudService<TipoHabitacion> crudService, Long id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<TipoHabitacion> optional = crudService.porId(id);
        if (optional.isPresent()) {
            req.setAttribute("tipoHabitacion", optional.get());
            getServletContext().getRequestDispatcher("/TipoHabitacion/editar.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el tipo de habitación a buscar");
        }
    }
}
