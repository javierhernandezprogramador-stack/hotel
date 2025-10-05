package org.jadez.apiservlet.webapp.hotel.dto;

import org.jadez.apiservlet.webapp.hotel.entity.Usuario;

public class UsuarioDto {
    private Long id;
    private String email;
    private Long estado;
    private RolDto rol;

    public UsuarioDto() {
    }

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.estado = usuario.getEstado();
        this.rol = new RolDto(usuario.getRol());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }

    public RolDto getRol() {
        return rol;
    }

    public void setRol(RolDto rol) {
        this.rol = rol;
    }
}
