package com.misproyectos.repositories;

import com.misproyectos.config.Database;
import com.misproyectos.enums.TipoTelefono;
import com.misproyectos.interfaces.TelefonoRepInterface;
import com.misproyectos.models.TelefonoCliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<TelefonoCliente> findByClientId(Long idCliente) throws SQLException {
        List<TelefonoCliente> phones = new ArrayList<>();
        String sql = "SELECT id, cliente_id, telefono, tipo FROM telefonos_clientes WHERE cliente_id = ?";

        try (
                PreparedStatement stmt = conn.prepareStatement(sql)

        ) {
            stmt.setLong(1, idCliente);
            try(ResultSet result = stmt.executeQuery()){
                while (result.next()) {
                    TelefonoCliente telefono = new TelefonoCliente();
                    telefono.setIdTelefono(result.getLong("id"));
                    telefono.setIdCliente(result.getLong("cliente_id"));
                    telefono.setTelefono(result.getString("telefono"));

                    TipoTelefono tipo = TipoTelefono.fromTag(result.getString("tipo"));
                    telefono.setTipo(tipo);
                    phones.add(telefono);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return phones;
    }
}
