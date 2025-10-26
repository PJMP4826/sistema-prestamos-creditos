package com.misproyectos.test;

import com.misproyectos.models.CorreoCliente;
import com.misproyectos.repositories.CorreoRepository;

import java.sql.SQLException;

public class TestCorreoById {
    public static void main(String[] args) {
        try {
            CorreoRepository repCorreo = new CorreoRepository();

            CorreoCliente correo = repCorreo.findById(1);

            System.out.println(correo.toString());

        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
    }
}

