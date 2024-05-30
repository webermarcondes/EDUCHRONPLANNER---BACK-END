package com.projetotcs.tcsbackend.controller;
import com.projetotcs.tcsbackend.model.SalaModel;
import com.projetotcs.tcsbackend.services.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Criar validação no Update para evitar que um registro de sala tenha seu número atualizado
//para um número que já está persistido em banco;

@RestController
@RequestMapping(value="/api/sala")
public class SalaController {

    @Autowired
    SalaService service;

    @GetMapping(value="/")
    public List<SalaModel> getSalas() {
        return service.findAll();
    }

    @GetMapping(value="/{id}")
    public SalaModel getSalaById(@PathVariable(name="id") Long id){
        return service.findById(id);
    }

    @GetMapping(value="buscapornumero/{numero_sala}")
    public Optional<SalaModel> getSalaByNumero(@PathVariable(name="numero_sala") Integer numeroSala) {
        return service.findByNumero(numeroSala);
    }
    

    @PostMapping(value="/")
    public ResponseEntity<SalaModel> createSala(@RequestBody SalaModel sala) {

        return new ResponseEntity<>(service.create(sala), HttpStatus.CREATED);
    }

    @PutMapping(value="/")
    public SalaModel updateSala(@RequestBody SalaModel sala, @PathVariable Long id){
        return service.update(sala, id);
    }


     @DeleteMapping(value="/{id}")
     public ResponseEntity<SalaModel> deleteSala(@PathVariable(value= "id") Long id) {
        service.delete(id);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
     }

}
