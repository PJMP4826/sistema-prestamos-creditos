package com.misproyectos.controllers;

import com.misproyectos.models.Usuario;
import com.misproyectos.repositories.UsuarioRepository;
import com.misproyectos.views.login.LoginDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginController {
    public LoginDialog loginDialog;
    public UsuarioRepository usuarioRepository;

    public LoginController(LoginDialog loginDialog) {
        this.loginDialog = loginDialog;
    }

    public void initListeners() {
        loginDialog.getUsuarioInput().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void registrarUsuario() throws SQLException {
        try {
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(loginDialog.);
            if()
        } catch (SQLException e) {
            loginDialog.mostrarMensaje("Error de base de datos: " + e.getMessage());
        }
    }
}
