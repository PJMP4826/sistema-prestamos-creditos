package com.misproyectos.controllers;

import com.misproyectos.models.Periodicidad;
import com.misproyectos.repositories.PeriodicidadesRepository;
import com.misproyectos.service.PeriodicidadService;
import com.misproyectos.views.Prestamos.SeleccionarPeriodicidad;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class PrestamoController {
    private final SeleccionarPeriodicidad periodicidadView;
    private final PeriodicidadService service;

    public PrestamoController(
            SeleccionarPeriodicidad periodicidadView
    ) {
        this.periodicidadView = periodicidadView;
        this.service = new PeriodicidadService(new PeriodicidadesRepository());
    }

    public void loadPeriodicidadesJBox() {
        try {
            List<Periodicidad> periodicidades = service.getPeriodicidades();

            JComboBox<String> select = periodicidadView.getJComboBoxPeriodicidades();

            select.removeAllItems();

            for (Periodicidad p : periodicidades) {
                select.addItem(p.getNombrePeriodicidad());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
