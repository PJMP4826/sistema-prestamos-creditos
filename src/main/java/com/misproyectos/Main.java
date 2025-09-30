package com.misproyectos;

import com.misproyectos.config.Database;
import com.misproyectos.config.PostgresStrategy;
import com.misproyectos.config.SQLiteStrategy;
import com.misproyectos.enums.DbStrategy;
import com.misproyectos.models.Prestamo;

import java.sql.Connection;

import com.misproyectos.config.SQLite;


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