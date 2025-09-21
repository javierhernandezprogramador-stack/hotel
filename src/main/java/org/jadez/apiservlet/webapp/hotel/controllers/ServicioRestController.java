package org.jadez.apiservlet.webapp.hotel.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jadez.apiservlet.webapp.hotel.dto.EstadoDto;
import org.jadez.apiservlet.webapp.hotel.dto.ServicioDto;
import org.jadez.apiservlet.webapp.hotel.dto.mapper.ServicioMapper;
import org.jadez.apiservlet.webapp.hotel.entity.Servicio;
import org.jadez.apiservlet.webapp.hotel.entity.TipoServicio;
import org.jadez.apiservlet.webapp.hotel.services.CrudService;

import java.util.List;
import java.util.Optional;

@RequestScoped
@Path("/servicios")
@Produces(MediaType.APPLICATION_JSON)
public class ServicioRestController {

    @Inject
    private CrudService<Servicio> service;

    @Inject
    private CrudService<TipoServicio> tipoService;

    @Inject
    private ServicioMapper servicioMapper;

    @GET
    public List<ServicioDto> listar() {
        List<Servicio> servicios = service.listar();
        return servicios.stream().map(ServicioDto::new).toList();
    }

    @GET
    @Path("/{id}")
    public Response porId(@PathParam("id") Long id) {
        Optional<Servicio> optionalServicio = service.porId(id);

        if (optionalServicio.isPresent()) {
            ServicioDto dto = new ServicioDto(optionalServicio.get());
            return Response.ok(dto).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(ServicioDto servicioDto) {
        try {
            Servicio servicio = servicioMapper.toEntity(servicioDto);
            servicio.setTipoServicio(obtenerTipoServicio(servicioDto));
            service.crear(servicio);
            ServicioDto dto = new ServicioDto(servicio);
            return Response.ok(dto).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@PathParam("id") Long id, ServicioDto servicioDto) {
        Optional<Servicio> optionalServicio = service.porId(id);

        if (optionalServicio.isPresent()) {
            try {
                Servicio servicio = servicioMapper.toEntity(servicioDto);
                servicio.setTipoServicio(obtenerTipoServicio(servicioDto));
                Servicio nuevoServicio = optionalServicio.get();
                nuevoServicio.setNombre(servicio.getNombre());
                nuevoServicio.setDescripcion(servicio.getDescripcion());
                nuevoServicio.setEstado(servicio.getEstado());
                nuevoServicio.setPrecio(servicio.getPrecio());
                nuevoServicio.setTipoServicio(servicio.getTipoServicio());

                service.crear(nuevoServicio);
                ServicioDto dto = new ServicioDto(nuevoServicio);
                return Response.ok(dto).build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editarEstado(@PathParam("id") Long id, EstadoDto dto) {
        Optional<Servicio> optionalServicio = service.porId(id);

        if (optionalServicio.isPresent()) {
            try {
                service.updateEstado(id, dto.getEstado());
                return Response.status(Response.Status.OK).build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    private TipoServicio obtenerTipoServicio(ServicioDto dto) {

        if (dto.getTipoServicioDto() != null) {
            Optional<TipoServicio> tipoServicioOptional = tipoService.porId(dto.getTipoServicioDto().getId());

            if (tipoServicioOptional.isPresent()) {
                return tipoServicioOptional.get();
            }
        }

        return null;
    }
}