package com.misproyectos.controllers;

import com.misproyectos.enums.TipoTelefono;
import com.misproyectos.exceptions.ValidacionException;
import com.misproyectos.models.Cliente;
import com.misproyectos.models.CorreoCliente;
import com.misproyectos.models.DireccionCliente;
import com.misproyectos.models.TelefonoCliente;
import com.misproyectos.repositories.ClienteRepository;
import com.misproyectos.repositories.CorreoRepository;
import com.misproyectos.repositories.DireccionRepository;
import com.misproyectos.repositories.TelefonoRepository;
import com.misproyectos.service.ClienteService;
import com.misproyectos.views.clientes.AddClientes;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class ClienteController {
    private final AddClientes clientView;
    private final ClienteService service;

    public ClienteController(AddClientes clientView) {
        this.clientView = clientView;
        this.service = new ClienteService(
                new ClienteRepository(),
                new TelefonoRepository(),
                new DireccionRepository(),
                new CorreoRepository()
        );
    }

    public void initListener() {
        clientView.getSaveBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    guardarCliente();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void guardarCliente() throws SQLException {
        try {
            Cliente cliente = new Cliente();
            TelefonoCliente telefonoCliente = new TelefonoCliente();
            DireccionCliente direccionCliente = new DireccionCliente();
            CorreoCliente correoCliente = new CorreoCliente();

            cliente.setNombre(clientView.getNombre());
            cliente.setRfc(clientView.getRfc());

            TipoTelefono tipo = TipoTelefono.fromTag(clientView.getTipoTelefono());
            telefonoCliente.setTelefono(clientView.getTelefono());
            telefonoCliente.setTipo(tipo);

            direccionCliente.setDescription(clientView.getDireccion());
            correoCliente.setCorreo(clientView.getCorreo());

            boolean isSuccess = this.service.registrarCliente(
                    cliente,
                    tipo,
                    telefonoCliente,
                    direccionCliente,
                    correoCliente
            );

            if (!isSuccess) {
                throw new ValidacionException("Error de validación");
            }

            clientView.mostrarMensaje("Cliente registrado correctamente");

        } catch (ValidacionException ex) {
            clientView.mostrarMensaje("Error de validación: " + ex.getMessage());
        } catch (SQLException ex) {
            clientView.mostrarMensaje("Error de base de datos: " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            clientView.mostrarMensaje("Error inesperado: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void loadClientes() {
        DefaultTableModel model = (DefaultTableModel) clientView.getClientesTable().getModel();
        model.setRowCount(0);

        try {
            List<Cliente> clientes = service.listarClientes();

            for (Cliente cliente : clientes) {
                String telefonos = "";
                for (TelefonoCliente tel : cliente.getTelefonoClientes()) {
                    telefonos += tel.getTelefono() + " ";
                }

                String correo = cliente.getCorreoClientes() != null ? cliente.getCorreoClientes().getCorreo() : "";
                String direccion = cliente.getDireccionClientes() != null ? cliente.getDireccionClientes().getDescription() : "";

                System.out.println(cliente.getRfc());
                model.addRow(new Object[]{
                        cliente.getNombre(),
                        cliente.getRfc(),
                        telefonos.trim(),
                        correo,
                        direccion
                });
            }

        } catch (SQLException e) {
            clientView.mostrarMensaje("Error al cargar clientes: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
