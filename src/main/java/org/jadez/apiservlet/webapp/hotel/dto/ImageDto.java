package org.jadez.apiservlet.webapp.hotel.dto;

import org.jadez.apiservlet.webapp.hotel.entity.Image;

public class ImageDto {
    private String nombre;

    public ImageDto() {
    }

    public ImageDto(Image image) {
        nombre = image.getNombre();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
