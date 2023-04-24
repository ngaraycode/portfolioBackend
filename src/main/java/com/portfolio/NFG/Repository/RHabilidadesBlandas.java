
package com.portfolio.NFG.Repository;

import com.portfolio.NFG.Entity.HabilidadesBlandas;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RHabilidadesBlandas extends JpaRepository<HabilidadesBlandas, Integer> {
    public Optional<HabilidadesBlandas> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
    
}
