package com.misproyectos.enums;

public enum EstadoPrestamo {
    ACTIVO,
    COMPLETADO,
    INCUMPLIDO;

    public boolean isFinalizado() {
        return this == COMPLETADO || this == INCUMPLIDO;
    }
}
