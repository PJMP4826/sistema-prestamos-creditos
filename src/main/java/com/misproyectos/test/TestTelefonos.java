package com.misproyectos.test;

import com.misproyectos.models.Cliente;
import com.misproyectos.models.TelefonoCliente;
import com.misproyectos.repositories.ClienteRepository;
import com.misproyectos.repositories.TelefonoRepository;

import java.util.List;

public class TestTelefonos {

    public static void main(String[] args) {
        try {
//            ClienteRepository repClient = new ClienteRepository();
//            TelefonoRepository repTel = new TelefonoRepository();
//
//            List<Cliente> clientes = repClient.getClients();
//
//            for(Cliente cliente : clientes){
//                System.out.println(cliente.toString());
//            }

            ClienteRepository repClient = new ClienteRepository();
            TelefonoRepository repTel = new TelefonoRepository();

            List<Cliente> clientes = repClient.getClients();

            for (Cliente cliente : clientes) {
                List<TelefonoCliente> telefonos = repTel.findByClientId(cliente.getId());

                cliente.setTelefonoClientes(telefonos);
                System.out.println(cliente.toString());

                for (TelefonoCliente telefono : telefonos) {
                    System.out.println(telefono.toString());
                }
            }


        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }
}
