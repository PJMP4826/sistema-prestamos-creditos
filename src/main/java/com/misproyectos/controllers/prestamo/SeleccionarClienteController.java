package com.misproyectos.controllers.prestamo;

import com.misproyectos.controllers.PrestamoController;
import com.misproyectos.models.Cliente;
import com.misproyectos.views.Prestamos.SeleccionarCliente;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class SeleccionarClienteController extends PrestamoController {
    private final SeleccionarCliente selectClientView;

    public SeleccionarClienteController(SeleccionarCliente selectClientView) {
        super();
        this.selectClientView = selectClientView;
    }

    public void loadClientesJBox() {
        try {
            List<Cliente> clientes = serviceClient.listarClientes();

            JComboBox<String> select = selectClientView.getJComboBoxCliente();

            select.removeAllItems();

            for (Cliente c : clientes) {
                select.addItem(c.getNombre());
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
