package com.misproyectos.test;

import com.misproyectos.models.Cliente;

import com.misproyectos.models.DireccionCliente;

import com.misproyectos.repositories.ClienteRepository;

import com.misproyectos.repositories.DireccionRepository;


import java.util.List;

public class TestDireccionRep {
    public static void main(String[] args) {
        try {
            ClienteRepository repClient = new ClienteRepository();
            DireccionRepository repAddress = new DireccionRepository();

            List<Cliente> clientes = repClient.getClients();

            for (Cliente cliente : clientes) {
                DireccionCliente direccionCliente = repAddress.findByClientId(cliente.getId());


                cliente.setDireccionClientes(direccionCliente);

                System.out.println(cliente.toString());


                //System.out.println(direccionCliente.toString());

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }
}
