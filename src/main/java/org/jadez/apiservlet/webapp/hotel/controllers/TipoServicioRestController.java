package org.jadez.apiservlet.webapp.hotel.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jadez.apiservlet.webapp.hotel.dto.EstadoDto;
import org.jadez.apiservlet.webapp.hotel.dto.TipoServicioDto;
import org.jadez.apiservlet.webapp.hotel.dto.mapper.TipoServicioMapper;
import org.jadez.apiservlet.webapp.hotel.entity.TipoServicio;
import org.jadez.apiservlet.webapp.hotel.services.CrudService;

import java.util.List;
import java.util.Optional;

@RequestScoped
@Path("/tipoServicios")
@Produces(MediaType.APPLICATION_JSON)
public class TipoServicioRestController {

    @Inject
    private CrudService<TipoServicio> service;

    @Inject
    private TipoServicioMapper tipoServicioMapper;

    @GET
    public List<TipoServicioDto> listar() {
        List<TipoServicio> tipoServicios = service.listar();
        return tipoServicios.stream().map(TipoServicioDto::new).toList();
    }

    @GET
    @Path("/{id}")
    public Response porId(@PathParam("id") Long id) {
        Optional<TipoServicio> optionalTipoServicio = service.porId(id);

        if(optionalTipoServicio.isPresent()) {
            return Response.ok(tipoServicioMapper.toDto(optionalTipoServicio.get())).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(TipoServicioDto tipoServicioDto) {
        try {
            TipoServicio tipoServicio = tipoServicioMapper.toEntity(tipoServicioDto);
            service.crear(tipoServicio);
            return Response.ok(tipoServicioMapper.toDto(tipoServicio)).build();
        }catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@PathParam("id") Long id, TipoServicioDto tipoServicioDto) {
        Optional<TipoServicio> optionalTipoServicio = service.porId(id);

        if(optionalTipoServicio.isPresent()) {

            try {
                TipoServicio tipoServicio = tipoServicioMapper.toEntity(tipoServicioDto);
                TipoServicio nuevoTipoServicio = optionalTipoServicio.get();
                nuevoTipoServicio.setNombre(tipoServicio.getNombre());
                nuevoTipoServicio.setEstado(tipoServicio.getEstado());
                service.crear(nuevoTipoServicio);
                return Response.ok(tipoServicioMapper.toDto(nuevoTipoServicio)).build();

            }catch (Exception e) {
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
        Optional<TipoServicio> optionalTipoServicio = service.porId(id);

        if(optionalTipoServicio.isPresent()) {
            try {
                service.updateEstado(id, dto.getEstado());
                return Response.status(Response.Status.OK).build();
            }catch (Exception e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
