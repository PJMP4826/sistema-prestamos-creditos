package com.misproyectos.test;

import com.misproyectos.enums.EstadoPrestamo;
import com.misproyectos.models.Cliente;
import com.misproyectos.models.Periodicidad;
import com.misproyectos.models.Prestamo;
import com.misproyectos.repositories.PrestamoRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TestAddPrestamos {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        Periodicidad periodicidad = new Periodicidad();

        cliente.setId(11L);
        periodicidad.setIdPeriodicidad(1L);

        PrestamoRepository prestamoRep = new PrestamoRepository();

        EstadoPrestamo estadoPrest = EstadoPrestamo.fromTag("Activo");
        Prestamo prestamo = Prestamo.builder()
                .setMontoPrestado(BigDecimal.valueOf(5000))
                .setPlazoPago(12)
                .setFechaInicio(Timestamp.valueOf("2025-11-25 12:24:12"))
                .setSaldoPendiente(5000)
                .setEstadoPrestamo(estadoPrest)
                .build();

        try{
            prestamoRep.add(prestamo, cliente, periodicidad);
        }catch (SQLException e){
            System.out.println("Error:" + e.getMessage());
        }
    }
}
