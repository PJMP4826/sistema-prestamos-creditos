package com.misproyectos.models;

public class SessionUsuario {
    private static Usuario usuarioActual;

    public static void iniciarSession(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static void cerrarSession() {
        usuarioActual = null;
    }
}
