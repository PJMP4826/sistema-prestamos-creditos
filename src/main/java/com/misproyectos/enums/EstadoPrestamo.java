package com.misproyectos.enums;

public enum EstadoPrestamo {
    ACTIVO("Activo"),
    COMPLETADO("Completado"),
    INCUMPLIDO("Incumplido");

    private final String tag;

    EstadoPrestamo(String tag){
        this.tag = tag;
    }

    public String getTag(){
        return tag;
    }

    public static EstadoPrestamo fromTag(String tag) {
        for (EstadoPrestamo t : EstadoPrestamo.values()) {
            if (t.getTag().equalsIgnoreCase(tag)) {
                return t;
            }
        }

        throw new IllegalArgumentException("Tipo Estado de prestamo invalido: " + tag);
    }

    public boolean isFinalizado() {
        return this == COMPLETADO || this == INCUMPLIDO;
    }
}
