package org.jadez.apiservlet.webapp.hotel.dto.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.dto.RolDto;
import org.jadez.apiservlet.webapp.hotel.dto.ServicioDto;
import org.jadez.apiservlet.webapp.hotel.dto.TipoHabitacionDto;
import org.jadez.apiservlet.webapp.hotel.dto.UsuarioDto;
import org.jadez.apiservlet.webapp.hotel.entity.Rol;
import org.jadez.apiservlet.webapp.hotel.entity.Servicio;
import org.jadez.apiservlet.webapp.hotel.entity.TipoHabitacion;
import org.jadez.apiservlet.webapp.hotel.entity.Usuario;
import org.jadez.apiservlet.webapp.hotel.services.CrudService;
import org.jadez.apiservlet.webapp.hotel.services.CrudServiceRol;
import org.jadez.apiservlet.webapp.hotel.services.CrudServiceUsuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MapperClass {

    @Inject
    private CrudService<Servicio> servicioService;

    @Inject
    private CrudService<TipoHabitacion> tipoHabitacionService;

    @Inject
    private CrudServiceRol rolService;

    @Inject
    private CrudServiceUsuario usuarioService;

    //Mappear array de servicioDTO a servicio
    public List<Servicio> obtenerServicios(List<ServicioDto> serviciosDto) {
        List<Servicio> servicios = new ArrayList<>();

        for (ServicioDto dto: serviciosDto) {
            Optional<Servicio> optionalServicio = servicioService.porId(dto.getId());

            if(optionalServicio.isPresent()) {
                servicios.add(optionalServicio.get());
            }
        }

        return servicios;
    }

    //Mappear TipoHabitacionDTO a TipoHabitacion
    public TipoHabitacion obtenerTipoHabitacion(TipoHabitacionDto tipoHabitacionDto) {
        Long id = tipoHabitacionDto.getId();
        Optional<TipoHabitacion> optionalTipoHabitacion = tipoHabitacionService.porId(id);


        if(optionalTipoHabitacion.isPresent()) {
            return optionalTipoHabitacion.get();
        }

        return null;
    }

    public Rol obtenerRol(RolDto rolDto) {
        Long id = rolDto.getId();
        Optional<Rol> optionalRol = rolService.porId(id);

        if(optionalRol.isPresent()) {
            return optionalRol.get();
        }

        return null;
    }

    public Usuario obtenerUsuario(UsuarioDto usuarioDto) {
        Long id = usuarioDto.getId();
        Optional<Usuario> optionalUsuario = usuarioService.porId(id);

        if(optionalUsuario.isPresent()) {
            return optionalUsuario.get();
        }

        return null;
    }
}
