package com.misproyectos.service;

import com.misproyectos.config.Database;
import com.misproyectos.models.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    private Connection conn;

    public ClienteRepository() {
        this.conn = Database.getInstance().getConnection();
    }

    public List<Cliente> getClients() {
        String sql = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();

        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet result = stmt.executeQuery();
        ) {
            while (result.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(result.getLong("id"));
                cliente.setNombre(result.getString("nombre"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return clientes;
    }
}
