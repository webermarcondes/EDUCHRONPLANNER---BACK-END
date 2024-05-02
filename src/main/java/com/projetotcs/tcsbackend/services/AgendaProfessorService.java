package com.projetotcs.tcsbackend.services;


import com.projetotcs.tcsbackend.model.AgendaProfessor;
import com.projetotcs.tcsbackend.repository.AgendaProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaProfessorService {


    @Autowired
    AgendaProfessorRepository repository;

    //@Autowired
    //@Lazy
    //ProfessorService professorService;


    public List<AgendaProfessor> findAll(){
        return repository.findAll();
    }

    public AgendaProfessor findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro na agenda de professores foi encontrado com o ID informado"));
    }

    public List<AgendaProfessor> findByProfessorId(Long professorId) {

        return repository.findByProfessorId(professorId);
    }

    public List<AgendaProfessor> findByDisciplinaId(Long disciplinaId) {
        return repository.findByDisciplinaId(disciplinaId);
    }

    public List<AgendaProfessor> findByDiaDaSemanaId(Long diaDaSemanaId) {
        return repository.findByDiaDaSemanaId(diaDaSemanaId);
    }



    public AgendaProfessor create(AgendaProfessor agendaProfessor) {


        /*if(agendaProfessor.getProfessor().getId() == null) {
            Professor professor = professorService.create(agendaProfessor.getProfessor());

            agendaProfessor.setProfessor(professor);
        }*/
        return repository.save(agendaProfessor);
    }

    public AgendaProfessor update(AgendaProfessor agendaProfessor, Long id) {

        //professorService.update(agendaProfessor.getProfessor(), agendaProfessor.getProfessor().getId());

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro na agenda de professores foi encontrado com o ID informado"));

        entity.setProfessor(agendaProfessor.getProfessor());
        entity.setDiaDaSemana(agendaProfessor.getDiaDaSemana());
        entity.setDisciplina(agendaProfessor.getDisciplina());

        repository.save(entity);

        return agendaProfessor;
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }



}
