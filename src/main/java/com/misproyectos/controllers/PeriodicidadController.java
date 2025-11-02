package com.misproyectos.controllers;

import com.misproyectos.exceptions.ValidacionException;
import com.misproyectos.models.Periodicidad;
import com.misproyectos.service.PeriodicidadService;
import com.misproyectos.views.periodicidades.Periodicidades;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class PeriodicidadController {
    private final Periodicidades periodicidadView;
    private final PeriodicidadService service;

    public PeriodicidadController(
            Periodicidades view,
            PeriodicidadService service
    ) {
        this.periodicidadView = view;
        this.service = service;
    }

    public void loadPeriodicidades() {
        DefaultTableModel model = (DefaultTableModel) periodicidadView.getPeriodicidadesTable().getModel();
        model.setRowCount(0);

        try {
            List<Periodicidad> periodicidads = service.getPeriodicidades();

            for(Periodicidad periodicidad : periodicidads){
                System.out.println(periodicidad.toString());
                model.addRow(new Object[] {
                        periodicidad.getNombrePeriodicidad(),
                        periodicidad.getDiasPeriodicidad(),
                        periodicidad.getPorcentajeIntereses()
                });
            }

        }catch (SQLException e){
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
}
