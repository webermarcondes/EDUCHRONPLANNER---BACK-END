package com.projetotcs.tcsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetotcs.tcsbackend.model.DiaDisponivelNaSemana;

//import com.projetotcs.tcsbackend.enums.DiaDaSemana;

public interface DiaDisponivelNaSemanaRepository  extends JpaRepository<DiaDisponivelNaSemana, Long>{
    
    DiaDisponivelNaSemana findByDiaDaSemana(String diaDaSemana);
}
