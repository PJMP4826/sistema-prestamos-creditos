package com.misproyectos.models;

import com.misproyectos.enums.TipoTelefono;

public class TelefonoCliente {
    private Long idTelefono;
    private Long idCliente;
    private String telefono;
    private TipoTelefono tipo;

    public TelefonoCliente() {

    }

    public Long getIdTelefono() {
        return idTelefono;
    }

    public void setIdTelefono(Long idTelefono) {
        this.idTelefono = idTelefono;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoTelefono getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefono tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "TelefonoCliente{" +
                "idTelefono=" + idTelefono +
                ", idCliente=" + idCliente +
                ", telefono='" + telefono + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
