package com.projetotcs.tcsbackend.controller;


/*
@RestController
@RequestMapping("/api/professor")
 */

import com.projetotcs.tcsbackend.model.AgendaProfessor;
import com.projetotcs.tcsbackend.model.Disciplina;
import com.projetotcs.tcsbackend.services.AgendaProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*

Opçao: Serializar apenas ID em alguns pontos;


Fazer validação de quantidade de aulas antes de atualizar ou vincular;

Atender ao RF 41: O sistema irá alertar se uma disciplina não tiver professor definido -> Atendido;

Mesma coisa
 {
Verificar RF 38:  O sistema irá alertar o usuário caso ele coloque uma sala com duas disciplinas em um mesmo dia

Verificar RN 05: O sistema não irá permitir que uma sala tenha duas disciplinas no mesmo dia
}

Verificar RN 06: O sistema não irá permitir que dois professores tenha aula com a mesma disciplina em um dia


- Finalizar Validação de Sala e dia iguais -> RF38 e RN05;

- Fazer validação de Disciplinas, dia igual e professores diferentes -> RN06;


 */

@RestController
@RequestMapping("/api/agendaprofessor")
public class AgendaProfessorController {

    @Autowired
    AgendaProfessorService service;

    @GetMapping(value="/")
    public List<AgendaProfessor> getAgendaProfessores(){
        return service.findAll();
    }

    @GetMapping(value="/{id}")
    public AgendaProfessor getAgendaProfessorById(@PathVariable(value="id") Long id) {
        return service.findById(id);
    }

    @GetMapping(value="/buscaporprofessor/{professor_id}")
    public List<AgendaProfessor> getAgendaProfessorByProfessorId(@PathVariable(value="professor_id") Long professorId) {
        return service.findByProfessorId(professorId);
    }

    @GetMapping(value="/buscapordisciplina/{disciplina_id}")
    public List<AgendaProfessor> getAgendaProfessorByDisciplinaId(@PathVariable(value="disciplina_id") Long disciplinaId) {
        return service.findByDisciplinaId(disciplinaId);
    }

    @GetMapping(value="/buscapordiadasemana/{dia_da_semana_id}")
    public List<AgendaProfessor> getAgendaProfessorByDiaDaSemanaId(@PathVariable(value="dia_da_semana_id") Long diaDaSemanaId) {

        return service.findByDiaDaSemanaId(diaDaSemanaId);
    }

    @GetMapping(value="/buscaporsala/{numero_sala}")
    public List<AgendaProfessor> getAgendaProfessorBySalaNumber(@PathVariable(value="numero_sala") Integer numeroSala) {
        return service.findByNumeroSala(numeroSala);
    }


    @GetMapping(value="/buscapordiasemprofessor/")
    public List<AgendaProfessor> getAgendaProfessorWhereProfessorIsNull() {
        return service.findByProfessorIsNull();
    }



    @PostMapping(value="/")
    public ResponseEntity<AgendaProfessor> createAgendaProfessor(@RequestBody AgendaProfessor agendaProfessor) {

        return new ResponseEntity<>(service.create(agendaProfessor), HttpStatus.CREATED);
    }


    @PutMapping(value="/{id}")
    public AgendaProfessor updateAgendaProfessor(@RequestBody AgendaProfessor agendaProfessor, @PathVariable(value="id") Long id) {
        return service.update(agendaProfessor, id);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<AgendaProfessor> deleteAgendaProfessor(@PathVariable(value="id") Long id) {

        service.delete(id);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }











}
