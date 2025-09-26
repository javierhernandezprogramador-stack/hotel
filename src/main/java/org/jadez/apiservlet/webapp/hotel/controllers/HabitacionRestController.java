package org.jadez.apiservlet.webapp.hotel.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jadez.apiservlet.webapp.hotel.dto.HabitacionDto;
import org.jadez.apiservlet.webapp.hotel.dto.mapper.HabitacionMapper;
import org.jadez.apiservlet.webapp.hotel.dto.mapper.MapperClass;
import org.jadez.apiservlet.webapp.hotel.entity.Habitacion;
import org.jadez.apiservlet.webapp.hotel.services.CrudService;

import java.util.List;
import java.util.Optional;

@RequestScoped
@Path("/habitaciones")
@Produces(MediaType.APPLICATION_JSON)
public class HabitacionRestController {

    @Inject
    private CrudService<Habitacion> service;

    @Inject
    private HabitacionMapper mapper;

    @Inject
    private MapperClass mapperClass;

    @GET
    public List<HabitacionDto> listar() {
        List<Habitacion> habitaciones = service.listar();
        return habitaciones.stream().map(HabitacionDto::new).toList();
    }

    @GET
    @Path("/{id}")
    public Response porId(@PathParam("id") Long id) {
        Optional<Habitacion> optionalHabitacion = service.porId(id);

        if(optionalHabitacion.isPresent()) {
            return Response.ok(new HabitacionDto(optionalHabitacion.get())).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(HabitacionDto habitacionDto) {
        try {
            Habitacion habitacion = mapper.toEntity(habitacionDto);
            habitacion.setServicios(mapperClass.obtenerServicios(habitacionDto.getServiciosDto()));
            habitacion.setTipoHabitacion(mapperClass.obtenerTipoHabitacion(habitacionDto.getTipoHabitacionDto()));

            service.crear(habitacion);
            return Response.ok(new HabitacionDto(habitacion)).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
