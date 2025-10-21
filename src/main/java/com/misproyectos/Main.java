package com.misproyectos;

import com.misproyectos.config.Database;
import com.misproyectos.models.Cliente;
import com.misproyectos.repositories.ClienteRepository;

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

            ClienteRepository rep = new ClienteRepository();
            Cliente cliente = new Cliente();
            cliente.setNombre("Fran");
            cliente.setRfc("FRGABHBJB2BJH2");

            int clientId = rep.add(cliente);

            System.out.println("Cliente id: " + clientId);

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