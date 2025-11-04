package com.misproyectos.controllers;

import com.misproyectos.models.Usuario;
import com.misproyectos.repositories.UsuarioRepository;
import com.misproyectos.views.login.RegistroDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegistroController {
    public RegistroDialog registroDialog;
    public UsuarioRepository usuarioRepository;

    public RegistroController(RegistroDialog registroDialog, UsuarioRepository repository) {
        this.registroDialog = registroDialog;
        this.usuarioRepository = repository;
    }

    public void initListeners() {
        registroDialog.getRegisternBtn_r().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    registrarUsuario();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void registrarUsuario() throws SQLException {
        try {
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(registroDialog.getUsuario2());
            usuario.setEmail(registroDialog.getCorreo1());
            usuario.setPassword(registroDialog.getPassword2());

            if (usuarioRepository.add(usuario)) {
                registroDialog.mostrarMensaje("Registrado correctamente");
            }
        } catch (SQLException e) {
            registroDialog.mostrarMensaje("Error de base de datos: " + e.getMessage());
        }
    }
}
