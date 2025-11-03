package com.misproyectos;

import com.misproyectos.views.MainWindow;

import javax.swing.*;

import static com.misproyectos.views.MainWindow.logger;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            MainWindow window = new MainWindow();
            window.setTitle("Sistema de Prestamos");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setLocationRelativeTo(null);
            window.setVisible(true);
        });
    }
}