package com.portfolio.NFG.Dto;

import jakarta.validation.constraints.NotBlank;


public class dtoProyecto {
    @NotBlank
    private String nombreP;
    @NotBlank
    private String fecha;   
    @NotBlank
    private String descripcionP;
    @NotBlank
    private String link;
    @NotBlank
    private String fotoP;

    public dtoProyecto() {
    }

    public dtoProyecto(String nombreP, String fecha, String descripcionP, String link, String fotoP) {
        this.nombreP = nombreP;
        this.fecha = fecha;
        this.descripcionP = descripcionP;
        this.link = link;
        this.fotoP = fotoP;
    }

    public String getNombreP() {
        return nombreP;
    }

    public void setNombreP(String nombreP) {
        this.nombreP = nombreP;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcionP() {
        return descripcionP;
    }

    public void setDescripcionP(String descripcionP) {
        this.descripcionP = descripcionP;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFotoP() {
        return fotoP;
    }

    public void setFotoP(String fotoP) {
        this.fotoP = fotoP;
    }
    
    
    
    
}
