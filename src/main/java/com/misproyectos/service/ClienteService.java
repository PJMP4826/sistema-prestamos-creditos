package com.misproyectos.service;

import com.misproyectos.enums.TipoTelefono;
import com.misproyectos.exceptions.ValidacionException;
import com.misproyectos.interfaces.ClienteRepInterface;
import com.misproyectos.interfaces.CorreoRepInterface;
import com.misproyectos.interfaces.DireccionRepInterface;
import com.misproyectos.interfaces.TelefonoRepInterface;
import com.misproyectos.models.Cliente;
import com.misproyectos.models.CorreoCliente;
import com.misproyectos.models.DireccionCliente;
import com.misproyectos.models.TelefonoCliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteService {
    private final ClienteRepInterface repClient;
    private final TelefonoRepInterface repTel;
    private final DireccionRepInterface repDireccion;
    private final CorreoRepInterface repCorreo;

    public ClienteService(
            ClienteRepInterface repClient,
            TelefonoRepInterface repTel,
            DireccionRepInterface repDireccion,
            CorreoRepInterface repCorreo
    ) {
        this.repClient = repClient;
        this.repTel = repTel;
        this.repDireccion = repDireccion;
        this.repCorreo = repCorreo;
    }


    public boolean registrarCliente(
            Cliente cliente,
            TipoTelefono tipoTelefono,
            TelefonoCliente telefonoCliente,
            DireccionCliente direccionCliente,
            CorreoCliente correoCliente
    ) throws ValidacionException, SQLException {
        ValidationService.validarEntradas(cliente, telefonoCliente, correoCliente);

        //sanitizar
        ValidationService.limpiarEntradas(
                cliente,
                telefonoCliente,
                correoCliente,
                direccionCliente
        );

        if (repClient.existeClientByRfc(cliente.getRfc())) {
            throw new SQLException("RFC de Cliente ya existente");
        }

        Long clientId = this.repClient.add(cliente);
        if (clientId <= 0) {
            throw new SQLException("No se pudo registrar el cliente");
        }

        telefonoCliente.setIdCliente(clientId);
        telefonoCliente.setTipo(TipoTelefono.fromTag(tipoTelefono.getTag()));

        direccionCliente.setIdCliente(clientId);
        correoCliente.setIdCliente(clientId);

        this.repTel.add(telefonoCliente);
        this.repDireccion.add(direccionCliente);
        this.repCorreo.add(correoCliente);

        return true;
    }

    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> clientes = repClient.getClients();

        for (Cliente cliente : clientes) {
            cliente.setTelefonoClientes(repTel.findByClientId(cliente.getId()));
            cliente.setDireccionClientes(repDireccion.findByClientId(cliente.getId()));
            cliente.setCorreoClientes(repCorreo.findByClientId(cliente.getId()));
        }

        return clientes;
    }

    public boolean deleteCliente(String rfc) throws ValidacionException, SQLException {
        ValidationService.existeRFC(rfc);
        //ValidationService.validarRFC(rfc);

        if (!repClient.existeClientByRfc(rfc)) {
            throw new SQLException("RFC de Cliente ya existente");
        }

        return repClient.delete(rfc);
    }
}
