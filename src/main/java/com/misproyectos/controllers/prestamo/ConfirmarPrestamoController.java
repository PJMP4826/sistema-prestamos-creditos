package com.misproyectos.controllers.prestamo;

import com.misproyectos.controllers.PrestamoController;
import com.misproyectos.views.Prestamos.ConfirmarPrestamo;
import com.misproyectos.views.Prestamos.DatosDelPrestamo;
import com.misproyectos.views.Prestamos.SeleccionarCliente;
import com.misproyectos.views.Prestamos.SeleccionarPeriodicidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class ConfirmarPrestamoController extends PrestamoController {
    private final ConfirmarPrestamo confirmarPrestamoView;
    private final DatosDelPrestamo datosDelPrestamoView;
    private final SeleccionarClienteController seleccionarClienteController;
    private final SeleccionarPeriodicidadController seleccionarPeriodicidadController;

    public ConfirmarPrestamoController(
            ConfirmarPrestamo confirmarPrestamoView,
            DatosDelPrestamo datosDelPrestamoView
    ) {
        this.confirmarPrestamoView = confirmarPrestamoView;
        this.datosDelPrestamoView = datosDelPrestamoView;
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
                System.out.println("Importe: " + datosDelPrestamoView.getImporteSpinner());
                System.out.println("Plazo: " + datosDelPrestamoView.getPlazoSpinner());
            }
        });
    }

    public void autoFillLabels() {
        confirmarPrestamoView.setClienteSelectedLbl(seleccionarClienteController.getSelectItemClienteJComboBox());
        confirmarPrestamoView.setPeriodicidadSelectedLbl(seleccionarPeriodicidadController.getSelectItemPeriodicidadJComboBox());

        DecimalFormat format = new DecimalFormat("$#,##0.00");
        String importe = format.format(datosDelPrestamoView.getImporteSpinner());

        confirmarPrestamoView.setImporteSelectedLbl(importe);
        confirmarPrestamoView.setPlazoSelectedLbl(datosDelPrestamoView.getPlazoSpinner().toString());
    }
}
