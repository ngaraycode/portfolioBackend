package com.portfolio.NFG.Controller;

import com.portfolio.NFG.Dto.dtoProyecto;
import com.portfolio.NFG.Entity.Proyecto;
import com.portfolio.NFG.Security.Controller.Mensaje;
import com.portfolio.NFG.Service.SProyecto;
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
@RequestMapping("proye")
//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "https://pnfg-91647.web.app")
@CrossOrigin(origins = {"https://pnfg-91647.web.app","http://localhost:4200"})
public class CProyecto {
    @Autowired
    SProyecto sProyecto;
    
    @GetMapping("/lista")
    public ResponseEntity <List<Proyecto>> list(){
        List<Proyecto> list = sProyecto.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/details/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id){
        if(!sProyecto.existById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Proyecto proyecto = sProyecto.getOne(id).get();
        return new ResponseEntity(proyecto, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyecto dtoproye){
        if(StringUtils.isBlank(dtoproye.getNombreP()))
            return new ResponseEntity(new Mensaje ("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sProyecto.existByNombreP(dtoproye.getNombreP()))
            return new ResponseEntity(new Mensaje("Ya existe ese proyecto"), HttpStatus.BAD_REQUEST);
        
        Proyecto proyecto = new Proyecto(dtoproye.getNombreP(),dtoproye.getFecha(),dtoproye.getDescripcionP(),dtoproye.getLink(),dtoproye.getFotoP());
        sProyecto.save(proyecto);
        return new ResponseEntity(new Mensaje("Proyecto agregado"), HttpStatus.OK);
        
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyecto dtoproye){
        if(!sProyecto.existById(id))
            return new ResponseEntity(new Mensaje("El id no existe"), HttpStatus.BAD_REQUEST);
        if(sProyecto.existByNombreP(dtoproye.getNombreP()) && sProyecto.getByNombreP(dtoproye.getNombreP()).get().getId() != id)
            return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(dtoproye.getNombreP()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Proyecto proyecto = sProyecto.getOne(id).get();
        proyecto.setNombreP(dtoproye.getNombreP());
        proyecto.setFecha(dtoproye.getFecha());
        proyecto.setLink(dtoproye.getLink());
        proyecto.setDescripcionP(dtoproye.getDescripcionP());
        proyecto.setFotoP(dtoproye.getFotoP());
        
        sProyecto.save(proyecto);
        return new ResponseEntity(new Mensaje("Proyecto actualizado"), HttpStatus.OK);
    }
    
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sProyecto.existById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sProyecto.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
    
}
