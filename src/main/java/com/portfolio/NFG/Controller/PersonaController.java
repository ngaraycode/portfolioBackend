package com.portfolio.NFG.Controller;

import com.portfolio.NFG.Dto.dtoPersona;
import com.portfolio.NFG.Entity.Persona;

import com.portfolio.NFG.Security.Controller.Mensaje;
import com.portfolio.NFG.Service.PersonaService;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://pnfg-91647.web.app")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = personaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") Long id) {
        if (!personaService.existById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Persona experiencia = personaService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }

    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtopersona){
        if(StringUtils.isBlank(dtopersona.getNombre()))
            return new ResponseEntity(new Mensaje ("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(personaService.existByNombre(dtopersona.getNombre()))
            return new ResponseEntity(new Mensaje("Ya existe ese nombre"), HttpStatus.BAD_REQUEST);
        
        Persona persona = new Persona(dtopersona.getNombre(),dtopersona.getApellido(),dtopersona.getCargo(),dtopersona.getDescripcion(),dtopersona.getFotoPerfil());
        personaService.save(persona);
        return new ResponseEntity(new Mensaje("Persona agregada"), HttpStatus.OK);
        
    }
     

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody dtoPersona dtopersona) {
        if (!personaService.existById(id)) {
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        }
        if (personaService.existByNombre(dtopersona.getNombre()) && personaService.getByNombre(dtopersona.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtopersona.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = personaService.getOne(id).get();
        persona.setNombre(dtopersona.getNombre());
        persona.setApellido(dtopersona.getApellido());
        persona.setCargo(dtopersona.getCargo());
        persona.setDescripcion(dtopersona.getDescripcion());
        persona.setFotoPerfil(dtopersona.getFotoPerfil());

        personaService.save(persona);
        return new ResponseEntity(new Mensaje("Persona actualizada"), HttpStatus.OK);
    }

    /*
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!personaService.existById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        personaService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
     */
}
