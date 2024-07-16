package com.projetotcs.tcsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetotcs.tcsbackend.model.DiaDaSemanaModel;

public interface DiaDaSemanaRepository  extends JpaRepository<DiaDaSemanaModel, Long>{
    
    DiaDaSemanaModel findByDescricao(String descricao);
}
