package com.misproyectos.service.validations;

import com.misproyectos.exceptions.ValidacionException;

public class PeriodicidadValidationService {
    public static void validateNamePeriodicidad(String periodicidad) throws ValidacionException {
        if (periodicidad == null || periodicidad.isEmpty()) {
            throw new ValidacionException("El nombre no puede estar vacio");
        }
    }

    public static void validateDaysPeriodicidad(int diasPeriodicidad) throws ValidacionException {
        if (diasPeriodicidad <= 0) {
            throw new ValidacionException("Debes especificar los dias de la periodicidad");
        }
    }

    public static void validateInterestPercent(int porcentajeIntereses) throws ValidacionException {
        if(porcentajeIntereses <= 0){
            throw new ValidacionException("Debes especificar el porcentaje de intereses");
        }
    }
}
