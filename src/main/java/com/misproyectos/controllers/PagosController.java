package com.misproyectos.controllers;

import com.misproyectos.models.Cliente;
import com.misproyectos.models.Prestamo;
import com.misproyectos.repositories.PrestamoRepository;
import com.misproyectos.views.RegistrosDePagos.RealizarPago;
import com.misproyectos.views.RegistrosDePagos.Registrosdepagos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class PagosController {
    private final Registrosdepagos pagosViews;
    private final PrestamoRepository prestamoRepository;

    public PagosController(
            Registrosdepagos pagosViews,
            PrestamoRepository prestamoRepository
    ) {
        this.pagosViews = pagosViews;
        this.prestamoRepository = prestamoRepository;
    }

    public void iniListeners() {
        pagosViews.getIrPagarBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getPrestamoFromTable();
            }
        });
    }

    public void loadPrestamosPendientes() {
        DefaultTableModel model = (DefaultTableModel) pagosViews.getPrestamosPendientesTable().getModel();
        model.setRowCount(0);

        try {
            List<Prestamo> prestamos = prestamoRepository.findPrestamosPendientes();

            for (Prestamo p : prestamos) {
                model.addRow(new Object[]{
                        p.getIdPrestamo(),
                        p.getCliente().getNombre(),
                        p.getMontoPrestado(),
                        p.getSaldoPendiente()
                });
            }
        } catch (SQLException e) {
            pagosViews.mostrarMensaje("Errror al cargar los prestamos pendientes" + e.getMessage());
        }
    }

    private JFrame jPanelToJFrame() {
        return (JFrame) SwingUtilities.getWindowAncestor(pagosViews);
    }

    public void getPrestamoFromTable() {
        int rowSelected = pagosViews.getPrestamosPendientesTable().getSelectedRow();

        if (rowSelected == -1) {
            pagosViews.mostrarMensaje("Debes seleccionar el prestamos a pagar");
        }
        Cliente cliente = new Cliente();
        String clienteSelected = (String) pagosViews.getPrestamosPendientesTable().getValueAt(rowSelected, 1);
        cliente.setNombre(clienteSelected);

        Prestamo prestamoPendiente = Prestamo.builder()
                .setIdPrestamo((int) pagosViews.getPrestamosPendientesTable().getValueAt(rowSelected, 0))
                .setCliente(cliente)
                .setMontoPrestado((BigDecimal) pagosViews.getPrestamosPendientesTable().getValueAt(rowSelected, 2))
                .setSaldoPendiente((double) pagosViews.getPrestamosPendientesTable().getValueAt(rowSelected, 3))
                .build();


        abrirPagoDialog(prestamoPendiente);
    }

    private void abrirPagoDialog(Prestamo prestamoPendiente) {
        RealizarPago pagoDialog = new RealizarPago(jPanelToJFrame(), true);
        pagoDialog.setPagoInputs(prestamoPendiente);
        pagoDialog.setVisible(true);
    }

    public void registrarPago() {

    }
}
