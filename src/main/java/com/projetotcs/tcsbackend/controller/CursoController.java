package com.projetotcs.tcsbackend.controller;

import com.projetotcs.tcsbackend.model.Curso;
import com.projetotcs.tcsbackend.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/curso")
public class CursoController {

    @Autowired
    CursoService service;

    @GetMapping(value="/")
    public List<Curso> getCursos() {
        return service.findAll();
    }

    @GetMapping(value="/{id}")
    public Curso getCursoById(@PathVariable(value="id") Long id) {
        return service.findById(id);
    }

    @PostMapping(value="/")
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
        return new ResponseEntity<>(service.create(curso), HttpStatus.CREATED);
    }

    @PutMapping(value="/{id}")
    public Curso updateCurso(@RequestBody Curso curso, @PathVariable(value="id") Long id) {
        return service.update(curso, id);
    }


    @DeleteMapping(value="/{id}")
    public ResponseEntity<Curso> deleteCurso(@PathVariable(value="id") Long id){
        service.delete(id);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
