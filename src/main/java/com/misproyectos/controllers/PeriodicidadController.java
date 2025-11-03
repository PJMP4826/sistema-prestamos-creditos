package com.misproyectos.controllers;

import com.misproyectos.exceptions.ValidacionException;
import com.misproyectos.models.Periodicidad;
import com.misproyectos.service.PeriodicidadService;
import com.misproyectos.views.MainWindow;
import com.misproyectos.views.periodicidades.EditPeriodicidadDialog;
import com.misproyectos.views.periodicidades.Periodicidades;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class PeriodicidadController {
    private final Periodicidades periodicidadView;
    private final EditPeriodicidadDialog editarDialog;
    private final PeriodicidadService service;

    public PeriodicidadController(
            Periodicidades view,
            PeriodicidadService service
    ) {
        this.periodicidadView = view;
        this.editarDialog = new EditPeriodicidadDialog(jPanelToJFrame(), true);
        this.service = service;
    }

    public void loadPeriodicidades() {
        DefaultTableModel model = (DefaultTableModel) periodicidadView.getPeriodicidadesTable().getModel();
        model.setRowCount(0);

        try {
            List<Periodicidad> periodicidads = service.getPeriodicidades();

            for (Periodicidad periodicidad : periodicidads) {
                System.out.println("Id periodicidad (no se muestra en la tabla): " + periodicidad.getIdPeriodicidad());
                model.addRow(new Object[]{
                        periodicidad.getIdPeriodicidad(),
                        periodicidad.getNombrePeriodicidad(),
                        periodicidad.getDiasPeriodicidad(),
                        periodicidad.getPorcentajeIntereses()
                });
            }

        } catch (SQLException e) {
            periodicidadView.mostrarMensaje("Errror al cargar las periodicidades" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void initListener() {
        periodicidadView.getSaveBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    guardarPeriodicidad();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        periodicidadView.getEditBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirEditDialog();
                //editarPeriodicidad();
            }
        });
    }

    public void guardarPeriodicidad() throws SQLException {
        try {
            Periodicidad periodicidad = new Periodicidad();
            periodicidad.setNombrePeriodicidad(periodicidadView.getNombre());
            periodicidad.setDiasPeriodicidad(Integer.parseInt(periodicidadView.getDiasPeriodicidad()));
            periodicidad.setPorcentajeIntereses(Integer.parseInt(periodicidadView.getPorcentaje()));

            boolean isSuccess = this.service.registrarPeriodicidad(periodicidad);

            if (!isSuccess) {
                throw new ValidacionException("Error de validación");
            }

            periodicidadView.mostrarMensaje("Periodicidad agregada exitosamente");
        } catch (ValidacionException ex) {
            periodicidadView.mostrarMensaje("Error de validación: " + ex.getMessage());
        } catch (SQLException ex) {
            periodicidadView.mostrarMensaje("Error de base de datos: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            periodicidadView.mostrarMensaje("Error inesperado: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void editarPeriodicidad() {
        DefaultTableModel model = (DefaultTableModel) periodicidadView.getPeriodicidadesTable().getModel();
        try {
            int rowSelected = periodicidadView.getPeriodicidadesTable().getSelectedRow();

            if(rowSelected == -1){
                periodicidadView.mostrarMensaje("Debes seleccionar una fila primero");
            }
            Long idPeriodicidad = (Long) periodicidadView.getPeriodicidadesTable().getValueAt(rowSelected, 0);
            System.out.println(idPeriodicidad);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        //MainWindow.ShowJPanel();


    }

    private JFrame jPanelToJFrame(){
        return (JFrame) SwingUtilities.getWindowAncestor(periodicidadView);
    }

    private void abrirEditDialog(){
        editarDialog.setVisible(true);
    }
}
