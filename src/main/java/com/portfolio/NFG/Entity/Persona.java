
package com.portfolio.NFG.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
public class Persona {
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min=1,max=50, message="Longitud incorrecta")
    private String nombre;
    @NotNull
    @Size(min=1,max=50, message="Longitud incorrecta")
    private String apellido;
    @NotNull
    @Size(min=1,max=100, message="Longitud incorrecta")
    private String cargo;
    @NotNull
    @Size(min=1,max=2000, message="Longitud incorrecta")
    private String descripcion;
    private String fotoPerfil;
    
}
