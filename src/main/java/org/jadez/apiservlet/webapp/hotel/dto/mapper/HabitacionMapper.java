package org.jadez.apiservlet.webapp.hotel.dto.mapper;

import org.jadez.apiservlet.webapp.hotel.dto.HabitacionDto;
import org.jadez.apiservlet.webapp.hotel.entity.Habitacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi", uses = {TipoHabitacionMapper.class, ServicioMapper.class})
public interface HabitacionMapper {

    HabitacionDto toDto(Habitacion habitacion);

    @Mapping(target = "tipoHabitacion", ignore = true)
    @Mapping(target = "servicios", ignore = true)
    Habitacion toEntity(HabitacionDto dto);
}
