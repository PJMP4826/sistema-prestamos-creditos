package com.misproyectos.service;

import com.misproyectos.exceptions.ValidacionException;
import com.misproyectos.models.Cliente;
import com.misproyectos.models.CorreoCliente;
import com.misproyectos.models.DireccionCliente;
import com.misproyectos.models.TelefonoCliente;

public class ValidationService {
    public static void validarEntradas(
            Cliente cliente,
            TelefonoCliente telefonoCliente,
            CorreoCliente correoCliente
    ) throws ValidacionException {
        validarRFC(cliente.getRfc());
        validarNombre(cliente.getNombre());
        validarTelefono(telefonoCliente.getTelefono());
        validarCorreo(correoCliente.getCorreo());
    }

    private void limpiarCliente(Cliente c) {
        c.setNombre(c.getNombre().trim());
        c.setRfc(c.getRfc().trim());
    }

    private void limpiarTelefono(TelefonoCliente t) {
        t.setTelefono(t.getTelefono().trim());
    }

    private void limpiarCorreo(CorreoCliente c) {
        c.setCorreo(c.getCorreo().trim());
    }

    private void limpiarDireccion(DireccionCliente d) {
        d.setDescription(d.getDescription().trim());
    }

    public static void validarRFC(String rfc) throws ValidacionException {
        if (rfc == null || !rfc.toUpperCase().matches("^[A-ZÑ&]{3,4}\\d{6}[A-Z0-9]{3}$")) {
            throw new ValidacionException("RFC invalido");
        }
    }

    public static void validarNombre(String nombre) throws ValidacionException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ValidacionException("El nombre no puede estar vacio");
        }
    }

    public static void validarTelefono(String telefono) throws ValidacionException {
        if (telefono == null || telefono.trim().length() < 10) {
            throw new ValidacionException("El telefono debe tener minimo 10 dígitos");
        }
    }

    public static void validarCorreo(String correo) throws ValidacionException {
        if (correo == null || !correo.contains("@")) {
            throw new ValidacionException("El correo es invalido");
        }
    }
}
