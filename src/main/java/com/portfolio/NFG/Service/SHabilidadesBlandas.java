package com.portfolio.NFG.Service;

import com.portfolio.NFG.Entity.HabilidadesBlandas;
import com.portfolio.NFG.Repository.RHabilidadesBlandas;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SHabilidadesBlandas {
    @Autowired
    RHabilidadesBlandas rHabilidadesBlandas;
    
    public List<HabilidadesBlandas> list(){
        return rHabilidadesBlandas.findAll();
    }
    
    public Optional<HabilidadesBlandas> getOne(int id){
        return rHabilidadesBlandas.findById(id);
    }
    
    public Optional<HabilidadesBlandas> getByNombreE(String nombre){
        return rHabilidadesBlandas.findByNombre(nombre);
    }
    
    public void save(HabilidadesBlandas habilidadesBlandas){
        rHabilidadesBlandas.save(habilidadesBlandas);
    }
    
    public void delete(int id){
        rHabilidadesBlandas.deleteById(id);
    }
    
    public boolean existById(int id){
        return rHabilidadesBlandas.existsById(id);
    }
    
    public boolean existByNombre(String nombre){
        return rHabilidadesBlandas.existsByNombre(nombre);
    }
}
