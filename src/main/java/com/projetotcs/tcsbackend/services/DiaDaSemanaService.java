package com.projetotcs.tcsbackend.services;

import com.projetotcs.tcsbackend.repository.DiaDaSemanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetotcs.tcsbackend.model.DiaDaSemana;

@Service
public class DiaDaSemanaService {

    @Autowired
    DiaDaSemanaRepository repository;

    public DiaDaSemana findByDescricao(String descricao) {
        return repository.findByDescricao(descricao);
    }
    
}
