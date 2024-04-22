package com.projetotcs.tcsbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.projetotcs.tcsbackend.enums.DiaDaSemana;
import com.projetotcs.tcsbackend.model.DiaDisponivelNaSemana;
import com.projetotcs.tcsbackend.repository.DiaDisponivelNaSemanaRepository;

@Service
public class DiaDisponivelNaSemanaService {

    @Autowired
    DiaDisponivelNaSemanaRepository repository;

    public DiaDisponivelNaSemana findByDiaDaSemana(String diaDaSemana) {
        return repository.findByDiaDaSemana(diaDaSemana);
    }
    
}
