package org.jadez.apiservlet.webapp.hotel.dto.mapper;

import org.jadez.apiservlet.webapp.hotel.dto.TipoServicioDto;
import org.jadez.apiservlet.webapp.hotel.entity.TipoServicio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface TipoServicioMapper {

    TipoServicioDto toDto(TipoServicio tipoServicio);

    TipoServicio toEntity(TipoServicioDto dto);
}
