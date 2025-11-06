package com.misproyectos.controllers;

import com.misproyectos.models.Prestamo;
import com.misproyectos.repositories.DashboardRepository;
import com.misproyectos.repositories.PrestamoRepository;
import com.misproyectos.views.Dashboard;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

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

    public void loadPrestamos() {
        DefaultTableModel model = (DefaultTableModel) dashboardView.getPeriodicidadesTable().getModel();
        model.setRowCount(0);

        try {
            List<Prestamo> prestamos = repositoryDash.getTop5PrestamosByUsuario();

            for (Prestamo p : prestamos) {
                model.addRow(new Object[]{
                        p.getCliente().getNombre(),
                        p.getCliente().getCorreoClientes().getCorreo(),
                        p.getMontoPrestado(),
                        p.getMontoPrestado(),
                        p.getPeriodicidadPago().getNombrePeriodicidad(),
                        p.getFechaInicio(),
                        p.getSaldoPendiente(),
                        p.getEstadoPrestamo().getTag()
                });
            }

        } catch (SQLException e) {
            dashboardView.mostrarMensaje("Errror al cargar los ultimos prestamos" + e.getMessage());
        }
    }
}
