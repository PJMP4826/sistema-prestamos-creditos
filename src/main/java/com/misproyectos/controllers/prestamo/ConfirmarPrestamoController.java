package com.misproyectos.controllers.prestamo;

import com.misproyectos.views.Prestamos.ConfirmarPrestamo;
import com.misproyectos.views.Prestamos.SeleccionarCliente;
import com.misproyectos.views.Prestamos.SeleccionarPeriodicidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmarPrestamoController {
    private final ConfirmarPrestamo confirmarPrestamoView;
    private final SeleccionarClienteController seleccionarClienteController;
    private final SeleccionarPeriodicidadController seleccionarPeriodicidadController;

    public ConfirmarPrestamoController(ConfirmarPrestamo confirmarPrestamoView) {
        this.confirmarPrestamoView = confirmarPrestamoView;
        this.seleccionarClienteController = new SeleccionarClienteController(
                new SeleccionarCliente()
        );
        this.seleccionarPeriodicidadController = new SeleccionarPeriodicidadController(
                new SeleccionarPeriodicidad()
        );
    }

    public void initListeners() {
        confirmarPrestamoView.getConfirmarPrestamoBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cliente seleccionado: " + seleccionarClienteController.getSelectItemClienteJComboBox());
                System.out.println("Periodicidad seleccionada: " + seleccionarPeriodicidadController.getSelectItemPeriodicidadJComboBox());
            }
        });
    }

    public void autoFillLabels() {

    }
}
