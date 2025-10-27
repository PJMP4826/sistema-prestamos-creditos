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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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

            if (isSuccess) {
                clientView.mostrarMensaje("Cliente registrado correctamente");
            } else {
                throw new ValidacionException("Error de validación");
            }

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
}
