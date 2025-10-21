package com.misproyectos.repositories;

import com.misproyectos.config.Database;
import com.misproyectos.interfaces.TelefonoRepInterface;
import com.misproyectos.models.TelefonoCliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TelefonoRepository implements TelefonoRepInterface {
    private Connection conn;

    public TelefonoRepository() {
        this.conn = Database.getInstance().getConnection();
    }

    @Override
    public boolean add(TelefonoCliente telefonoCliente) throws SQLException {
        String sql = "INSERT INTO telefonos_clientes (cliente_id, telefono, tipo) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, telefonoCliente.getIdCliente());
            stmt.setString(2, telefonoCliente.getTelefono());
            stmt.setString(3, telefonoCliente.getTipo().getTag());

            int rowAffected = stmt.executeUpdate();
            if (rowAffected > 0) {
                return true;
            } else {
                throw new SQLException("Error al guardar el telefono del cliente");
            }
        }
    }

    @Override
    public boolean update(TelefonoCliente telefonoCliente) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int idTelefono) throws SQLException {
        return false;
    }

    @Override
    public TelefonoCliente findById(int idTelefono) throws SQLException {
        return null;
    }

    @Override
    public List<TelefonoCliente> findByClientId(int idTelefono) throws SQLException {
        return List.of();
    }
}
