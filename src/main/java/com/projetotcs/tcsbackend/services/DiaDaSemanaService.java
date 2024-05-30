package com.projetotcs.tcsbackend.services;

import com.projetotcs.tcsbackend.repository.DiaDaSemanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.projetotcs.tcsbackend.model.DiaDaSemanaModel;

import java.util.List;

@Service
public class DiaDaSemanaService {

    @Autowired
    DiaDaSemanaRepository repository;

    public List<DiaDaSemanaModel> findAll() {
        return repository.findAll();
    }


    public DiaDaSemanaModel findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de dia da semana com o ID informado"));

    }

    public DiaDaSemanaModel findByDescricao(String descricao) {

        DiaDaSemanaModel diaDaSemana = repository.findByDescricao(descricao);

        if(diaDaSemana == null) {
            throw new ResourceNotFoundException("Não há registro de dia da semana com a descrição informada");
        }

        return diaDaSemana;
    }

}
