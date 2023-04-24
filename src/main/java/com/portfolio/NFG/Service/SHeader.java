package com.portfolio.NFG.Service;

import com.portfolio.NFG.Entity.Header;
import com.portfolio.NFG.Repository.RHeader;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SHeader {
    @Autowired
    RHeader rHeader; 
    
    public List<Header> list(){
        return rHeader.findAll();
    }
    
    public Optional<Header> getOne(int id){
        return rHeader.findById(id);
    }
    
    public Optional<Header> getByNombre(String nombre){
        return rHeader.findByNombre(nombre);
    }
    
    public void save(Header header){
        rHeader.save(header);
    }
    
    public void delete(int id){
        rHeader.deleteById(id);
    }
    
    public boolean existById(int id){
        return rHeader.existsById(id);
    }
    
    public boolean existByNombre(String nombre){
        return rHeader.existsByNombre(nombre);
    }
    
    
}
