package com.misproyectos.controllers;

import com.misproyectos.interfaces.PrestamoViewInterface;
import com.misproyectos.models.Prestamo;
import com.misproyectos.repositories.*;
import com.misproyectos.service.ClienteService;
import com.misproyectos.service.PeriodicidadService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;


public abstract class PrestamoController implements PrestamoViewInterface {
    protected final PeriodicidadService servicePeriodicidad;
    protected final ClienteService serviceClient;
    public abstract JTable getPrestamoTable();
    public abstract void mostrarMensaje(String mensaje);

    public PrestamoController() {
        this.servicePeriodicidad = new PeriodicidadService(
                new PeriodicidadesRepository()
        );
        this.serviceClient = new ClienteService(
                new ClienteRepository(),
                new TelefonoRepository(),
                new DireccionRepository(),
                new CorreoRepository()
        );
    }

    @Override
    public void loadPrestamos() {
        DefaultTableModel model = (DefaultTableModel) getPrestamoTable().getModel();
        model.setRowCount(0);

        try {
            PrestamoRepository prestamoRepository = new PrestamoRepository();
            List<Prestamo> prestamos = prestamoRepository.findAll();

            for (Prestamo p : prestamos) {
                String nombreCliente = p.getCliente().getNombre();
                String nombrePeriodicidad = p.getPeriodicidadPago().getNombrePeriodicidad();

                model.addRow(new Object[]{
                        nombreCliente,
                        p.getMontoPrestado(),
                        p.getPlazoPago(),
                        nombrePeriodicidad,
                        p.getFechaInicio().toString(),
                        p.getSaldoPendiente(),
                        p.getEstadoPrestamo()
                });
            }
        } catch (SQLException ex) {
            mostrarMensaje("Error al obtener los prestamos: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
