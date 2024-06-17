package com.projetotcs.tcsbackend.services;


import com.projetotcs.tcsbackend.model.CursoModel;
import com.projetotcs.tcsbackend.model.FaseModel;
import com.projetotcs.tcsbackend.repository.FaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FaseService {

    @Autowired
    FaseRepository repository;

    public List<FaseModel> findAll() {
        List<FaseModel> fases = repository.findAll();

        if (fases.isEmpty()) {
            throw new ResourceNotFoundException("Não há fases cadastradas");
        }

        return fases;
    }


    public FaseModel findById(Long id) {

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não há registro de fase com o ID informado"));
    }

    public FaseModel findByNumero(Integer numeroFase) {

        FaseModel fase = repository.findByNumero(numeroFase);

        if (fase == null) {
            throw new ResourceNotFoundException("Não há registro de fase com o número informado");
        }

        return fase;
    }

    public List<FaseModel> getFasesByCurso(CursoModel curso) {
        return repository.getFasesByCurso(curso);
    }

    public FaseModel create(FaseModel fase) {

        return repository.save(fase);
    }


    public FaseModel update(FaseModel fase, Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de fase com o ID informado para atualizar informações"));


        entity.setNumero(fase.getNumero());
        entity.setCurso(fase.getCurso());

        repository.save(entity);

        return entity;
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }
}
