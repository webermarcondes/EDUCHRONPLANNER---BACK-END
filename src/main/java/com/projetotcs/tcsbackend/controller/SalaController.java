package com.projetotcs.tcsbackend.controller;

import com.projetotcs.tcsbackend.model.Sala;
import com.projetotcs.tcsbackend.services.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Verificar a viabilidade de definir um getMapping de busca por número pra fase, no service o método já
tá definido e é usado no DisciplinaService;
 */
@RestController
@RequestMapping(value="/api/sala")
public class SalaController {

    @Autowired
    SalaService service;

    @GetMapping(value="/")
    public List<Sala> getSalas() {
        return service.findAll();
    }


    @GetMapping(value="/{id}")
    public Sala getSalaById(@PathVariable(name="id") Long id){
        return service.findById(id);
    }

    @PostMapping(value="/")
    public ResponseEntity<Sala> createSala(@RequestBody Sala sala) {

        return new ResponseEntity<>(service.create(sala), HttpStatus.CREATED);
    }

    @PutMapping(value="/{id}")
    public Sala updateSala(@RequestBody Sala sala, @PathVariable(name="id") Long id) {
        return service.update(sala, id);
    }


     @DeleteMapping(value="/{id}")
     public ResponseEntity<Sala> deleteSala(@PathVariable(value= "id") Long id) {
        service.delete(id);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
     }

}
