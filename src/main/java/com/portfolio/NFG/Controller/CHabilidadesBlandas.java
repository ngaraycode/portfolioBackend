package com.portfolio.NFG.Controller;

import com.portfolio.NFG.Dto.dtoHabilidadesBlandas;
import com.portfolio.NFG.Entity.HabilidadesBlandas;
import com.portfolio.NFG.Security.Controller.Mensaje;
import com.portfolio.NFG.Service.SHabilidadesBlandas;
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
@RequestMapping("/habilidadesblandas")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://pnfg-91647.web.app")
//@CrossOrigin(origins = {"https://pnfg-91647.web.app","http://localhost:4200"})
public class CHabilidadesBlandas {
    @Autowired
    SHabilidadesBlandas sHabilidadesBlandas;
    
    @GetMapping("/lista")
    public ResponseEntity <List<HabilidadesBlandas>> list(){
        List<HabilidadesBlandas> list = sHabilidadesBlandas.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/details/{id}")
    public ResponseEntity<HabilidadesBlandas> getById(@PathVariable("id") int id){
        if(!sHabilidadesBlandas.existById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        HabilidadesBlandas habilidadesBlandas = sHabilidadesBlandas.getOne(id).get();
        return new ResponseEntity(habilidadesBlandas, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHabilidadesBlandas dtohabilidadesblandas){
        if(StringUtils.isBlank(dtohabilidadesblandas.getNombre()))
            return new ResponseEntity(new Mensaje ("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sHabilidadesBlandas.existByNombre(dtohabilidadesblandas.getNombre()))
            return new ResponseEntity(new Mensaje("Ya existe esa habilidad"), HttpStatus.BAD_REQUEST);
        
        HabilidadesBlandas habilidadesBlandas = new HabilidadesBlandas(dtohabilidadesblandas.getNombre(),dtohabilidadesblandas.getPorcentaje(),dtohabilidadesblandas.getUrlImg());
        sHabilidadesBlandas.save(habilidadesBlandas);
        return new ResponseEntity(new Mensaje("Habilidad agregada"), HttpStatus.OK);   
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHabilidadesBlandas dtohabilidadesblandas){
        if(!sHabilidadesBlandas.existById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        if(sHabilidadesBlandas.existByNombre(dtohabilidadesblandas.getNombre()) && sHabilidadesBlandas.getByNombreE(dtohabilidadesblandas.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtohabilidadesblandas.getNombre()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        HabilidadesBlandas habilidadesBlandas = sHabilidadesBlandas.getOne(id).get();
        habilidadesBlandas.setNombre(dtohabilidadesblandas.getNombre());
        habilidadesBlandas.setPorcentaje(dtohabilidadesblandas.getPorcentaje());
        habilidadesBlandas.setUrlImg(dtohabilidadesblandas.getUrlImg());
        
        sHabilidadesBlandas.save(habilidadesBlandas);
        return new ResponseEntity(new Mensaje("Habilidad actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sHabilidadesBlandas.existById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sHabilidadesBlandas.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
    
}
