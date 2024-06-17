package com.projetotcs.tcsbackend.controller;

import com.projetotcs.tcsbackend.model.CursoModel;
import com.projetotcs.tcsbackend.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//Testar o método de busca por nome
@RestController
@RequestMapping("/api/curso")
public class CursoController {

    @Autowired
    CursoService service;

    @GetMapping(value="/")
    public List<CursoModel> getCursos() {
        return service.findAll();
    }

    @GetMapping(value="/{id}")
    public CursoModel getCursoById(@PathVariable(value="id") Long id) {
        return service.findById(id);
    }

    @PostMapping(value="/")
    public ResponseEntity<CursoModel> createCurso(@RequestBody CursoModel curso) {
        return new ResponseEntity<>(service.create(curso), HttpStatus.CREATED);
    }

    @PutMapping(value="/{id}")
    public CursoModel updateCurso(@RequestBody CursoModel curso, @PathVariable(value="id") Long id) {
        return service.update(curso, id);
    }


    @DeleteMapping(value="/{id}")
    public ResponseEntity<CursoModel> deleteCurso(@PathVariable(value="id") Long id){
        service.delete(id);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
