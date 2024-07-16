package com.projetotcs.tcsbackend.controller;

import com.projetotcs.tcsbackend.model.DiaExcecaoModel;
import com.projetotcs.tcsbackend.services.DiaExcecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/dia-excecao")
public class DiaExcecaoController {

    @Autowired
    DiaExcecaoService service;

    @GetMapping(value="/get-dias-excecao")
    public List<DiaExcecaoModel> getDiasExcecao() {
        return service.findAll();
    }

    @GetMapping(value="/get-dia-excecao/{id}")
    public DiaExcecaoModel getDiaExcecaoById(@PathVariable(value="id") Long id) {
        return service.findById(id);
    }

    @PostMapping(value="/create-dia-excecao")
    public ResponseEntity<DiaExcecaoModel> createDiaExcecao(@RequestBody DiaExcecaoModel diaExcecao) {

        return new ResponseEntity<>(service.create(diaExcecao), HttpStatus.CREATED);
    }


    @PutMapping(value="/update-dia-excecao/{id}")
    public DiaExcecaoModel updateDiaExcecao(@RequestBody DiaExcecaoModel diaExcecao, @PathVariable(value="id") Long id) {
        return service.update(diaExcecao, id);
    }

    @DeleteMapping(value="/delete-dia-excecao/{id}")
    public ResponseEntity<DiaExcecaoModel> deleteDiaExcecao(@PathVariable(value="id") Long id) {
        service.delete(id);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
