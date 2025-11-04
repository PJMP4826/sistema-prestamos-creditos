package com.misproyectos.controllers.prestamo;

import com.misproyectos.controllers.PrestamoController;
import com.misproyectos.views.Prestamos.DatosDelPrestamo;

import javax.swing.*;

public class DatosDelPrestamoController extends PrestamoController {
    private final DatosDelPrestamo datosDelPrestamoView;

    public DatosDelPrestamoController(DatosDelPrestamo datosDelPrestamoView) {
        this.datosDelPrestamoView = datosDelPrestamoView;
    }

    @Override
    public JTable getPrestamoTable() {
        return datosDelPrestamoView.getListaPrestamosTable();
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        datosDelPrestamoView.mostrarMensaje(mensaje);
    }
}
