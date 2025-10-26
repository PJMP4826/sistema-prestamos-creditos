package com.misproyectos.models;

public class CorreoCliente {
    private Long idCorreo;
    private Long idCliente;
    private String correo;

    public CorreoCliente() {

    }

    public Long getIdCorreo() {
        return idCorreo;
    }

    public void setIdCorreo(Long idCorreo) {
        this.idCorreo = idCorreo;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "CorreoCliente{" +
                "idCorreo=" + idCorreo +
                ", idCliente=" + idCliente +
                ", correo='" + correo + '\'' +
                '}';
    }
}
