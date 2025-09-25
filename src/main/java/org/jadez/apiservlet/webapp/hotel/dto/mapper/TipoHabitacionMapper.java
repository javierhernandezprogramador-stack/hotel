package org.jadez.apiservlet.webapp.hotel.dto.mapper;

import org.jadez.apiservlet.webapp.hotel.dto.TipoHabitacionDto;
import org.jadez.apiservlet.webapp.hotel.entity.TipoHabitacion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface TipoHabitacionMapper {

    TipoHabitacionDto toDto(TipoHabitacion tipoHabitacion);

    TipoHabitacion toEntity(TipoHabitacionDto tipoHabitacionDto);
}
