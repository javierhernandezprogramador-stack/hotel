package org.jadez.apiservlet.webapp.hotel.dto;

import org.jadez.apiservlet.webapp.hotel.entity.Rol;

public class RolDto {
    private Long id;

    public RolDto() {
    }

    public RolDto(Rol rol) {
        this.id = rol.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
