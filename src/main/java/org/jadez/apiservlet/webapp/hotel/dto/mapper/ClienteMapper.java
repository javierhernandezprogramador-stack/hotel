package org.jadez.apiservlet.webapp.hotel.dto.mapper;

import org.jadez.apiservlet.webapp.hotel.dto.ClienteDto;
import org.jadez.apiservlet.webapp.hotel.entity.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ClienteMapper {
    ClienteDto toDto(Cliente cliente);

    Cliente toEntity(ClienteDto dto);
}
