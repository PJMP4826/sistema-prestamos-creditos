package com.misproyectos.controllers;

import com.misproyectos.repositories.UsuarioRepository;
import com.misproyectos.views.login.LoginDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
}
