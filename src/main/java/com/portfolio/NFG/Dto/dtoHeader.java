package com.portfolio.NFG.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public class dtoHeader {
    
    @NotBlank
    @Size(min=1,max=2000, message="Longitud incorrecta")
    private String nombre;

    public dtoHeader() {
    }

    public dtoHeader(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    
    
    
    
}
