package com.misproyectos.interfaces;

import com.misproyectos.models.Periodicidad;

import java.sql.SQLException;
import java.util.List;

public abstract class PeriodicidadesRepInterface {
    protected abstract boolean add(Periodicidad periodicidad) throws SQLException;

    protected abstract boolean delete(Long idPeriodicidad) throws SQLException;

    protected abstract List<Periodicidad> findAll() throws SQLException;

    protected abstract boolean existePeriodicidadByName(String periodicidad) throws SQLException;
    //nota: queda pendiente checar si se usara findById
}
