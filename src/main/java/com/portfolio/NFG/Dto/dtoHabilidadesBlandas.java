package com.portfolio.NFG.Dto;


public class dtoHabilidadesBlandas {
    private int id;
    private String nombre;
    private int porcentaje;
    private String urlImg;

    public dtoHabilidadesBlandas() {
    }

    public dtoHabilidadesBlandas(String nombre, int porcentaje, String urlImg) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.urlImg = urlImg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }
    
    
}
