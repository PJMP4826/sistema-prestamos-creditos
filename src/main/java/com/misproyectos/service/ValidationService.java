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
        validarNombre(cliente.getNombre());
        existeRFC(cliente.getRfc());
        existeTelefono(telefonoCliente.getTelefono());
        existeCorreo(correoCliente.getCorreo());
        validarRFC(cliente.getRfc());
        validarTelefono(telefonoCliente.getTelefono());
        validarCorreo(correoCliente.getCorreo());
    }

    public static void limpiarEntradas(
            Cliente cliente,
            TelefonoCliente telefonoCliente,
            CorreoCliente correoCliente,
            DireccionCliente direccionCliente
    ) {
        limpiarCliente(cliente);
        limpiarTelefono(telefonoCliente);
        limpiarCorreo(correoCliente);
        limpiarDireccion(direccionCliente);
    }

    private static void limpiarCliente(Cliente c) {
        c.setNombre(c.getNombre().trim());
        c.setRfc(c.getRfc().trim());
    }

    private static void limpiarTelefono(TelefonoCliente t) {
        t.setTelefono(t.getTelefono().trim());
    }

    private static void limpiarCorreo(CorreoCliente c) {
        c.setCorreo(c.getCorreo().trim());
    }

    private static void limpiarDireccion(DireccionCliente d) {
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

    public static void existeTelefono(String telefono) throws ValidacionException {
        if (telefono == null || telefono.isEmpty()) {
            throw new ValidacionException("El Telefono no puede estar vacio");
        }
    }

    public static void existeCorreo(String correo) throws ValidacionException {
        if (correo == null || correo.isEmpty()) {
            throw new ValidacionException("El Correo no puede estar vacio");
        }
    }

    public static void existeRFC(String rfc) throws ValidacionException {
        if (rfc == null || rfc.isEmpty()) {
            throw new ValidacionException("El RFC no puede estar vacio");
        }
    }

    public static void validarCorreo(String correo) throws ValidacionException {
        if (correo == null || !correo.contains("@")) {
            throw new ValidacionException("El correo es invalido");
        }
    }
}
