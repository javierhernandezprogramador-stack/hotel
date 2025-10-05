package org.jadez.apiservlet.webapp.hotel.dto;

import org.jadez.apiservlet.webapp.hotel.entity.Cliente;
import org.jadez.apiservlet.webapp.hotel.entity.Empleado;

import java.time.LocalDate;

public class EmpleadoDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String dui;
    private Long estado;
    private LocalDate fechaNacimiento;
    private String telefono;
    private UsuarioDto usuario;

    public EmpleadoDto() {
    }

    public EmpleadoDto(Empleado empleado) {
        this.id = empleado.getId();
        this.nombre = empleado.getNombre();
        this.apellido = empleado.getApellido();
        this.dui = empleado.getDui();
        this.estado = empleado.getEstado();
        this.fechaNacimiento = empleado.getFechaNacimiento();
        this.telefono = empleado.getTelefono();
        this.usuario = new UsuarioDto(empleado.getUsuario());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public UsuarioDto getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDto usuario) {
        this.usuario = usuario;
    }
}
