package com.misproyectos.interfaces;

import com.misproyectos.models.DireccionCliente;

import java.sql.SQLException;
import java.util.List;

public interface DireccionRepInterface {
    boolean add(DireccionCliente direccionCliente) throws SQLException;

    boolean update(DireccionCliente direccionCliente) throws SQLException;

    boolean delete(int idDireccion) throws SQLException;

    DireccionCliente findById(int idDireccion) throws SQLException;

    List<DireccionCliente> findByClientId(int idDireccion) throws SQLException;
}
