package com.misproyectos.models;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private Long id;
    private String nombre;
    private String rfc;

    private List<TelefonoCliente> telefonoClientes;
    private List<CorreoCliente> correoClientes;
    private List<DireccionCliente> direccionClientes;

    public Cliente() {
        this.telefonoClientes = new ArrayList<>();
        this.correoClientes = new ArrayList<>();
        this.direccionClientes = new ArrayList<>();
    }

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.telefonoClientes = new ArrayList<>();
        this.correoClientes = new ArrayList<>();
        this.direccionClientes = new ArrayList<>();
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

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public List<TelefonoCliente> getTelefonoClientes() {
        return telefonoClientes;
    }

    public void setTelefonoClientes(List<TelefonoCliente> telefonoClientes) {
        this.telefonoClientes = telefonoClientes;
    }

    public List<CorreoCliente> getCorreoClientes() {
        return correoClientes;
    }

    public void setCorreoClientes(List<CorreoCliente> correoClientes) {
        this.correoClientes = correoClientes;
    }

    public List<DireccionCliente> getDireccionClientes() {
        return direccionClientes;
    }

    public void setDireccionClientes(List<DireccionCliente> direccionClientes) {
        this.direccionClientes = direccionClientes;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefonoClientes=" + telefonoClientes +
                ", correoClientes=" + correoClientes +
                ", direccionClientes=" + direccionClientes +
                '}';
    }
}
