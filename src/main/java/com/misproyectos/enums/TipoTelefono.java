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

    public static TipoTelefono fromTag(String tag) {
        for (TipoTelefono t : TipoTelefono.values()) {
            if (t.getTag().equalsIgnoreCase(tag)) {
                return t;
            }
        }

        throw new IllegalArgumentException("Tipo de telefono invalido: " + tag);
    }
}
