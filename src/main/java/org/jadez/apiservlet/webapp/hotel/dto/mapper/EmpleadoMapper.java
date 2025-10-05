package org.jadez.apiservlet.webapp.hotel.dto.mapper;

import org.jadez.apiservlet.webapp.hotel.dto.EmpleadoDto;
import org.jadez.apiservlet.webapp.hotel.entity.Empleado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface EmpleadoMapper {
    EmpleadoDto toDto(Empleado empleado);

    Empleado toEntity(EmpleadoDto dto);
}
