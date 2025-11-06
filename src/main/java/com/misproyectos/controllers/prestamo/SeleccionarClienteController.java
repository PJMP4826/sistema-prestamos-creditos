package com.misproyectos.controllers.prestamo;

import com.misproyectos.controllers.PrestamoController;
import com.misproyectos.models.Cliente;
import com.misproyectos.models.PrestamoComboItem;
import com.misproyectos.views.Prestamos.SeleccionarCliente;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class SeleccionarClienteController extends PrestamoController {
    private final SeleccionarCliente selectClientView;
    private static Long selectedClienteId;

    public SeleccionarClienteController(SeleccionarCliente selectClientView) {
        super();
        this.selectClientView = selectClientView;
    }

    public void loadClientesJBox() {
        try {
            List<Cliente> clientes = serviceClient.listarClientes();

            JComboBox<PrestamoComboItem> select = selectClientView.getJComboBoxCliente();

            select.removeAllItems();

            for (Cliente c : clientes) {
                //System.out.println(c.toString());
                select.addItem(new PrestamoComboItem(
                        c.getId(),
                        c.getNombre()
                ));
            }

            if (selectedClienteId != null) {
                for (int i = 0; i < select.getItemCount(); i++) {
                    PrestamoComboItem item = select.getItemAt(i);
                    if (item.getId().equals(selectedClienteId)) {
                     select.setSelectedIndex(i);
                     break;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Long getSelectedClienteId() {
        JComboBox<PrestamoComboItem> select = selectClientView.getJComboBoxCliente();
        PrestamoComboItem selectItem = (PrestamoComboItem) select.getSelectedItem();
        selectedClienteId = selectItem != null ? selectItem.getId() : null;
        return selectedClienteId;
    }

    public String getSelectItemClienteJComboBox() {
        JComboBox<PrestamoComboItem> select = selectClientView.getJComboBoxCliente();
        PrestamoComboItem selectItem = (PrestamoComboItem) select.getSelectedItem();
        return selectItem != null ? selectItem.toString() : "";
    }

    @Override
    public JTable getPrestamoTable() {
        return selectClientView.getListaPrestamosTable();
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        selectClientView.mostrarMensaje(mensaje);
    }
}
