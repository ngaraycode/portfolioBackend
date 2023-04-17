package com.portfolio.NFG.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public class dtoPersona {
        
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;
    @NotBlank
    private String cargo;
    @NotBlank
    @Size(min=1,max=2000, message="Longitud incorrecta")
    private String descripcion;
    @NotBlank
    private String fotoPerfil;

    public dtoPersona() {
    }

    public dtoPersona(String nombre, String apellido, String cargo, String descripcion, String fotoPerfil) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.descripcion = descripcion;
        this.fotoPerfil = fotoPerfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

   
    
    
}
