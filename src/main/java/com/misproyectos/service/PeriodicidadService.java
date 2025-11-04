package com.misproyectos.service;

import com.misproyectos.exceptions.ValidacionException;
import com.misproyectos.models.Periodicidad;
import com.misproyectos.repositories.PeriodicidadesRepository;
import com.misproyectos.service.validations.PeriodicidadValidationService;

import java.sql.SQLException;
import java.util.List;

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

    public boolean updatePeriodicidad(Periodicidad periodicidad, Long id) throws ValidacionException, SQLException {
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

        return repository.update(periodicidad, id);
    }

    public List<Periodicidad> getPeriodicidades() throws SQLException {
        try {
            List<Periodicidad> periodicidades = repository.findAll();
            return periodicidades;
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public Periodicidad findById(Long id) throws ValidacionException, SQLException{
        try {
            if (id == null){
                throw new ValidacionException("El id no puede ser null");
            }

           Periodicidad periodicidad = repository.findById(id);
            return periodicidad;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
