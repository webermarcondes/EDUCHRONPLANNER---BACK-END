package com.projetotcs.tcsbackend.controller;

import com.projetotcs.tcsbackend.model.DiaExcecaoModel;
import com.projetotcs.tcsbackend.services.DiaExcecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diaexcecao")
public class DiaExcecaoController {

    @Autowired
    DiaExcecaoService service;

    @GetMapping(value="/")
    public List<DiaExcecaoModel> getDiasExcecao() {
        return service.findAll();
    }

    @GetMapping(value="/{id}")
    public DiaExcecaoModel getDiaExcecaoById(@PathVariable(value="id") Long id) {
        return service.findById(id);
    }

    @PostMapping(value="/")
    public ResponseEntity<DiaExcecaoModel> createDiaExcecao(@RequestBody DiaExcecaoModel diaExcecao) {

        return new ResponseEntity<>(service.create(diaExcecao), HttpStatus.CREATED);
    }


    @PutMapping(value="/{id}")
    public DiaExcecaoModel updateDiaExcecao(@RequestBody DiaExcecaoModel diaExcecao, @PathVariable(value="id") Long id) {
        return service.update(diaExcecao, id);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<DiaExcecaoModel> deleteDiaExcecao(@PathVariable(value="id") Long id) {
        service.delete(id);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
