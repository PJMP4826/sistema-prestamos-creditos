package com.misproyectos.interfaces;

import com.misproyectos.models.Prestamo;

import java.sql.SQLException;
import java.util.List;

public abstract class PrestamoRepInterface {
    protected abstract boolean add(Prestamo prestamo) throws SQLException;
    protected abstract boolean update(Prestamo prestamo) throws SQLException;
    protected abstract boolean delete(Long idPrestamo) throws SQLException;
    protected abstract Prestamo findById(Long idPrestamo) throws SQLException;
    protected abstract List<Prestamo> findAll() throws SQLException;
}
