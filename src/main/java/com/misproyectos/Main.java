package com.misproyectos;

import com.misproyectos.views.MainWindow;
import com.misproyectos.views.login.LoginDialog;

import javax.swing.*;

import static com.misproyectos.views.MainWindow.logger;


public class Main {
    public static void main(String[] args) {
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
//            logger.log(java.util.logging.Level.SEVERE, null, ex);
//        }
//
//        java.awt.EventQueue.invokeLater(() -> {
//            MainWindow window = new MainWindow();
//            window.setTitle("Sistema de Prestamos");
//            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            window.setLocationRelativeTo(null);
//            window.setVisible(true);
//        });
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginDialog dialog = new LoginDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
}