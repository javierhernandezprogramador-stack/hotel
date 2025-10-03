package org.jadez.apiservlet.webapp.hotel.dto.mapper;

import org.jadez.apiservlet.webapp.hotel.dto.ImageDto;
import org.jadez.apiservlet.webapp.hotel.entity.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ImageMapper {
    ImageDto toDto(Image image);

    Image toEntity(ImageDto dto);
}
