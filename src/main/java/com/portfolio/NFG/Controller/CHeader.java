package com.portfolio.NFG.Controller;

import com.portfolio.NFG.Dto.dtoHeader;
import com.portfolio.NFG.Entity.Header;
import com.portfolio.NFG.Security.Controller.Mensaje;
import com.portfolio.NFG.Security.Controller.Mensaje2;
import com.portfolio.NFG.Service.SHeader;
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
@RequestMapping("/header")
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(origins = "https://pnfg-91647.web.app")
//@CrossOrigin(origins = {"https://pnfg-91647.web.app","http://localhost:4200"})
public class CHeader {
    @Autowired
    SHeader sHeader;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Header>> list() {
        List<Header> list = sHeader.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/details/{id}")
    public ResponseEntity<Header> getById(@PathVariable("id") int id) {
        if (!sHeader.existById(id)) {
            return new ResponseEntity(new Mensaje2("no existe"), HttpStatus.NOT_FOUND);
        }
        Header header = sHeader.getOne(id).get();
        return new ResponseEntity(header, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHeader dtoheader){
        if(StringUtils.isBlank(dtoheader.getNombre()))
            return new ResponseEntity(new Mensaje2 ("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sHeader.existByNombre(dtoheader.getNombre()))
            return new ResponseEntity(new Mensaje2("Ya existe ese nombre"), HttpStatus.BAD_REQUEST);
        
        Header header = new Header(dtoheader.getNombre());
        sHeader.save(header);
        return new ResponseEntity(new Mensaje2("Persona agregada"), HttpStatus.OK);
        
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHeader dtoheader) {
        if (!sHeader.existById(id)) {
            return new ResponseEntity(new Mensaje2("El id no existe"), HttpStatus.BAD_REQUEST);
        }
        if (sHeader.existByNombre(dtoheader.getNombre()) && sHeader.getByNombre(dtoheader.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje2("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoheader.getNombre())) {
            return new ResponseEntity(new Mensaje2("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Header header = sHeader.getOne(id).get();
        header.setNombre(dtoheader.getNombre());
        

        sHeader.save(header);
        return new ResponseEntity(new Mensaje2("Persona actualizada"), HttpStatus.OK);
    }
    
}
