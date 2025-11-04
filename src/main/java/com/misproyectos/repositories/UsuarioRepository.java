package com.misproyectos.repositories;

import com.misproyectos.config.Database;
import com.misproyectos.models.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public boolean existeUsuarioByTagName(Usuario usuario) throws SQLException {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE nombre = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNombreUsuario());

            try (ResultSet result = stmt.executeQuery()) {
                result.next();
                int count = result.getInt(1);

                //si count es mayor a cero el usuario existe
                //si count es menor a cero el usuario no existe
                return count > 0;
            }
        }
    }

    public String findPasswordByTagName(String tagName) throws SQLException {
        String sql = "SELECT password FROM usuarios WHERE nombre = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tagName);

            try (ResultSet result = stmt.executeQuery()) {
                if(result.next()){
                    return result.getString("password");
                }
                return null;
            }
        }
    }
}
