package com.misproyectos.test;

import com.misproyectos.models.Periodicidad;
import com.misproyectos.repositories.PeriodicidadesRepository;

public class TestPeriodicidadRep {
    public static void main(String[] args) {
        try {
            Periodicidad periodicidad = new Periodicidad();
            PeriodicidadesRepository repPer = new PeriodicidadesRepository();

            periodicidad.setNombrePeriodicidad("Semanal");
            periodicidad.setDiasPeriodicidad(7);
            periodicidad.setPorcentajeIntereses(24);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
