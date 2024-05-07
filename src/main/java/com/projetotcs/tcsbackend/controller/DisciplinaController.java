package com.projetotcs.tcsbackend.controller;

import com.projetotcs.tcsbackend.model.Disciplina;
import com.projetotcs.tcsbackend.services.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Definir busca por nome pra disciplina

Verificar se é melhor adicionar o service de fase para busca por fase e o service de sala
para busca por sala no controller de disciplina, ao inves de deixa-los no service de disciplina
 */
@RestController
@RequestMapping(value="/api/disciplina")
public class DisciplinaController {

    @Autowired
    DisciplinaService service;

    @GetMapping(value="/")
    public List<Disciplina> getDisciplinas() {
        return service.findAll();
    }

    @GetMapping(value="/{id}")
    public Disciplina getDisciplinaById(@PathVariable(value="id") Long id) {
        return service.findById(id);
    }


    @GetMapping(value="/buscaporfase/{numero_fase}")
    public List<Disciplina> getDisciplinasByFaseNumber(@PathVariable(value="numero_fase") Integer numeroFase) {
        return service.findByNumeroFase(numeroFase);
    }

    @PostMapping(value="/")
    public ResponseEntity<Disciplina> createDisciplina(@RequestBody Disciplina disciplina) {
        return new ResponseEntity<>(service.create(disciplina), HttpStatus.CREATED);
    }

    @PutMapping(value="/{id}")
    public Disciplina updateDisciplina(@RequestBody Disciplina disciplina, @PathVariable(value="id") Long id) {
        return service.update(disciplina, id);
    }


    @DeleteMapping(value="/{id}")
    public ResponseEntity<Disciplina> deleteDisciplina(@PathVariable(value="id") Long id) {

         service.delete(id);

         return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }




}
