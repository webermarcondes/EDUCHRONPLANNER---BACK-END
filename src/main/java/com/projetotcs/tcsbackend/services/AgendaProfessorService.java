package com.projetotcs.tcsbackend.services;


import com.projetotcs.tcsbackend.model.AgendaProfessor;
import com.projetotcs.tcsbackend.model.DiaDaSemana;
import com.projetotcs.tcsbackend.model.Disciplina;
import com.projetotcs.tcsbackend.model.Sala;
import com.projetotcs.tcsbackend.repository.AgendaProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaProfessorService {


    @Autowired
    AgendaProfessorRepository repository;

    @Autowired
    SalaService salaService;

    @Autowired
    DiaDaSemanaService diaDaSemanaService;


    public List<AgendaProfessor> findAll(){

        List<AgendaProfessor> agendaProfessores =  repository.findAll();

        if (agendaProfessores.isEmpty()) {
            throw new ResourceNotFoundException("A agenda de professores está vazia.");
        }

        return agendaProfessores;
    }

    public AgendaProfessor findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de agenda professor com o ID informado"));
    }

    public List<AgendaProfessor> findByProfessorId(Long professorId) {

        List<AgendaProfessor> agendaProfessores = repository.findByProfessorId(professorId);

        if(agendaProfessores.isEmpty()) {
            throw new ResourceNotFoundException("Não há registros de agenda professor com o ID do professor informado");
        }

        return agendaProfessores;
    }

    public List<AgendaProfessor> findByDisciplinaId(Long disciplinaId) {
        List<AgendaProfessor> agendaProfessores = repository.findByDisciplinaId(disciplinaId);

        if(agendaProfessores.isEmpty()) {
            throw new ResourceNotFoundException("Não há registros de agenda professor com o ID da disciplina informado");
        }

        return agendaProfessores;
    }

    public List<AgendaProfessor> findByDiaDaSemanaId(Long diaDaSemanaId) {

        List<AgendaProfessor> agendaProfessores = repository.findByDiaDaSemanaId(diaDaSemanaId);

        if(agendaProfessores.isEmpty()) {
            throw new ResourceNotFoundException("Não há registros de agenda professor com o ID do dia da semana informado");
        }

        return agendaProfessores;
    }


    public List<AgendaProfessor> findByNumeroSala(Integer numeroSala) {

        Long salaId = salaService.findByNumero(numeroSala).getId();

        List<AgendaProfessor> agendaProfessores =  repository.findBySalaId(salaId);

        if(agendaProfessores.isEmpty()) {
            throw new ResourceNotFoundException("Não há registro de agenda professor com o número de sala informado");
        }

        return agendaProfessores;


    }

    public List<AgendaProfessor> findByProfessorIsNull() {
        List<AgendaProfessor> agendaProfessores = repository.findByProfessorIsNull();

        if (agendaProfessores.isEmpty()) {
            throw new ResourceNotFoundException("Não há registros de agenda professor sem professor definido");
        }

        return agendaProfessores;
    }



    public AgendaProfessor create(AgendaProfessor agendaProfessor) {

        return repository.save(agendaProfessor);
    }

    public AgendaProfessor update(AgendaProfessor agendaProfessor, Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de agenda professor com o ID informado para atualizar informações"));

        entity.setProfessor(agendaProfessor.getProfessor());
        entity.setDiaDaSemana(agendaProfessor.getDiaDaSemana());
        entity.setDisciplina(agendaProfessor.getDisciplina());
        entity.setSala(agendaProfessor.getSala());

        repository.save(entity);

        return agendaProfessor;
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }



}
