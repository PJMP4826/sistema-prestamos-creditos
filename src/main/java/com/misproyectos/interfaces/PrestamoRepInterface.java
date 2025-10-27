package com.misproyectos.interfaces;

import com.misproyectos.models.Prestamo;

import java.sql.SQLException;
import java.util.List;

public abstract class PrestamoRepInterface {
    abstract boolean add(Prestamo prestamo) throws SQLException;
    abstract boolean update(Prestamo prestamo) throws SQLException;
    abstract boolean delete(int idPrestamos) throws SQLException;
    abstract List<Prestamo> findAll() throws SQLException;
}
