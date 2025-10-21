package com.misproyectos.interfaces;

import com.misproyectos.models.TelefonoCliente;

import java.sql.SQLException;
import java.util.List;

public interface TelefonoRepInterface {
    int add(TelefonoCliente telefonoCliente) throws SQLException;

    boolean update(TelefonoCliente telefonoCliente) throws SQLException;

    boolean delete(int idTelefono) throws SQLException;

    TelefonoCliente findById(int idTelefono) throws SQLException;

    List<TelefonoCliente> findByClientId(int idTelefono) throws SQLException;
}
