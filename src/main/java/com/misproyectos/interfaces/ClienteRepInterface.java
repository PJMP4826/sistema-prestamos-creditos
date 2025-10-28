package com.misproyectos.interfaces;

import com.misproyectos.models.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteRepInterface {
    Long add(Cliente cliente) throws SQLException;
    boolean existeClientByRfc(String rfc) throws SQLException;
    List<Cliente> getClients() throws SQLException;
}
