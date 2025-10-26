package com.misproyectos.models;

public class Periodicidad {
    private Long idPeriodicidad;
    private String nombrePeriodicidad;
    private int diasPeriodicidad;
    private int porcentajeIntereses;

    public Periodicidad(){

    }

    public Long getIdPeriodicidad() {
        return idPeriodicidad;
    }

    public void setIdPeriodicidad(Long idPeriodicidad) {
        this.idPeriodicidad = idPeriodicidad;
    }

    public String getNombrePeriodicidad() {
        return nombrePeriodicidad;
    }

    public void setNombrePeriodicidad(String nombrePeriodicidad) {
        this.nombrePeriodicidad = nombrePeriodicidad;
    }

    public int getDiasPeriodicidad() {
        return diasPeriodicidad;
    }

    public void setDiasPeriodicidad(int diasPeriodicidad) {
        this.diasPeriodicidad = diasPeriodicidad;
    }

    public int getPorcentajeIntereses() {
        return porcentajeIntereses;
    }

    public void setPorcentajeIntereses(int porcentajeIntereses) {
        this.porcentajeIntereses = porcentajeIntereses;
    }
}
