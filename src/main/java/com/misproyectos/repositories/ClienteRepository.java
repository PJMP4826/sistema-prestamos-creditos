package com.misproyectos.repositories;

import com.misproyectos.config.Database;
import com.misproyectos.interfaces.ClienteRepInterface;
import com.misproyectos.models.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository implements ClienteRepInterface {
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

    public boolean existeClientByRfc(String rfc) {
        String sql = "SELECT COUNT(*) FROM clientes WHERE rfc = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, rfc.trim().toUpperCase());

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public Long add(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nombre, rfc) VALUES (?, ?) RETURNING id";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getRfc());

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    return res.getLong("id");
                } else {
                    throw new SQLException("Error al guardar cliente");
                }
            }
        }
    }
}
