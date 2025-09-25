package org.jadez.apiservlet.webapp.hotel.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jadez.apiservlet.webapp.hotel.dto.EstadoDto;
import org.jadez.apiservlet.webapp.hotel.dto.TipoHabitacionDto;
import org.jadez.apiservlet.webapp.hotel.dto.mapper.TipoHabitacionMapper;
import org.jadez.apiservlet.webapp.hotel.entity.TipoHabitacion;
import org.jadez.apiservlet.webapp.hotel.services.CrudService;

import java.util.List;
import java.util.Optional;

@RequestScoped
@Path("/tipo_habitaciones")
@Produces(MediaType.APPLICATION_JSON)
public class TipoHabitacionRestController {

    @Inject
    private CrudService<TipoHabitacion> service;

    @Inject
    private TipoHabitacionMapper mapper;

    @GET
    public List<TipoHabitacionDto> listar() {
        List<TipoHabitacion> tipoHabitaciones = service.listar();
        return tipoHabitaciones.stream().map(TipoHabitacionDto::new).toList();
    }

    @GET
    @Path("/{id}")
    public Response porId(@PathParam("id") Long id) {
        Optional<TipoHabitacion> optionalTipoHabitacion = service.porId(id);

        if(optionalTipoHabitacion.isPresent()) {
            return Response.ok(mapper.toDto(optionalTipoHabitacion.get())).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(TipoHabitacionDto tipoHabitacionDto) {
        try {
            TipoHabitacion tipoHabitacion = mapper.toEntity(tipoHabitacionDto);
            service.crear(tipoHabitacion);
            return Response.ok(mapper.toDto(tipoHabitacion)).build();
        }catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@PathParam("id") Long id, TipoHabitacionDto tipoHabitacionDto) {
        Optional<TipoHabitacion> optionalTipoHabitacion = service.porId(id);

        if(optionalTipoHabitacion.isPresent()) {
            try {
                TipoHabitacion nuevoTipoHabitacion = optionalTipoHabitacion.get();
                nuevoTipoHabitacion.setNombre(tipoHabitacionDto.getNombre());
                nuevoTipoHabitacion.setEstado(tipoHabitacionDto.getEstado());

                service.crear(nuevoTipoHabitacion);
                return Response.ok(mapper.toDto(nuevoTipoHabitacion)).build();
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
    public Response editarEstado(@PathParam("id") Long id, EstadoDto estado) {
        Optional<TipoHabitacion> optionalTipoHabitacion = service.porId(id);

        if(optionalTipoHabitacion.isPresent()) {
            try {
                service.updateEstado(id, estado.getEstado());
                return Response.status(Response.Status.OK).build();
            }catch (Exception e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
