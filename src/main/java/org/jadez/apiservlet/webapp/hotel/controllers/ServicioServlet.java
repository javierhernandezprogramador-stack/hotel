package org.jadez.apiservlet.webapp.hotel.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jadez.apiservlet.webapp.hotel.models.Servicio;
import org.jadez.apiservlet.webapp.hotel.models.TipoServicio;
import org.jadez.apiservlet.webapp.hotel.services.crudService;
import org.jadez.apiservlet.webapp.hotel.services.ServicioCrudServiceImpl;
import org.jadez.apiservlet.webapp.hotel.services.TipoServicioCrudServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/servicio")
public class ServicioServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        crudService<Servicio> crudService = new ServicioCrudServiceImpl(conn);
        req.setAttribute("servicios", crudService.listar());
        getServletContext().getRequestDispatcher("/Servicio/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = (Connection) req.getAttribute("conn");
        crudService<Servicio> crudService = new ServicioCrudServiceImpl(conn);
        crudService<TipoServicio> tipoServicioCrudService = new TipoServicioCrudServiceImpl(conn);

        String accion = req.getParameter("accion");
        
        Long id = parseLong(req.getParameter("id"));
        Long estado = parseLong(req.getParameter("estado"));
        double precio = parseDouble(req.getParameter("precio"), 0.00);
        String nombre = req.getParameter("nombre");
        String descripcion = req.getParameter("descripcion");

        Long idTipoServicio = parseLong(req.getParameter("tipoServicio"));

        Servicio servicio = construirServicio(id, precio, nombre, descripcion,idTipoServicio);

        switch (accion != null ? accion : "") {
            case "cambiar":
                cambiarEstado(crudService, id, estado, req, resp);
                break;
            case "buscar":
                buscarServicio(crudService, tipoServicioCrudService, id, req, resp);
                break;
            case "crear":
            case "modificar":
                crudService.crear(servicio);
                resp.sendRedirect(req.getContextPath() + "/servicio");
                break;
            case "cargar":
                cargar(tipoServicioCrudService, req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error acción no especificada");
        }
    }

    private void cargar(crudService<TipoServicio> crudService, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("tipos", crudService.listar());
        getServletContext().getRequestDispatcher("/Servicio/formulario.jsp").forward(req, resp);
    }

    private Long parseLong(String parametro) {
        try {
            return parametro != null  ? Long.parseLong(parametro) : null;
        }catch (NumberFormatException e) {
            return null;
        }
    }
    
    private double parseDouble(String parametro, double defaultValue) {
        try {
            return parametro != null ? Double.parseDouble(parametro) : defaultValue;
        }catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    private Servicio construirServicio(Long id, double precio, String nombre, String descripcion, Long idTipoServicio) {
        TipoServicio tipoServicio = new TipoServicio();
        tipoServicio.setId(idTipoServicio);

        Servicio servicio = new Servicio();
        servicio.setId(id);
        servicio.setNombre(nombre);
        servicio.setPrecio(precio);
        servicio.setDescripcion(descripcion);
        servicio.setEstado(1L);
        servicio.setTipoServicio(tipoServicio);

        return servicio;
    }

    private void cambiarEstado(crudService<Servicio> crudService, Long id, Long estado, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<Servicio> optional = crudService.porId(id);
        if (optional.isPresent()) {
            crudService.updateEstado(id, estado);
            resp.sendRedirect(req.getContextPath() + "/servicio");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el servicio a cambiar estado");
        }
    }

    private void buscarServicio(crudService<Servicio> servicioCrudService, crudService<TipoServicio> tipoServicioCrudService, Long id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Optional<Servicio> optional = servicioCrudService.porId(id);

        if (optional.isPresent()) {
            req.setAttribute("servicio", optional.get());
            req.setAttribute("tipos", tipoServicioCrudService.listar());
            getServletContext().getRequestDispatcher("/Servicio/editar.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe el servicio a buscar");
        }
    }
}
