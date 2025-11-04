package com.misproyectos.controllers.prestamo;

import com.misproyectos.controllers.PrestamoController;
import com.misproyectos.exceptions.ValidacionException;
import com.misproyectos.models.Periodicidad;
import com.misproyectos.service.PeriodicidadService;
import com.misproyectos.service.PrestamoService;
import com.misproyectos.views.Prestamos.ConfirmarPrestamo;
import com.misproyectos.views.Prestamos.DatosDelPrestamo;
import com.misproyectos.views.Prestamos.SeleccionarCliente;
import com.misproyectos.views.Prestamos.SeleccionarPeriodicidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;

public class ConfirmarPrestamoController extends PrestamoController {
    private final ConfirmarPrestamo confirmarPrestamoView;
    private final DatosDelPrestamo datosDelPrestamoView;
    private final SeleccionarClienteController seleccionarClienteController;
    private final SeleccionarPeriodicidadController seleccionarPeriodicidadController;
    private final PrestamoService servicePrestamo;
    private final PeriodicidadService servicePeriodicidad;

    public ConfirmarPrestamoController(
            ConfirmarPrestamo confirmarPrestamoView,
            DatosDelPrestamo datosDelPrestamoView,
            PrestamoService servicePrestamo,
            PeriodicidadService servicePeriodicidad
    ) {
        this.confirmarPrestamoView = confirmarPrestamoView;
        this.datosDelPrestamoView = datosDelPrestamoView;
        this.seleccionarClienteController = new SeleccionarClienteController(
                new SeleccionarCliente()
        );
        this.seleccionarPeriodicidadController = new SeleccionarPeriodicidadController(
                new SeleccionarPeriodicidad()
        );
        this.servicePrestamo = servicePrestamo;
        this.servicePeriodicidad = servicePeriodicidad;
    }

    public void initListeners() {
        confirmarPrestamoView.getConfirmarPrestamoBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Id del cliente seleccionado: " + seleccionarClienteController.getSelectedClienteId());
                System.out.println("Cliente seleccionado: " + seleccionarClienteController.getSelectItemClienteJComboBox());
                System.out.println("Id de la periodicidad seleccionada: " + seleccionarPeriodicidadController.getSelectedPeriodicidadId());
                System.out.println("Periodicidad seleccionada: " + seleccionarPeriodicidadController.getSelectItemPeriodicidadJComboBox());
                System.out.println("Importe: " + datosDelPrestamoView.getImporteSpinner());
                System.out.println("Plazo: " + datosDelPrestamoView.getPlazoSpinner());
            }
        });
    }

    public void autoFillLabels() throws ValidacionException, SQLException {
        confirmarPrestamoView.setClienteSelectedLbl(seleccionarClienteController.getSelectItemClienteJComboBox());
        confirmarPrestamoView.setPeriodicidadSelectedLbl(seleccionarPeriodicidadController.getSelectItemPeriodicidadJComboBox());

        DecimalFormat format = new DecimalFormat("$#,##0.00");
        String importe = format.format(datosDelPrestamoView.getImporteSpinner());

        confirmarPrestamoView.setImporteSelectedLbl(importe);
        confirmarPrestamoView.setPlazoSelectedLbl(datosDelPrestamoView.getPlazoSpinner().toString());

        Periodicidad periodicidad = servicePeriodicidad.findById(seleccionarPeriodicidadController.getSelectedPeriodicidadId());
        double tasaInteres = periodicidad.getPorcentajeIntereses();
        double plazo = datosDelPrestamoView.getPlazoSpinner().doubleValue();

        String importeLimpio = importe.replace("$", "").replace(",", "").trim();
        double montoCuota = servicePrestamo.calcularMontoPorCuota(
                Double.parseDouble(importeLimpio),
                tasaInteres,
                plazo
        );

        double totalPagar = servicePrestamo.calcularTotalToPagar(montoCuota, plazo);
        double interesTotal = servicePrestamo.calcularInteresTotal(totalPagar, Double.parseDouble(importeLimpio));

        confirmarPrestamoView.setInteresSelectedLbl(String.valueOf(interesTotal));
        confirmarPrestamoView.setTotalPagarSelectedLbl(String.valueOf(totalPagar));
        confirmarPrestamoView.setNoCuotasSelectedLbl(String.valueOf(plazo));
        confirmarPrestamoView.setMontoCuotaSelectedLbl(String.valueOf(montoCuota));

    }
}
