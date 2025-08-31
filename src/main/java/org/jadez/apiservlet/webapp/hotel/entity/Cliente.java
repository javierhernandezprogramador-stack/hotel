package org.jadez.apiservlet.webapp.hotel.entity;

public class Cliente extends Persona{
    private String pasaporte;
    private String nacionalidad;

    public Cliente() {
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
