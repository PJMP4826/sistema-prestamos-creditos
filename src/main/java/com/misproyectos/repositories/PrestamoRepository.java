package com.misproyectos.repositories;

import com.misproyectos.config.Database;
import com.misproyectos.interfaces.PrestamoRepInterface;
import com.misproyectos.models.Cliente;
import com.misproyectos.models.Periodicidad;
import com.misproyectos.models.Prestamo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrestamoRepository extends PrestamoRepInterface {
    private Connection conn;

    public PrestamoRepository() {
        this.conn = Database.getInstance().getConnection();
    }

    public boolean add(
            Prestamo prestamo,
            Cliente cliente,
            Periodicidad periodicidad
    ) throws SQLException {
        String sql = """
                   INSERT INTO prestamos(
                   cliente_id, periodicidad_id, importe, plazo, fecha_inicio, saldo_actual,
                   aprobado) VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, cliente.getId());
            stmt.setLong(2, periodicidad.getIdPeriodicidad());
            stmt.setBigDecimal(3, prestamo.getMontoPrestado());
            stmt.setDouble(4, prestamo.getPlazoPago());
            stmt.setTimestamp(5, prestamo.getFechaInicio());
            stmt.setDouble(6, prestamo.getSaldoPendiente());
            stmt.setString(7, prestamo.getEstadoPrestamo().getTag());

            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected <= 0){
                throw new SQLException("Errror al guadar el prestamo");
            }
            return  true;
        }
    }

    public boolean update(Prestamo prestamo) throws SQLException {
        return false;
    }

    public boolean delete(Long idPrestamo) throws SQLException {
        return false;
    }

    public Prestamo findById(Long idPrestamo) throws SQLException {
        return null;
    }

    public List<Prestamo> findAll() throws SQLException {
        String sql = """
                        SELECT c.nombre as nombre_cliente, p.importe, p.plazo, perd.nombre_periodicidad,
                        		p.fecha_inicio, p.saldo_actual
                        FROM
                            prestamos p
                        LEFT JOIN clientes c ON p.cliente_id = c.id
                        LEFT JOIN periodicidad_pago perd ON p.periodicidad_id = perd.id
                """;
        List<Prestamo> prestamos = new ArrayList<>();

        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet result = stmt.executeQuery();
        ) {
            while (result.next()) {
                Cliente cliente = new Cliente();
                cliente.setNombre(result.getString("nombre_cliente"));

                Periodicidad periodicidad = new Periodicidad();
                periodicidad.setNombrePeriodicidad(result.getString("nombre_periodicidad"));

                Prestamo prestamo = Prestamo.builder()
                        .setMontoPrestado(result.getBigDecimal("importe"))
                        .setPlazoPago(result.getInt("plazo"))
                        .setFechaInicio(result.getTimestamp("fecha_inicio"))
                        .setSaldoPendiente(result.getDouble("saldo_actual"))
                        .setCliente(cliente)
                        .setPeriodicidadPago(periodicidad)
                        .build();

                prestamos.add(prestamo);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los prestamos", e);
        }
        return prestamos;
    }
}
