package com.misproyectos.models;

public class DireccionCliente {
    private Long idDireccion;
    private Long idCliente;
    private String description;

    public DireccionCliente() {

    }

    public Long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DireccionCliente{" +
                "idDireccion=" + idDireccion +
                ", idCliente=" + idCliente +
                ", description='" + description + '\'' +
                '}';
    }
}
