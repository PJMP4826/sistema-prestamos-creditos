package com.misproyectos.controllers;

import com.misproyectos.models.Prestamo;
import com.misproyectos.repositories.PrestamoRepository;
import com.misproyectos.views.RegistrosDePagos.Registrosdepagos;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

            }
        });
    }

    public void loadPrestamosPendientes() {
        DefaultTableModel model = (DefaultTableModel) pagosViews.getPrestamosPendientesTable().getModel();
        model.setRowCount(0);

        try {
            List<Prestamo> prestamos = prestamoRepository.findPrestamosPendientes();

            for(Prestamo p : prestamos){
                model.addRow(new Object[] {
                        p.getIdPrestamo(),
                        p.getCliente().getNombre(),
                        p.getMontoPrestado(),
                        p.getSaldoPendiente()
                });
            }
        }catch (SQLException e){
            pagosViews.mostrarMensaje("Errror al cargar los prestamos pendientes" + e.getMessage());
        }
    }
}
