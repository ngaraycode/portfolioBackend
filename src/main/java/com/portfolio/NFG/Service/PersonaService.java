package com.portfolio.NFG.Service;

import com.portfolio.NFG.Entity.Persona;
import com.portfolio.NFG.Repository.IPersonaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonaService{

    @Autowired
    private IPersonaRepository ipersonaRepository; 
            
    
    public List<Persona> list(){
        return ipersonaRepository.findAll();
    }
    
    public Optional<Persona> getOne(Long id){
        return ipersonaRepository.findById(id);
    }
    
    public Optional<Persona> getByNombre(String nombre){
        return ipersonaRepository.findByNombre(nombre);
    }
    
    public void save(Persona persona){
        ipersonaRepository.save(persona);
    }
    
    public void delete(Long id){
        ipersonaRepository.deleteById(id);
    }
    
    public boolean existById(Long id){
        return ipersonaRepository.existsById(id);
    }
    
    public boolean existByNombre(String nombre){
        return ipersonaRepository.existsByNombre(nombre);
    }
    
}
