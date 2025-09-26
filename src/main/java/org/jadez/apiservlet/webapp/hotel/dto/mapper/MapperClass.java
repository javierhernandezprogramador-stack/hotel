package org.jadez.apiservlet.webapp.hotel.dto.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jadez.apiservlet.webapp.hotel.dto.ServicioDto;
import org.jadez.apiservlet.webapp.hotel.dto.TipoHabitacionDto;
import org.jadez.apiservlet.webapp.hotel.entity.Servicio;
import org.jadez.apiservlet.webapp.hotel.entity.TipoHabitacion;
import org.jadez.apiservlet.webapp.hotel.services.CrudService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MapperClass {

    @Inject
    private CrudService<Servicio> servicioService;

    @Inject
    private CrudService<TipoHabitacion> tipoHabitacionService;

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
    public TipoHabitacion obtenerTipoHabitacion(TipoHabitacionDto habitacionDto) {
        Optional<TipoHabitacion> optionalTipoHabitacion = tipoHabitacionService.porId(habitacionDto.getId());

        if(optionalTipoHabitacion.isPresent()) {
            return optionalTipoHabitacion.get();
        }

        return null;
    }
}
