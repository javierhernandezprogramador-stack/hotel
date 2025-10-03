package org.jadez.apiservlet.webapp.hotel.services;

import org.jadez.apiservlet.webapp.hotel.entity.Image;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import java.io.IOException;
import java.util.List;

public interface ImageService<T> {
    void addImagen(MultipartFormDataInput input, T t) throws IOException;

    void eliminarImagen(Long id, List<Image> imagenes);
}
