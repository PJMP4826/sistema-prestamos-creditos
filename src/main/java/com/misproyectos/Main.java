package com.misproyectos;

import com.misproyectos.config.Database;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
//        Prestamo prestamo = Prestamo.builder()
//                .setIdPrestamo(123)
//                .build();
//
//        System.out.println("Prestamo: " + prestamo.toString());
//        System.out.println("Prestamo id: " + prestamo.getIdPrestamo());

        try {
            Connection db = Database.getInstance().getConnection();
            System.out.println("Conexion exitosa: " + db);

        } catch (Exception e) {
            System.out.println("Conexion faild: ");
            e.printStackTrace();

        }

    }
}