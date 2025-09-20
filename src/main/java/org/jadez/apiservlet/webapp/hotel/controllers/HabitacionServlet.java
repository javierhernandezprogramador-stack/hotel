package org.jadez.apiservlet.webapp.hotel.controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.jadez.apiservlet.webapp.hotel.entity.*;
import org.jadez.apiservlet.webapp.hotel.services.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/habitacion")
@MultipartConfig
public class HabitacionServlet extends HttpServlet {

    /*@Inject
    private CrudService<Habitacion> serviceHabitacion;

    @Inject
    private CrudService<TipoHabitacion> tipoHabitacionCrudService;

    @Inject
    private CrudService<Servicio> serviceServicio;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("habitacionServicio", serviceHabitacion.listar());
        getServletContext().getRequestDispatcher("/Habitacion/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        Long id = parseLong(req.getParameter("id"));
        String numeroHabitacion = req.getParameter("numero_habitacion");
        String descripcion = req.getParameter("descripcion");
        Integer capacidad =  parseInt(req.getParameter("capacidad"));
        double precio = parseDouble(req.getParameter("precio"), 0.00);
        Long estado = parseLong(req.getParameter("estado"));
        String piso = req.getParameter("piso");
        Integer cama = parseInt(req.getParameter("cama"));
        Integer bw = parseInt(req.getParameter("bw"));
        String fileName = req.getParameter("imageName");

        Long idTipoHabitacion = parseLong(req.getParameter("tipoHabitacion"));
        List<Servicio> servicios = convertirArray(req.getParameterValues("servicio"));

        Part filePart = req.getPart("imagen");//obtener imagen

        if(filePart != null && filePart.getSize() != 0) {
            if(fileName != null) { //esto significa que quiere cambiar la imagen
                eliminarImagen(fileName);
            }
            fileName = almacenarImagen(filePart);
        }

        Habitacion habitacion = construirHabitacion(id, numeroHabitacion, descripcion, capacidad, precio, piso, cama, bw, idTipoHabitacion, fileName);

        switch (accion != null ? accion : "") {
            case "cambiar":
                cambiarEstado(serviceHabitacion, id, estado, req, resp);
                break;
            case "buscar":
                buscarHabitacion(serviceHabitacion, serviceServicio, tipoHabitacionCrudService, id, req, resp);
                break;
            case "crear":
            case "modificar":
                serviceHabitacion.crear(habitacion, servicios);
                resp.sendRedirect(req.getContextPath() + "/habitacion");
                break;
            case "cargar":
                cargar(serviceServicio, tipoHabitacionCrudService, req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Error acción no especificada");
        }
    }



    private List<Servicio> convertirArray(String[] parametros) {
        List<Servicio> servicios = new ArrayList<>();

        try {
            for(String parametro: parametros) {
                Servicio servicio = new Servicio();
                servicio.setId(parseLong(parametro));
                servicios.add(servicio);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
        return servicios;
    }

    private void cargar(CrudService<Servicio> servicioCrudService, CrudService<TipoHabitacion> tipoHabitacionCrudService, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("tipos", tipoHabitacionCrudService.listar());
        req.setAttribute("servicios", servicioCrudService.listar());
        getServletContext().getRequestDispatcher("/Habitacion/formulario.jsp").forward(req, resp);
    }

    private String almacenarImagen(Part filePart) throws IOException {
        InputStream fileContent = filePart.getInputStream();//contenido
        String uploadPath = getServletContext().getInitParameter("upload.path");//ruta
        String extension = obtenerExtension(filePart);
        String fileName = UUID.randomUUID().toString() + extension;

        //validamos si existe la carpeta en la ruta especificada sino la creamos
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        //crear la imagen que vamos a almacenar
        File file = new File(uploadDir, fileName);

        try(OutputStream out = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while((bytesRead = fileContent.read(buffer)) != -1 ) {
                out.write(buffer, 0, bytesRead);
            }
        }

        return fileName;
    }

    private boolean eliminarImagen(String nombreArchivo) {
        String uploadPath = getServletContext().getInitParameter("upload.path");
        File file = new File(uploadPath, nombreArchivo);
        return file.exists() && file.delete();
    }


    private Integer parseInt(String parametro) {
        try {
            return parametro != null ? Integer.valueOf(parametro) : null;
        }catch (NumberFormatException e) {
            return null;
        }
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

    private Habitacion construirHabitacion(Long id, String numeroHabitacion, String descripcion, Integer capacidad, double precio, String piso, Integer cama, Integer bw, Long idTipoHabitacion, String imagen) {
        TipoHabitacion tipoHabitacion = new TipoHabitacion();
        tipoHabitacion.setId(idTipoHabitacion);

        Habitacion habitacion = new Habitacion();
        habitacion.setId(id);
        habitacion.setNumeroHabitacion(numeroHabitacion);
        habitacion.setDescripcion(descripcion);
        habitacion.setCapacidad(capacidad);
        habitacion.setPrecio(precio);
        habitacion.setEstado(1L);
        habitacion.setPiso(piso);
        habitacion.setCama(cama);
        habitacion.setBw(bw);
        habitacion.setTipoHabitacion(tipoHabitacion);
        habitacion.setImagen(imagen);

        return habitacion;
    }

    private void cambiarEstado(CrudService crudService, Long id, Long estado, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<HabitacionServicio> optional = crudService.porId(id);
        if (optional.isPresent()) {
            crudService.updateEstado(id, estado);
            resp.sendRedirect(req.getContextPath() + "/habitacion");
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe la habitacion a cambiar estado");
        }
    }

    private void buscarHabitacion(CrudServiceHabitacion service, CrudService<Servicio> servicioCrudService, CrudService<TipoHabitacion> tipoHabitacionCrudService, Long id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Optional<HabitacionServicio> optional = service.porId(id);

        if (optional.isPresent()) {
            req.setAttribute("habitacionService", optional.get());
            req.setAttribute("tipos", tipoHabitacionCrudService.listar());
            req.setAttribute("servicios", servicioCrudService.listar());
            getServletContext().getRequestDispatcher("/Habitacion/editar.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No existe la habitacion a buscar");
        }
    }

    private String getFileName(Part part) {
        for(String cd: part.getHeader("content-disposition").split(";")) {
            if(cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return  null;
    }

    private String obtenerExtension(Part part) {
        String fileName = getFileName(part);
        if(fileName != null && fileName.contains(".")) {
            return fileName.substring(fileName.lastIndexOf("."));
        }
        return null;
    }*/
}
