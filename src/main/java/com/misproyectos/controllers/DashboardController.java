package com.misproyectos.controllers;

import com.misproyectos.repositories.DashboardRepository;
import com.misproyectos.repositories.PrestamoRepository;
import com.misproyectos.views.Dashboard;

import java.sql.SQLException;

public class DashboardController {
    private final Dashboard dashboardView;
    private DashboardRepository repositoryDash;
    private PrestamoRepository prestamoRepository;

    public DashboardController(Dashboard dashboardView) {
        this.dashboardView = dashboardView;
        this.repositoryDash = new DashboardRepository();
        this.prestamoRepository = new PrestamoRepository();
    }

    public void loadCards() {
        try {
            String clientesRegistrados = String.valueOf(repositoryDash.countClientesByUsuarioId());
            String prestamosActivos = String.valueOf(prestamoRepository.countPrestamosPendientes());

            dashboardView.setClientesRegistrados(clientesRegistrados);
            dashboardView.setNumPrestamosActivos(prestamosActivos);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
