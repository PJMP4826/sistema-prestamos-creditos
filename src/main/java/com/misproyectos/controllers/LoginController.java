package com.misproyectos.controllers;

import com.misproyectos.exceptions.ValidacionException;
import com.misproyectos.models.SessionUsuario;
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
        String nombreUsuario = loginDialog.getUsuario();
        String password = loginDialog.getPassword();

        try {
            if (!validarInputs()) return;

            if (!usuarioRepository.existeUsuarioByTagName(nombreUsuario)) {
                loginDialog.mostrarMensaje("Usuario no existente");
                return;
            }

            if (!usuarioRepository.findPasswordByTagName(nombreUsuario).equals(password)) {
                loginDialog.mostrarMensaje("Contraseña incorrecta");
                return;
            }

            Usuario usuarioAutenticado = usuarioRepository.findUsuarioByTagName(nombreUsuario);
            SessionUsuario.iniciarSession(usuarioAutenticado);
            showMainWindow();
            loginDialog.dispose();
        } catch (SQLException | ValidacionException e) {
            loginDialog.mostrarMensaje(e.getMessage());
        }
    }

    public boolean validarInputs() throws ValidacionException {
        return validarUsuario() && validarPassword();
    }

    public boolean validarUsuario() throws ValidacionException {
        String usuario = loginDialog.getUsuario();

        if (usuario.isEmpty()) {
            throw new ValidacionException("Debes ingresar tu usuario");
        }

        if (usuario.trim().length() > 1000) {
            throw new ValidacionException("Nombre de usuario demasiado grande");
        }

        return true;
    }

    public boolean validarPassword() throws ValidacionException {
        String password = loginDialog.getPassword();

        if (password.isEmpty()) {
            throw new ValidacionException("Desbes especificar una contraseña");
        }

        if (password.trim().length() > 1000) {
            throw new ValidacionException("Tu contraseña es demansiado grande");
        }

        return true;
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
