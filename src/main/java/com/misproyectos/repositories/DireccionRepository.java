package com.misproyectos.repositories;

import com.misproyectos.config.Database;
import com.misproyectos.interfaces.DireccionRepInterface;
import com.misproyectos.models.DireccionCliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DireccionRepository implements DireccionRepInterface {
    private Connection conn;

    public DireccionRepository() {
        conn = Database.getInstance().getConnection();
    }

    @Override
    public boolean add(DireccionCliente direccionCliente) throws SQLException {
        String sql = "INSERT INTO direcciones_clientes (cliente_id, direccion) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, direccionCliente.getIdCliente());
            stmt.setString(2, direccionCliente.getDescription());

            int rowAffected = stmt.executeUpdate();
            if (rowAffected <= 0) {
                throw new SQLException("Error al guardar direccion del cliente");
            }
            return true;
        }
    }

    @Override
    public boolean update(DireccionCliente direccionCliente) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int idDireccion) throws SQLException {
        return false;
    }

    @Override
    public DireccionCliente findById(int idDireccion) throws SQLException {
        return null;
    }

    @Override
    public DireccionCliente findByClientId(Long idCliente) throws SQLException {
        DireccionCliente address = new DireccionCliente();
        String sql = "SELECT id, cliente_id, direccion FROM direcciones_clientes WHERE cliente_id = ?";

        try (
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setLong(1, idCliente);
            try(ResultSet result = stmt.executeQuery()){
                while (result.next()){
                    address.setIdDireccion(result.getLong("id"));
                    address.setIdCliente(result.getLong("cliente_id"));
                    address.setDescription(result.getString("direccion"));
                }
            }

            return  address;
        }

    }
}
