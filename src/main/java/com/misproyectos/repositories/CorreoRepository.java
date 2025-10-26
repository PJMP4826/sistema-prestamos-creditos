package com.misproyectos.repositories;

import com.misproyectos.config.Database;
import com.misproyectos.interfaces.CorreoRepInterface;
import com.misproyectos.models.CorreoCliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CorreoRepository implements CorreoRepInterface {

    private Connection conn;

    public CorreoRepository() {
        conn = Database.getInstance().getConnection();
    }

    @Override
    public boolean add(CorreoCliente correoCliente) throws SQLException {
        String sql = "INSERT INTO correos_clientes (cliente_id, correo) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, correoCliente.getIdCliente());
            stmt.setString(2, correoCliente.getCorreo());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected <= 0) {
                throw new SQLException("Error al guardar correo de cliente");
            }
            return true;
        }
    }

    @Override
    public boolean update(CorreoCliente correoCliente) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int idTelefono) throws SQLException {
        return false;
    }

    @Override
    public CorreoCliente findById(int idCorreo) throws SQLException {
        return null;
    }

    @Override
    public List<CorreoCliente> findByClientId(int idCorreo) throws SQLException {
        return List.of();
    }
}
