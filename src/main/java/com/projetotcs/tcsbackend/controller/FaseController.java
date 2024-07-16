package com.projetotcs.tcsbackend.controller;

import com.projetotcs.tcsbackend.model.FaseModel;
import com.projetotcs.tcsbackend.services.FaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/fase")
public class FaseController {

    @Autowired
    FaseService service;

    @GetMapping(value="/get-fases")
    public List<FaseModel> getFases() {
        return service.findAll();
    }

    @GetMapping(value="/get-fase/{id}")
    public FaseModel getFaseById(@PathVariable(name="id") Long id) {
        return service.findById(id);
    }

    @GetMapping(value="get-fase-by-numero/{numero_fase}")
    public FaseModel getFaseByNumero(@PathVariable(name="numero_fase") Integer numeroFase) {
        return service.findByNumero(numeroFase);
    }

    @PostMapping(value="/create-fase")
    public ResponseEntity<FaseModel> createFase(@RequestBody FaseModel fase) {
        return new ResponseEntity<>(service.create(fase), HttpStatus.CREATED);
    }

    @PutMapping(value="/update-fase/{id}")
    public FaseModel updateFase(@RequestBody FaseModel fase, @PathVariable(name="id") Long id) {
        return service.update(fase, id);
    }


    @DeleteMapping(value="/delete-fase/{id}")
    public ResponseEntity<FaseModel> deleteFase(@PathVariable(value="id") Long id) {

         service.delete(id);

         return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
