package com.misproyectos.repositories;

import com.misproyectos.config.Database;
import com.misproyectos.enums.EstadoPrestamo;
import com.misproyectos.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Prestamo> getTop5PrestamosByUsuario() throws SQLException {
        String sql = """
                    SELECT
                        c.nombre AS nombre_cliente,
                        e.correo,
                        p.importe,
                        p.plazo,
                        prd.nombre_periodicidad,
                        p.fecha_inicio,
                        p.saldo_actual,
                        p.aprobado
                    FROM prestamos p
                    INNER JOIN periodicidad_pago prd ON p.periodicidad_id = prd.id
                    INNER JOIN clientes c ON p.cliente_id = c.id
                    INNER JOIN correos_clientes e ON e.cliente_id = c.id
                    INNER JOIN usuarios u ON p.usuario_id = u.id
                    WHERE u.id = ?
                    ORDER BY p.fecha_inicio DESC
                    LIMIT 5
                """;

        List<Prestamo> prestamos = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, SessionUsuario.getUsuarioActual().getIdUsuario());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    CorreoCliente correoCliente = new CorreoCliente();
                    correoCliente.setCorreo(rs.getString("correo"));

                    Cliente cliente = new Cliente();
                    cliente.setNombre(rs.getString("nombre_cliente"));
                    cliente.setCorreoClientes(correoCliente);

                    Periodicidad periodicidad = new Periodicidad();
                    periodicidad.setNombrePeriodicidad(rs.getString("nombre_periodicidad"));

                    Prestamo prestamo = Prestamo.builder()
                            .setCliente(cliente)
                            .setMontoPrestado(rs.getBigDecimal("importe"))
                            .setPlazoPago(rs.getInt("plazo"))
                            .setPeriodicidadPago(periodicidad)
                            .setFechaInicio(Timestamp.valueOf(rs.getString("fecha_inicio")))
                            .setSaldoPendiente(rs.getDouble("saldo_actual"))
                            .setEstadoPrestamo(EstadoPrestamo.fromTag(rs.getString("aprobado")))
                            .build();

                    prestamos.add(prestamo);
                }
            }
        }
        return prestamos;
    }


}
