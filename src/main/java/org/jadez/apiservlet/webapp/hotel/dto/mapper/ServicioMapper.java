package org.jadez.apiservlet.webapp.hotel.dto.mapper;

import org.jadez.apiservlet.webapp.hotel.dto.ServicioDto;
import org.jadez.apiservlet.webapp.hotel.entity.Servicio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi", uses = {TipoServicioMapper.class})
public interface ServicioMapper {
    ServicioDto toDto(Servicio servicio);

    @Mapping(target = "tipoServicio", ignore = true)
    Servicio toEntity(ServicioDto dto);
}
