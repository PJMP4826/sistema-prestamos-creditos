package com.misproyectos.interfaces;

import com.misproyectos.models.Cliente;

import java.sql.SQLException;

public interface ClienteRepInterface {
    Long add(Cliente cliente) throws SQLException;
    boolean existeClientByRfc(String rfc) throws SQLException;
}
