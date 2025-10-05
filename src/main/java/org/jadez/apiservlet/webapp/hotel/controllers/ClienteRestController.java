package org.jadez.apiservlet.webapp.hotel.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jadez.apiservlet.webapp.hotel.dto.ClienteDto;
import org.jadez.apiservlet.webapp.hotel.dto.EstadoDto;
import org.jadez.apiservlet.webapp.hotel.dto.mapper.ClienteMapper;
import org.jadez.apiservlet.webapp.hotel.dto.mapper.MapperClass;
import org.jadez.apiservlet.webapp.hotel.entity.Cliente;
import org.jadez.apiservlet.webapp.hotel.services.CrudService;

import java.util.List;
import java.util.Optional;

@RequestScoped
@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
public class ClienteRestController {

    @Inject
    private CrudService<Cliente> service;

    @Inject
    private MapperClass mapperClass;

    @Inject
    private ClienteMapper mapper;

    @GET
    public List<ClienteDto> listar() {
        return service.listar().stream().map(ClienteDto::new).toList();
    }

    @GET
    @Path("/{id}")
    public Response porId(@PathParam("id") Long id) {
        Optional<Cliente> optionalCliente = service.porId(id);

        if(optionalCliente.isPresent()) {
            return Response.ok(new ClienteDto(optionalCliente.get())).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crear(ClienteDto clienteDto) {
        try {
            Cliente cliente = mapper.toEntity(clienteDto);
            cliente.getUsuario().setEmail(clienteDto.getUsuario().getEmail());
            cliente.getUsuario().setEstado(clienteDto.getUsuario().getEstado());
            //cliente.setUsuario(mapperClass.obtenerUsuario(clienteDto.getUsuario()));
            cliente.getUsuario().setRol(mapperClass.obtenerRol(clienteDto.getUsuario().getRol()));

            service.crear(cliente);
            return Response.ok(new ClienteDto(cliente)).build();
        }catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editar(@PathParam("id") Long id, ClienteDto clienteDto) {
        Optional<Cliente> optionalCliente = service.porId(id);

        if(optionalCliente.isPresent()) {
            try {
                Cliente nuevoCliente = optionalCliente.get();
                Cliente cliente = mapper.toEntity(clienteDto);
                cliente.setUsuario(mapperClass.obtenerUsuario(clienteDto.getUsuario()));
                cliente.getUsuario().setEmail(clienteDto.getUsuario().getEmail());

                nuevoCliente.setApellido(cliente.getApellido());
                nuevoCliente.setDui(cliente.getDui());
                nuevoCliente.setEstado(cliente.getEstado());
                nuevoCliente.setFechaNacimiento(cliente.getFechaNacimiento());
                nuevoCliente.setNacionalidad(cliente.getNacionalidad());
                nuevoCliente.setNombre(cliente.getNombre());
                nuevoCliente.setTelefono(cliente.getTelefono());
                nuevoCliente.setPasaporte(cliente.getPasaporte());
                nuevoCliente.setUsuario(cliente.getUsuario());

                service.crear(nuevoCliente);
                return Response.ok(new ClienteDto(nuevoCliente)).build();

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
        Optional<Cliente> optionalCliente = service.porId(id);

        if(optionalCliente.isPresent()) {
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
