package com.projetotcs.tcsbackend.controller;

import com.projetotcs.tcsbackend.model.DisciplinaModel;
import com.projetotcs.tcsbackend.services.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="/api/disciplina")
public class DisciplinaController {

    @Autowired
    DisciplinaService service;

    @GetMapping(value="/get-disciplinas")
    public List<DisciplinaModel> getDisciplinas() {
        return service.findAll();
    }

    @GetMapping(value="/get-disciplina/{id}")
    public DisciplinaModel getDisciplinaById(@PathVariable(value="id") Long id) {
        return service.findById(id);
    }

    @PostMapping(value="/create-disciplina")
    public ResponseEntity<DisciplinaModel> createDisciplina(@RequestBody DisciplinaModel disciplina) {
        return new ResponseEntity<>(service.create(disciplina), HttpStatus.CREATED);
    }

    @PutMapping(value="/update-disciplina/{id}")
    public DisciplinaModel updateDisciplina(@RequestBody DisciplinaModel disciplina, @PathVariable(value="id") Long id) {
        return service.update(disciplina, id);
    }


    @DeleteMapping(value="/delete-disciplina/{id}")
    public ResponseEntity<DisciplinaModel> deleteDisciplina(@PathVariable(value="id") Long id) {

         service.delete(id);

         return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
