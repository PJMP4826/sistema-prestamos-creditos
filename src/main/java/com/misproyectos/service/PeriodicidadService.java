package com.misproyectos.service;

import com.misproyectos.exceptions.ValidacionException;
import com.misproyectos.interfaces.PeriodicidadesRepInterface;
import com.misproyectos.models.Periodicidad;
import com.misproyectos.repositories.PeriodicidadesRepository;
import com.misproyectos.service.validations.PeriodicidadValidationService;

import java.sql.SQLException;

public class PeriodicidadService {
    private final PeriodicidadesRepository repository;

    public PeriodicidadService(PeriodicidadesRepository repository) {
        this.repository = repository;
    }

    public boolean registrarPeriodicidad(Periodicidad periodicidad) throws ValidacionException, SQLException {
        //validar
        if (periodicidad == null) {
            throw new ValidacionException("El objeto no puede ser null");
        }
        PeriodicidadValidationService
                .validateNamePeriodicidad(periodicidad.getNombrePeriodicidad());
        PeriodicidadValidationService
                .validateDaysPeriodicidad(periodicidad.getDiasPeriodicidad());
        PeriodicidadValidationService
                .validateInterestPercent(periodicidad.getPorcentajeIntereses());

        if (repository.existePeriodicidadByName(periodicidad.getNombrePeriodicidad())) {
            throw new SQLException("Periodicidad ya existente");
        }

        return repository.add(periodicidad);
    }
}
