package com.projetotcs.tcsbackend.controller;

import com.projetotcs.tcsbackend.model.Fase;
import com.projetotcs.tcsbackend.services.FaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Verificar a viabilidade de definir um getMapping para busca de fase por número. No Service
o método já tá definido, e ele é usado no disciplinaService atualmente
 */
@RestController
@RequestMapping("/api/fase")
public class FaseController {

    @Autowired
    FaseService service;

    @GetMapping(value="/")
    public List<Fase> getFases() {
        return service.findAll();
    }

    @GetMapping(value="/{id}")
    public Fase getFaseById(@PathVariable(name="id") Long id) {
        return service.findById(id);
    }

    @PostMapping(value="/")
    public ResponseEntity<Fase> createFase(@RequestBody Fase fase) {
        return new ResponseEntity<>(service.create(fase), HttpStatus.CREATED);
    }

    @PutMapping(value="/{id}")
    public Fase updateFase(@RequestBody Fase fase, @PathVariable(name="id") Long id) {
        return service.update(fase, id);
    }


    @DeleteMapping(value="/{id}")
    public ResponseEntity<Fase> deleteFase(@PathVariable(value="id") Long id) {

         service.delete(id);

         return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }


}
