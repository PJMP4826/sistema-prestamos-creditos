package com.misproyectos.controllers;

import com.misproyectos.repositories.*;
import com.misproyectos.service.ClienteService;
import com.misproyectos.service.PeriodicidadService;


public class PrestamoController {
    protected final PeriodicidadService servicePeriodicidad;
    protected final ClienteService serviceClient;

    public PrestamoController() {
        this.servicePeriodicidad = new PeriodicidadService(
                new PeriodicidadesRepository()
        );
        this.serviceClient = new ClienteService(
                new ClienteRepository(),
                new TelefonoRepository(),
                new DireccionRepository(),
                new CorreoRepository()
        );
    }
}
