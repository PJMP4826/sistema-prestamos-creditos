package com.misproyectos.repositories;

import com.misproyectos.config.Database;
import com.misproyectos.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioRepository {
    private final Connection conn;

    public UsuarioRepository() {
        this.conn = Database.getInstance().getConnection();
    }

    public boolean add(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios(nombre, email, password) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getPassword());

            int rowAffected = stmt.executeUpdate();

            if (rowAffected <= 0) {
                throw new SQLException("Errror al registrar usuario");
            }
            return true;
        }
    }
}
