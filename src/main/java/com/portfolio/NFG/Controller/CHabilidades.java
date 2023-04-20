package com.portfolio.NFG.Controller;

import com.portfolio.NFG.Dto.dtoHabilidades;
import com.portfolio.NFG.Entity.Habilidades;
import com.portfolio.NFG.Security.Controller.Mensaje;
import com.portfolio.NFG.Service.SHabilidades;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/habilidades")
//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "https://pnfg-91647.web.app")
@CrossOrigin(origins = {"https://pnfg-91647.web.app","http://localhost:4200"})
public class CHabilidades {
    @Autowired
    SHabilidades sHabilidades;
    
    @GetMapping("/lista")
    public ResponseEntity <List<Habilidades>> list(){
        List<Habilidades> list = sHabilidades.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/details/{id}")
    public ResponseEntity<Habilidades> getById(@PathVariable("id") int id){
        if(!sHabilidades.existById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Habilidades habilidades = sHabilidades.getOne(id).get();
        return new ResponseEntity(habilidades, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHabilidades dtohabilidades){
        if(StringUtils.isBlank(dtohabilidades.getNombre()))
            return new ResponseEntity(new Mensaje ("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sHabilidades.existByNombre(dtohabilidades.getNombre()))
            return new ResponseEntity(new Mensaje("Ya existe esa habilidad"), HttpStatus.BAD_REQUEST);
        
        Habilidades habilidades = new Habilidades(dtohabilidades.getNombre(),dtohabilidades.getPorcentaje(),dtohabilidades.getUrlImg());
        sHabilidades.save(habilidades);
        return new ResponseEntity(new Mensaje("Habilidad agregada"), HttpStatus.OK);   
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHabilidades dtohabilidades){
        if(!sHabilidades.existById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        if(sHabilidades.existByNombre(dtohabilidades.getNombre()) && sHabilidades.getByNombreE(dtohabilidades.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtohabilidades.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Habilidades habilidades = sHabilidades.getOne(id).get();
        habilidades.setNombre(dtohabilidades.getNombre());
        habilidades.setPorcentaje(dtohabilidades.getPorcentaje());
        habilidades.setUrlImg(dtohabilidades.getUrlImg());
        
        sHabilidades.save(habilidades);
        return new ResponseEntity(new Mensaje("Habilidad actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sHabilidades.existById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sHabilidades.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
    
    
}
