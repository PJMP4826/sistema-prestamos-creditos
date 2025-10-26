package com.misproyectos.interfaces;

import com.misproyectos.models.CorreoCliente;

import java.sql.SQLException;
import java.util.List;

public interface CorreoRepInterface {
    boolean add(CorreoCliente correoCliente) throws SQLException;

    boolean update(CorreoCliente correoCliente) throws SQLException;

    boolean delete(int idTelefono) throws SQLException;

    CorreoCliente findById(int idCorreo) throws SQLException;

    CorreoCliente findByClientId(Long idCliente) throws SQLException;
}
