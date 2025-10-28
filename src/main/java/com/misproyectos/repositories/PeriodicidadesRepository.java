package com.misproyectos.repositories;

import com.misproyectos.config.Database;
import com.misproyectos.interfaces.PeriodicidadesRepInterface;
import com.misproyectos.models.Periodicidad;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.List;

public class PeriodicidadesRepository extends PeriodicidadesRepInterface {
    private Connection conn;

    public PeriodicidadesRepository() {
        this.conn = Database.getInstance().getConnection();
    }

    public boolean add(Periodicidad periodicidad) throws SQLException {
        String sql = """
                INSERT INTO periodicidad_pago(
                nombre_periodicidad, dias_periodicidad, porcentaje_intereses)
                VALUES (?, ?, ?)
                """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1,periodicidad.getNombrePeriodicidad());
            stmt.setInt(2, periodicidad.getDiasPeriodicidad());
            stmt.setInt(3, periodicidad.getPorcentajeIntereses());

            int rowAffected = stmt.executeUpdate();

            if(rowAffected <= 0){
                throw new SQLException("Error al guardar periodicidad");
            }

            return true;
        }
    }

    public boolean delete(Long idPeriodicidad) throws SQLException {
        return false;
    }

    public List<Periodicidad> findAll() throws SQLException {
        return null;
    }
}
