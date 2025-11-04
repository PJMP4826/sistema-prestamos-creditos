package com.misproyectos.controllers.prestamo;

import com.misproyectos.controllers.PrestamoController;
import com.misproyectos.models.Periodicidad;
import com.misproyectos.models.PrestamoComboItem;
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

            JComboBox<PrestamoComboItem> select = selectPeriodicidadView.getJComboBoxPeriodicidades();

            select.removeAllItems();

            for (Periodicidad p : periodicidades) {
                select.addItem(new PrestamoComboItem(
                        p.getIdPeriodicidad(),
                        p.getNombrePeriodicidad()
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Long getSelectedPeriodicidadId() {
        JComboBox<PrestamoComboItem> select = selectPeriodicidadView.getJComboBoxPeriodicidades();
        PrestamoComboItem selectItem = (PrestamoComboItem) select.getSelectedItem();
        return selectItem != null ? selectItem.getId() : null;
    }

    public String getSelectItemPeriodicidadJComboBox() {
        JComboBox<PrestamoComboItem> select = selectPeriodicidadView.getJComboBoxPeriodicidades();
        PrestamoComboItem selectItem = (PrestamoComboItem) select.getSelectedItem();
        return selectItem != null ? selectItem.toString() : "";
    }

    @Override
    public JTable getPrestamoTable() {
        return selectPeriodicidadView.getListaPrestamosTable();
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        selectPeriodicidadView.mostrarMensaje(mensaje);
    }
}
