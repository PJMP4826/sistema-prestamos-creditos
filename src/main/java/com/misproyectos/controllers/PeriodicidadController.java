package com.misproyectos.controllers;

import com.misproyectos.exceptions.ValidacionException;
import com.misproyectos.models.Periodicidad;
import com.misproyectos.service.PeriodicidadService;
import com.misproyectos.views.periodicidades.Periodicidades;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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
