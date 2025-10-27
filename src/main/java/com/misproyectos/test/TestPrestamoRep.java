package com.misproyectos.test;

import com.misproyectos.models.Prestamo;
import com.misproyectos.repositories.PrestamoRepository;

import java.util.List;

public class TestPrestamoRep {
    public static void main(String[] args) {
        try {
            PrestamoRepository prestamoRep = new PrestamoRepository();
            List<Prestamo> prestamos = prestamoRep.findAll();

            for(Prestamo prestamo : prestamos){
                System.out.println(prestamo.toString());
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
