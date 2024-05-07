package com.projetotcs.tcsbackend.services;


import com.projetotcs.tcsbackend.model.Fase;
import com.projetotcs.tcsbackend.repository.FaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaseService {

    @Autowired
    FaseRepository repository;

    public List<Fase> findAll() {
        List<Fase> fases = repository.findAll();

        if (fases.isEmpty()) {
            throw new ResourceNotFoundException("Não há fases cadastradas");
        }

        return fases;
    }


    public Fase findById(Long id) {

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não há registro de fase com o ID informado"));
    }

    public Fase findByNumero(Integer numeroFase) {

        Fase fase = repository.findByNumero(numeroFase);

        if (fase.getId() == null) {
            throw new ResourceNotFoundException("Não há registro de fase com o número informado");
        }

        return fase;
    }

    public Fase create(Fase fase) {

        return repository.save(fase);
    }


    public Fase update(Fase fase, Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de fase com o ID informado para atualizar informações"));


        entity.setNumero(fase.getNumero());
        entity.setCurso(fase.getCurso());

        repository.save(entity);

        return fase;
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }
}
