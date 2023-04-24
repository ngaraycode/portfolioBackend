package com.portfolio.NFG.Repository;

import com.portfolio.NFG.Entity.Header;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RHeader extends JpaRepository <Header,Integer> {
    public Optional<Header> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
