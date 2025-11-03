package com.misproyectos.controllers.prestamo;

import com.misproyectos.controllers.PrestamoController;
import com.misproyectos.models.Periodicidad;
import com.misproyectos.views.Prestamos.SeleccionarPeriodicidad;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class SeleccionarPeriodicidadController extends PrestamoController {
    private final SeleccionarPeriodicidad selectPeriodicidadView;

    public SeleccionarPeriodicidadController(SeleccionarPeriodicidad selectPeriodicidadView) {
        super();
        this.selectPeriodicidadView = selectPeriodicidadView;
    }

    public void loadPeriodicidadesJBox() {
        try {
            List<Periodicidad> periodicidades = servicePeriodicidad.getPeriodicidades();

            JComboBox<String> select = selectPeriodicidadView.getJComboBoxPeriodicidades();

            select.removeAllItems();

            for (Periodicidad p : periodicidades) {
                select.addItem(p.getNombrePeriodicidad());
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
