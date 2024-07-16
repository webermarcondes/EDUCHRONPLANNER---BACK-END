package com.projetotcs.tcsbackend.services;

import com.projetotcs.tcsbackend.model.AgendaProfessorModel;
import com.projetotcs.tcsbackend.model.FaseModel;
import com.projetotcs.tcsbackend.repository.AgendaProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaProfessorService {

    @Autowired
    AgendaProfessorRepository repository;

    public List<AgendaProfessorModel> findAll() {

        List<AgendaProfessorModel> agendaProfessores = repository.findAll();

        if (agendaProfessores.isEmpty()) {
            throw new ResourceNotFoundException("A agenda de professores está vazia.");
        }

        return agendaProfessores;
    }

    public AgendaProfessorModel findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de agenda professor com o ID informado"));
    }


    public AgendaProfessorModel create(AgendaProfessorModel agendaProfessor) {

        return repository.save(agendaProfessor);
    }

    public AgendaProfessorModel update(AgendaProfessorModel agendaProfessor, Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de agenda professor com o ID informado para atualizar informações"));

        entity.setProfessor(agendaProfessor.getProfessor());
        entity.setDiaDaSemana(agendaProfessor.getDiaDaSemana());
        entity.setDisciplina(agendaProfessor.getDisciplina());

        repository.save(entity);

        return entity;
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }


    public List<AgendaProfessorModel> findByDisciplinaFase(FaseModel fase) {

        return repository.findByDisciplinaFase(fase);
    }

    public Long countByDisciplinaId(Long disciplinaId) {
        return repository.countByDisciplinaId(disciplinaId);
    }
}
