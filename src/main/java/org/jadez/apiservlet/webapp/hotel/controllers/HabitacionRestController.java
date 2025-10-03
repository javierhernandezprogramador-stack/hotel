package org.jadez.apiservlet.webapp.hotel.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jadez.apiservlet.webapp.hotel.dto.EstadoDto;
import org.jadez.apiservlet.webapp.hotel.dto.HabitacionDto;
import org.jadez.apiservlet.webapp.hotel.dto.ImageDeleteRequest;
import org.jadez.apiservlet.webapp.hotel.dto.ImageDto;
import org.jadez.apiservlet.webapp.hotel.dto.mapper.HabitacionMapper;
import org.jadez.apiservlet.webapp.hotel.dto.mapper.ImageMapper;
import org.jadez.apiservlet.webapp.hotel.dto.mapper.MapperClass;
import org.jadez.apiservlet.webapp.hotel.entity.Habitacion;
import org.jadez.apiservlet.webapp.hotel.entity.Image;
import org.jadez.apiservlet.webapp.hotel.services.CrudServiceImageService;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestScoped
@Path("/habitaciones")
@Produces(MediaType.APPLICATION_JSON)
public class HabitacionRestController {

    @Inject
    private CrudServiceImageService<Habitacion> service;

    @Inject
    private HabitacionMapper mapper;

    @Inject
    private MapperClass mapperClass;

    @Inject
    private ImageMapper imageMapper;

    @GET
    public List<HabitacionDto> listar() {
        List<Habitacion> habitaciones = service.listar();
        return habitaciones.stream().map(HabitacionDto::new).toList();
    }

    @GET
    @Path("/{id}")
    public Response porId(@PathParam("id") Long id) {
        Optional<Habitacion> optionalHabitacion = service.porId(id);

        if (optionalHabitacion.isPresent()) {
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

    @POST
    @Path("/{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImage(@PathParam("id") Long id, MultipartFormDataInput input) {
        Optional<Habitacion> optionalHabitacion = service.porId(id);

        if (optionalHabitacion.isPresent()) {
            try {
                service.addImagen(input, optionalHabitacion.get());
                return Response.status(Response.Status.OK).build();
            } catch (Exception e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@PathParam("id") Long id, HabitacionDto habitacionDto) {
        Optional<Habitacion> optionalHabitacion = service.porId(id);

        if (optionalHabitacion.isPresent()) {
            try {
                Habitacion habitacion = mapper.toEntity(habitacionDto);
                habitacion.setServicios(mapperClass.obtenerServicios(habitacionDto.getServiciosDto()));
                habitacion.setTipoHabitacion(mapperClass.obtenerTipoHabitacion(habitacionDto.getTipoHabitacionDto()));

                Habitacion nuevaHabitacion = optionalHabitacion.get();
                nuevaHabitacion.setBw(habitacion.getBw());
                nuevaHabitacion.setCama(habitacion.getCama());
                nuevaHabitacion.setCapacidad(habitacion.getCapacidad());
                nuevaHabitacion.setDescripcion(habitacion.getDescripcion());
                nuevaHabitacion.setEstado(habitacion.getEstado());
                nuevaHabitacion.setNumeroHabitacion(habitacion.getNumeroHabitacion());
                nuevaHabitacion.setPiso(habitacion.getPiso());
                nuevaHabitacion.setPrecio(habitacion.getPrecio());
                nuevaHabitacion.setServicios(habitacion.getServicios());
                nuevaHabitacion.setTipoHabitacion(habitacion.getTipoHabitacion());

                service.crear(nuevaHabitacion);
                return Response.ok(new HabitacionDto(nuevaHabitacion)).build();

            } catch (Exception e) {
                e.printStackTrace();
                return Response.serverError().build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    //Metodo que recibe el id de la habitación, una lista de nombre de imagenes y luego la elimina
    @DELETE
    @Path("/{id_habitacion}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteImage(@PathParam("id_habitacion") Long id_habitacion, ImageDeleteRequest request) {
        Optional<Habitacion> optionalHabitacion = service.porId(id_habitacion);

        if (optionalHabitacion.isPresent()) {
            try {
                List<Image> images = request.getImages().stream().map(imageMapper::toEntity).toList();
                service.eliminarImagen(id_habitacion, images);
                return Response.status(Response.Status.OK).build();
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
    public Response updateEstado(@PathParam("id") Long id, EstadoDto dto) {
        Optional<Habitacion> optionalHabitacion = service.porId(id);

        if(optionalHabitacion.isPresent()) {
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
