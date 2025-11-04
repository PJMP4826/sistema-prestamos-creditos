package com.misproyectos.models;

public class PrestamoComboItem {
    private final Long id;
    private final String text;

    public PrestamoComboItem(Long id, String text){
        this.id = id;
        this.text = text;
    }

    public Long getId(){
        return id;
    }

    public String toString(){
        return text;
    }
}
