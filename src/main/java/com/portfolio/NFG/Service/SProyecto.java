package com.portfolio.NFG.Service;

import com.portfolio.NFG.Entity.Proyecto;
import com.portfolio.NFG.Repository.RProyecto;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class SProyecto {
    @Autowired
    RProyecto rProyecto;
    
    public List<Proyecto> list(){
        return rProyecto.findAll();
    }
    
    public Optional<Proyecto> getOne(int id){
        return rProyecto.findById(id);
    }
    
    public Optional<Proyecto> getByNombreP(String nombreP){
        return rProyecto.findByNombreP(nombreP);
    }
    
    public void save(Proyecto proye){
        rProyecto.save(proye);
    }
    
    public void delete(int id){
        rProyecto.deleteById(id);
    }
    
    public boolean existById(int id){
        return rProyecto.existsById(id);
    }
    
    public boolean existByNombreP(String nombreP){
        return rProyecto.existsByNombreP(nombreP);
    }
    
}
