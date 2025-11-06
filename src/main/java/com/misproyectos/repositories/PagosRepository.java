package com.misproyectos.repositories;

import com.misproyectos.config.Database;
import com.misproyectos.models.Pago;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PagosRepository {
    private Connection conn;

    public PagosRepository() {
        this.conn = Database.getInstance().getConnection();
    }

    public boolean registrarPago(Pago pago) throws SQLException {
        String sql = "CALL registrar_pago(?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, pago.getIdPrestamo());
            stmt.setBigDecimal(2, pago.getImporte());
            stmt.setLong(3, pago.getUsuarioId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new SQLException("Error al registrar su pago: " , e.getMessage());
        }
    }
}
