package com.projetotcs.tcsbackend.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;


import com.projetotcs.tcsbackend.model.Professor;
import com.projetotcs.tcsbackend.repository.ProfessorRepository;

@Service
public class ProfessorService {
 
    @Autowired
    ProfessorRepository repository;

    public List<Professor> findAll() {

        List<Professor> professores = repository.findAll();

        if(professores.isEmpty()) {
            throw new ResourceNotFoundException("Não há professores cadastrados");
        }

        return professores;
    }

    public Professor findById(Long id) {

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não há registro de professor com o ID informado"));
    }

    public Professor create(Professor professor) {

        repository.save(professor);

        return professor;

    }

    public Professor update(Professor professor, Long id) {

        var entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Não há registro de professor com o ID informado para atualizar informações"));

        entity.setId(id);
        entity.setNomeCompleto(professor.getNomeCompleto());
        entity.setTelefone(professor.getTelefone());
        entity.setQtdeDiasDeAula(professor.getQtdeDiasDeAula());
        entity.setStatus(professor.getStatus());

        repository.save(entity);

        return professor;

    }

    /* 
    public void delete(Long id) {

        repository.deleteById(id);
    }*/

}
