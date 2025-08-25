package org.jadez.apiservlet.webapp.hotel.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jadez.apiservlet.webapp.hotel.models.TipoServicio;
import org.jadez.apiservlet.webapp.hotel.services.crudService;
import org.jadez.apiservlet.webapp.hotel.services.TipoServicioCrudServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/tipoServicio")
public class TipoServicioServlet extends HttpServlet {

    @Inject
    private crudService<TipoServicio> serviceTipoServicio;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tipoServicios", serviceTipoServicio.listar());
        getServletContext().getRequestDispatcher("/TipoServicio/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String accion = req.getParameter("accion");

        Long id = parseLong(req.getParameter("id"));
        Long estado = parseLong(req.getParameter("estado"));
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");

        TipoServicio tipoServicio = construirTipoServicio(id, nombre, descripcion);

        //acciones
        switch (accion != null ? accion : "") {
            case "cambiar":
                cambiarTipoServicio(serviceTipoServicio, id, estado, req, resp);
                break;
            case "buscar":
                buscarTipoServicio(serviceTipoServicio, id, req, resp);
                break;
            case "crear":
            case "modificar":
                serviceTipoServicio.crear(tipoServicio);
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

    private void cambiarTipoServicio(crudService<TipoServicio> crudService, Long id, Long estado, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<TipoServicio> optional = crudService.porId(id);
        if (optional.isPresent()) {
            crudService.updateEstado(id, estado);
            resp.sendRedirect(req.getContextPath() + "/tipoServicio");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el tipo de servicio a cambiar estado");
        }
    }

    private void buscarTipoServicio(crudService<TipoServicio> crudService, Long id, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Optional<TipoServicio> optional = crudService.porId(id);
        if (optional.isPresent()) {
            req.setAttribute("tipoServicio", optional.get());
            getServletContext().getRequestDispatcher("/TipoServicio/editar.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el tipo de servicio a editar");
        }
    }
}
