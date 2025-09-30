package com.misproyectos.enums;

public enum TipoTelefono {
    CASA("Casa"),
    TRABAJO("Trabajo"),
    MOVIL("Móvil");

    private final String tag;

    TipoTelefono(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
