package org.jadez.apiservlet.webapp.hotel.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jadez.apiservlet.webapp.hotel.dto.EmpleadoDto;
import org.jadez.apiservlet.webapp.hotel.dto.EstadoDto;
import org.jadez.apiservlet.webapp.hotel.dto.mapper.EmpleadoMapper;
import org.jadez.apiservlet.webapp.hotel.dto.mapper.MapperClass;
import org.jadez.apiservlet.webapp.hotel.entity.Empleado;
import org.jadez.apiservlet.webapp.hotel.services.CrudService;

import java.util.List;
import java.util.Optional;

@RequestScoped
@Path("/empleados")
@Produces(MediaType.APPLICATION_JSON)
public class EmpleadoRestController {

    @Inject
    private CrudService<Empleado> service;

    @Inject
    private MapperClass mapperClass;

    @Inject
    private EmpleadoMapper mapper;

    @GET
    public List<EmpleadoDto> listar() {
        return service.listar().stream().map(EmpleadoDto::new).toList();
    }

    @GET
    @Path("/{id}")
    public Response porId(@PathParam("id") Long id) {
        Optional<Empleado> optionalEmpleado = service.porId(id);

        if(optionalEmpleado.isPresent()) {
            return Response.ok(new EmpleadoDto(optionalEmpleado.get())).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(EmpleadoDto empleadoDto) {
        try {
            Empleado empleado = mapper.toEntity(empleadoDto);
            empleado.getUsuario().setEmail(empleadoDto.getUsuario().getEmail());
            empleado.getUsuario().setEstado(empleadoDto.getUsuario().getEstado());
            empleado.getUsuario().setRol(mapperClass.obtenerRol(empleadoDto.getUsuario().getRol()));

            service.crear(empleado);
            return Response.ok(new EmpleadoDto(empleado)).build();
        }catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@PathParam("id") Long id, EmpleadoDto empleadoDto) {
        Optional<Empleado> optionalEmpleado = service.porId(id);

        if(optionalEmpleado.isPresent()) {
            try {
                Empleado nuevoEmpleado = optionalEmpleado.get();
                Empleado empleado = mapper.toEntity(empleadoDto);
                empleado.setUsuario(mapperClass.obtenerUsuario(empleadoDto.getUsuario()));
                empleado.getUsuario().setEmail(empleadoDto.getUsuario().getEmail());

                nuevoEmpleado.setApellido(empleado.getApellido());
                nuevoEmpleado.setDui(empleado.getDui());
                nuevoEmpleado.setEstado(empleado.getEstado());
                nuevoEmpleado.setFechaNacimiento(empleado.getFechaNacimiento());
                nuevoEmpleado.setNombre(empleado.getNombre());
                nuevoEmpleado.setTelefono(empleado.getTelefono());
                nuevoEmpleado.setUsuario(empleado.getUsuario());

                service.crear(nuevoEmpleado);
                return Response.ok(new EmpleadoDto(nuevoEmpleado)).build();

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
        Optional<Empleado> optionalEmpleado = service.porId(id);

        if(optionalEmpleado.isPresent()) {
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
