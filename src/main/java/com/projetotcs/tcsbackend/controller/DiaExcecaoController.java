package com.projetotcs.tcsbackend.controller;


import com.projetotcs.tcsbackend.model.DiaExcecao;
import com.projetotcs.tcsbackend.services.DiaExcecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*

- Criar uma Migration para Dia Exceção, para que sejam cadastrados alguns dias no banco de dados;

 */

@RestController
@RequestMapping("/api/diaexcecao")
public class DiaExcecaoController {

    @Autowired
    DiaExcecaoService service;

    @GetMapping(value="/")
    public List<DiaExcecao> getDiasExcecao() {
        return service.findAll();
    }

    @GetMapping(value="/{id}")
    public DiaExcecao getDiaExcecaoById(@PathVariable(value="id") Long id) {
        return service.findById(id);
    }

    @PostMapping(value="/")
    public ResponseEntity<DiaExcecao> createDiaExcecao(@RequestBody DiaExcecao diaExcecao) {

        return new ResponseEntity<>(service.create(diaExcecao), HttpStatus.CREATED);
    }


    @PutMapping(value="/{id}")
    public DiaExcecao updateDiaExcecao(@RequestBody DiaExcecao diaExcecao, @PathVariable(value="id") Long id) {
        return service.update(diaExcecao, id);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<DiaExcecao> deleteDiaExcecao(@PathVariable(value="id") Long id) {
        service.delete(id);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
