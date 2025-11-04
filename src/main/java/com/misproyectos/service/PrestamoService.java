package com.misproyectos.service;

import com.misproyectos.models.Prestamo;


public class PrestamoService {
    public PrestamoService() {

    }

    public double calcularMontoPorCuota(
            double importe,
            double tasaInteresAnual,
            double plazoDias
    ) {
        double tasaAnualDecimal = tasaInteresAnual / 100;
        double tasaDiaria = tasaAnualDecimal / 365;

        double interesTotal = importe * tasaDiaria * plazoDias;

        double montoTotal = importe + interesTotal;
        double cuotaDiaria = montoTotal / plazoDias;

        return Math.round(cuotaDiaria * 100.0) / 100.0;
    }

    public double calcularTotalToPagar(double montoCuota, double plazo) {
        double total = montoCuota * plazo;
        return Math.round(total * 100.0) / 100.0;
    }

    public double calcularInteresTotal(double totalPagar, double importe) {
        double interesTotal = totalPagar - importe;
        return Math.round(interesTotal * 100.0) / 100.0;
    }

}
