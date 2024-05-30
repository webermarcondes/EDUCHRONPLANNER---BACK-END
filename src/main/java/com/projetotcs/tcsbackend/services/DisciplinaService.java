package com.projetotcs.tcsbackend.services;

import com.projetotcs.tcsbackend.model.DisciplinaModel;
import com.projetotcs.tcsbackend.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository repository;

    public List<DisciplinaModel> findAll() {

        List<DisciplinaModel> disciplinas = repository.findAll();

        if(disciplinas.isEmpty()) {
            throw new ResourceNotFoundException("Não há disciplinas cadastradas");
        }

        return disciplinas;
    }

    public DisciplinaModel findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de disciplina com o ID informado"));
    }

    public DisciplinaModel create(DisciplinaModel disciplina) {
        return repository.save(disciplina);
    }

    public DisciplinaModel update(DisciplinaModel disciplina, Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de disciplina com o ID informado para atualizar informações"));

        entity.setCargaHoraria(disciplina.getCargaHoraria());
        entity.setNome(disciplina.getNome());
        entity.setCodigoCor(disciplina.getCodigoCor());
        entity.setFase(disciplina.getFase());

        repository.save(entity);

        return entity;

    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

}
