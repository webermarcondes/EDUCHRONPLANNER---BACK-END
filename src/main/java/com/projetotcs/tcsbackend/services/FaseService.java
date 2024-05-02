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
        return repository.findAll();
    }


    public Fase findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma fase foi encontrado com o ID informado"));

    }

    public Fase findByNumero(Integer numeroFase) {
        return repository.findByNumero(numeroFase);
    }

    public Fase create(Fase fase) {

        return repository.save(fase);
    }


    public Fase update(Fase fase, Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma fase foi encontrado com o ID informado"));


        entity.setNumero(fase.getNumero());

        repository.save(entity);

        return fase;
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }
}
