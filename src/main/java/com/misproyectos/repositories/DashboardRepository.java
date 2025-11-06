package com.misproyectos.repositories;

import com.misproyectos.config.Database;
import com.misproyectos.models.SessionUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardRepository {
    private Connection conn;

    public DashboardRepository() {
        this.conn = Database.getInstance().getConnection();
    }

    public int countClientesByUsuarioId() throws SQLException {
        String sql = "SELECT COUNT(*) FROM clientes WHERE usuario_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, SessionUsuario.getUsuarioActual().getIdUsuario());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return 0;
    }

}
