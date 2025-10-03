package org.jadez.apiservlet.webapp.hotel.repositories;


import org.jadez.apiservlet.webapp.hotel.entity.Image;

public interface CrudRepositoryImage<T> extends CrudRepository<T> {
    Image addImagen(Image image);

    void deleteImage(Long id, Image image);
}
