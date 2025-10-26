package com.misproyectos;

import com.misproyectos.config.Database;
import com.misproyectos.enums.TipoTelefono;
import com.misproyectos.models.Cliente;
import com.misproyectos.models.DireccionCliente;
import com.misproyectos.models.TelefonoCliente;
import com.misproyectos.repositories.ClienteRepository;
import com.misproyectos.repositories.DireccionRepository;
import com.misproyectos.repositories.TelefonoRepository;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Prestamo prestamo = Prestamo.builder()
//                .setIdPrestamo(123)
//                .build();
//
//        System.out.println("Prestamo: " + prestamo.toString());
//        System.out.println("Prestamo id: " + prestamo.getIdPrestamo());

        try {
            //Connection db = Database.getInstance().getConnection();
            //System.out.println("Conexion exitosa: " + db);

            ClienteRepository repClient = new ClienteRepository();
            Cliente cliente = new Cliente();
            cliente.setNombre("Daniel");
            cliente.setRfc("DAJKNJKCNSVCS");

            //id autogenerado por Postgres
            Long clientId = repClient.add(cliente);

            //Objects
            TipoTelefono tipo = TipoTelefono.fromTag("Móvil");
            TelefonoCliente telefono = new TelefonoCliente();

            DireccionCliente address = new DireccionCliente();

            //agregar Data
            telefono.setIdCliente(clientId);
            telefono.setTelefono("9723768645");
            telefono.setTipo(tipo);

            address.setIdCliente(clientId);
            address.setDescription("Calle 4, Centro, Gaviotas, Tabasco");

            //Insert data
            TelefonoRepository repTel = new TelefonoRepository();
            repTel.add(telefono);

            DireccionRepository repAddress = new DireccionRepository();
            repAddress.add(address);

            System.out.println("Cliente id: " + clientId);
            System.out.println("Cliente info: " + cliente.toString());

            //List<Cliente> clientes = rep.getClients();
            //for (Cliente c : clientes) {
                //System.out.println(c);
            //}

        } catch (Exception e) {
            System.out.println("Conexion faild: ");
            e.printStackTrace();
        }

    }
}