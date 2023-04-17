
package com.portfolio.NFG.Dto;

import jakarta.validation.constraints.NotBlank;


public class dtoEducacion {
    @NotBlank
    private String nombreE;
    @NotBlank
    private String fechaI;
    @NotBlank
    private String fechaF;
    @NotBlank
    private String descripcionE;
    @NotBlank
    private String fotoE;

    public dtoEducacion() {
    }

    public dtoEducacion(String nombreE, String fechaI, String fechaF, String descripcionE, String fotoE) {
        this.nombreE = nombreE;
        this.fechaI = fechaI;
        this.fechaF = fechaF;
        this.descripcionE = descripcionE;
        this.fotoE = fotoE;
    }

    public String getNombreE() {
        return nombreE;
    }

    public void setNombreE(String nombreE) {
        this.nombreE = nombreE;
    }

    public String getFechaI() {
        return fechaI;
    }

    public void setFechaI(String fechaI) {
        this.fechaI = fechaI;
    }

    public String getFechaF() {
        return fechaF;
    }

    public void setFechaF(String fechaF) {
        this.fechaF = fechaF;
    }

    public String getDescripcionE() {
        return descripcionE;
    }

    public void setDescripcionE(String descripcionE) {
        this.descripcionE = descripcionE;
    }

    public String getFotoE() {
        return fotoE;
    }

    public void setFotoE(String fotoE) {
        this.fotoE = fotoE;
    }
    
    
    
}
