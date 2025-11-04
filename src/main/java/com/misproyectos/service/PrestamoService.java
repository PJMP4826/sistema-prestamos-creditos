package com.misproyectos.service;

import com.misproyectos.models.Prestamo;


public class PrestamoService {
    public PrestamoService() {

    }

    public double calcularMontoPorCuota(
            double importe,
            double tasaInteres,
            double plazo
    ) {
        double tasaPeriodica = tasaInteres / 100;
        double factoElevado = Math.pow(1 * tasaPeriodica, plazo);

        double numerador = tasaPeriodica * factoElevado;
        double denominador = factoElevado - 1;

        double factorFormula = numerador / denominador;

        double montoCuota = importe * factorFormula;

        return montoCuota;
    }

    public double calcularTotalToPagar(double montoCuota, double plazo) {
        return montoCuota * plazo;
    }

    public double calcularInteresTotal(double totalPagar, double importe) {
        return totalPagar - importe;
    }

}
