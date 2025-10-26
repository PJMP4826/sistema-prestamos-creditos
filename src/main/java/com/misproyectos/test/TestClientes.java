package com.misproyectos.test;

import com.misproyectos.models.Cliente;
import com.misproyectos.models.CorreoCliente;
import com.misproyectos.models.DireccionCliente;
import com.misproyectos.models.TelefonoCliente;
import com.misproyectos.repositories.ClienteRepository;
import com.misproyectos.repositories.CorreoRepository;
import com.misproyectos.repositories.DireccionRepository;
import com.misproyectos.repositories.TelefonoRepository;

import java.util.List;

public class TestClientes {
    public static void main(String[] args) {
        try {
            ClienteRepository repClient = new ClienteRepository();
            TelefonoRepository repTel = new TelefonoRepository();
            CorreoRepository repCorreo = new CorreoRepository();
            DireccionRepository repAddress = new DireccionRepository();

            List<Cliente> clientes = repClient.getClients();

            for (Cliente cliente : clientes) {
                List<TelefonoCliente> telefonos = repTel.findByClientId(cliente.getId());
                CorreoCliente correoCliente = repCorreo.findByClientId(cliente.getId());
                DireccionCliente direccionCliente = repAddress.findByClientId(cliente.getId());

                cliente.setTelefonoClientes(telefonos);
                cliente.setCorreoClientes(correoCliente);
                cliente.setDireccionClientes(direccionCliente);

                System.out.println(cliente.toString());

//                for (TelefonoCliente telefono : telefonos) {
//                    System.out.println(telefono.toString());
//                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }
}
