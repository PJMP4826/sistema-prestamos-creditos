package com.misproyectos.interfaces;

import com.misproyectos.models.Cliente;

import java.sql.SQLException;

public interface ClienteRepInterface {
    int add(Cliente cliente) throws SQLException;
}
