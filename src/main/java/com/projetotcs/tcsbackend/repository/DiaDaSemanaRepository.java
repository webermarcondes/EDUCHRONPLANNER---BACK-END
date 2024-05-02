package com.projetotcs.tcsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetotcs.tcsbackend.model.DiaDaSemana;

//import com.projetotcs.tcsbackend.enums.DiaDaSemana;

public interface DiaDaSemanaRepository  extends JpaRepository<DiaDaSemana, Long>{
    
    DiaDaSemana findByDescricao(String descricao);
}
