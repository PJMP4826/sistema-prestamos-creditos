package com.misproyectos.controllers;

import com.misproyectos.Main;
import com.misproyectos.models.Usuario;
import com.misproyectos.repositories.UsuarioRepository;
import com.misproyectos.views.MainWindow;
import com.misproyectos.views.login.LoginDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginController {
    public LoginDialog loginDialog;
    public UsuarioRepository usuarioRepository;

    public LoginController(
            LoginDialog loginDialog,
            UsuarioRepository usuarioRepository
    ) {
        this.loginDialog = loginDialog;
        this.usuarioRepository = usuarioRepository;
    }

    public void initListeners() {
        loginDialog.iniciarSessionBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
    }

    public void iniciarSesion() {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(loginDialog.getUsuario());
        usuario.setPassword(loginDialog.getPassword());

        try {
            if (!usuarioRepository.existeUsuarioByTagName(usuario)) {
                loginDialog.mostrarMensaje("Usuario no existente");
            }

            if (usuarioRepository.findPasswordByTagName(usuario.getNombreUsuario()).equals(usuario.getPassword())) {
                showMainWindow();
                loginDialog.dispose();
            } else {
                loginDialog.mostrarMensaje("Contraseña incorrecta");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showMainWindow() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
        }

        java.awt.EventQueue.invokeLater(() -> {
            MainWindow window = new MainWindow();
            window.setTitle("Sistema de Prestamos");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setLocationRelativeTo(null);
            window.setVisible(true);
        });
    }
}
