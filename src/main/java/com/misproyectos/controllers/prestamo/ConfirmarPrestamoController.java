package com.misproyectos.controllers.prestamo;

import com.misproyectos.controllers.PrestamoController;
import com.misproyectos.enums.EstadoPrestamo;
import com.misproyectos.exceptions.ValidacionException;
import com.misproyectos.interfaces.PrestamoViewInterface;
import com.misproyectos.models.Cliente;
import com.misproyectos.models.Periodicidad;
import com.misproyectos.models.Prestamo;
import com.misproyectos.repositories.PrestamoRepository;
import com.misproyectos.service.PeriodicidadService;
import com.misproyectos.service.PrestamoService;
import com.misproyectos.views.Prestamos.ConfirmarPrestamo;
import com.misproyectos.views.Prestamos.DatosDelPrestamo;
import com.misproyectos.views.Prestamos.SeleccionarCliente;
import com.misproyectos.views.Prestamos.SeleccionarPeriodicidad;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

public class ConfirmarPrestamoController extends PrestamoController implements PrestamoViewInterface {
    private final ConfirmarPrestamo confirmarPrestamoView;
    private final DatosDelPrestamo datosDelPrestamoView;
    private final SeleccionarClienteController seleccionarClienteController;
    private final SeleccionarPeriodicidadController seleccionarPeriodicidadController;
    private final PrestamoService servicePrestamo;
    private final PeriodicidadService servicePeriodicidad;

    public ConfirmarPrestamoController(
            ConfirmarPrestamo confirmarPrestamoView,
            DatosDelPrestamo datosDelPrestamoView,
            PrestamoService servicePrestamo,
            PeriodicidadService servicePeriodicidad
    ) {
        this.confirmarPrestamoView = confirmarPrestamoView;
        this.datosDelPrestamoView = datosDelPrestamoView;
        this.seleccionarClienteController = new SeleccionarClienteController(
                new SeleccionarCliente()
        );
        this.seleccionarPeriodicidadController = new SeleccionarPeriodicidadController(
                new SeleccionarPeriodicidad()
        );
        this.servicePrestamo = servicePrestamo;
        this.servicePeriodicidad = servicePeriodicidad;
    }

    public void initListeners() {
        confirmarPrestamoView.getConfirmarPrestamoBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guadarPrestamo();
            }
        });
    }

    public void autoFillLabels() throws ValidacionException, SQLException {
        confirmarPrestamoView.setClienteSelectedLbl(seleccionarClienteController.getSelectItemClienteJComboBox());
        confirmarPrestamoView.setPeriodicidadSelectedLbl(seleccionarPeriodicidadController.getSelectItemPeriodicidadJComboBox());

        DecimalFormat format = new DecimalFormat("$#,##0.00");
        String importe = format.format(datosDelPrestamoView.getImporteSpinner());

        confirmarPrestamoView.setImporteSelectedLbl(importe);
        confirmarPrestamoView.setPlazoSelectedLbl(datosDelPrestamoView.getPlazoSpinner().toString());

        Periodicidad periodicidad = servicePeriodicidad.findById(seleccionarPeriodicidadController.getSelectedPeriodicidadId());
        double tasaInteres = periodicidad.getPorcentajeIntereses();
        double plazo = datosDelPrestamoView.getPlazoSpinner().doubleValue();

        String importeLimpio = importe.replace("$", "").replace(",", "").trim();
        double montoCuota = servicePrestamo.calcularMontoPorCuota(
                Double.parseDouble(importeLimpio),
                tasaInteres,
                plazo
        );

        double totalPagar = servicePrestamo.calcularTotalToPagar(montoCuota, plazo);
        double interesTotal = servicePrestamo.calcularInteresTotal(totalPagar, Double.parseDouble(importeLimpio));

        confirmarPrestamoView.setInteresSelectedLbl(String.valueOf(interesTotal));
        confirmarPrestamoView.setTotalPagarSelectedLbl(String.valueOf(totalPagar));
        confirmarPrestamoView.setNoCuotasSelectedLbl(String.valueOf(plazo));
        confirmarPrestamoView.setMontoCuotaSelectedLbl(String.valueOf(montoCuota));

    }

    public void guadarPrestamo() {
        PrestamoRepository prestamoRep = new PrestamoRepository();

        Cliente cliente = new Cliente();
        Periodicidad periodicidad = new Periodicidad();

        cliente.setId(seleccionarClienteController.getSelectedClienteId());
        periodicidad.setIdPeriodicidad(seleccionarPeriodicidadController.getSelectedPeriodicidadId());

        EstadoPrestamo estadoPrest = EstadoPrestamo.fromTag("Activo");
        Prestamo prestamo = Prestamo.builder()
                .setMontoPrestado(datosDelPrestamoView.getImporteSpinner())
                .setPlazoPago(datosDelPrestamoView.getPlazoSpinner().doubleValue())
                .setSaldoPendiente(datosDelPrestamoView.getImporteSpinner().doubleValue())
                .setEstadoPrestamo(estadoPrest)
                .build();

        try {
            prestamoRep.add(prestamo, cliente, periodicidad);
            confirmarPrestamoView.mostrarMensaje("Prestamo registrado correctamente");
        } catch (SQLException ex) {
            confirmarPrestamoView.mostrarMensaje("Error de base de datos: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void loadPrestamos() {
        DefaultTableModel model = (DefaultTableModel) confirmarPrestamoView.getListaPrestamosTable().getModel();
        model.setRowCount(0);

        try {
            PrestamoRepository prestamoRepository = new PrestamoRepository();
            List<Prestamo> prestamos = prestamoRepository.findAll();

            for (Prestamo p : prestamos) {
                String nombreCliente = p.getCliente().getNombre();
                String nombrePeriodicidad = p.getPeriodicidadPago().getNombrePeriodicidad();

                model.addRow(new Object[]{
                        nombreCliente,
                        p.getMontoPrestado(),
                        p.getPlazoPago(),
                        nombrePeriodicidad,
                        p.getFechaInicio().toString(),
                        p.getSaldoPendiente(),
                        p.getEstadoPrestamo()
                });
            }
        } catch (SQLException ex) {
            confirmarPrestamoView.mostrarMensaje("Error al obtener los prestamos: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
