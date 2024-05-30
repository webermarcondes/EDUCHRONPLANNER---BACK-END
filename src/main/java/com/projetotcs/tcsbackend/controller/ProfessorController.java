package com.projetotcs.tcsbackend.controller;

import java.util.List;

import com.projetotcs.tcsbackend.exceptions.StatusInativoException;
import com.projetotcs.tcsbackend.model.ProfessorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.projetotcs.tcsbackend.services.ProfessorService;


/*
Fazer validação no update e no create para que não sejá possível cadastrar um usuário com um CPF
que já está persistido, ou atualizar o CPF de um usuário para um que já está persistido
*/
@RestController
@RequestMapping("/api/professor")
public class ProfessorController {

    @Autowired
    ProfessorService service;
 
     @GetMapping(value="/")
     public List<ProfessorModel> getProfessores() {
        return service.findAll();
        
     }

     @GetMapping(value="/{id}")
     public ProfessorModel getProfessorById(@PathVariable(value="id") Long id) {
        return service.findById(id);
        
     }

     @GetMapping(value="/buscaporcpf/")
     public ProfessorModel getProfessorByCpf(@RequestBody String cpf) {


         return service.findByCpf(cpf);
     }


     @PostMapping(value="/")
     public ResponseEntity<ProfessorModel> createProfessor(@RequestBody ProfessorModel professor) {

        return new ResponseEntity<>(service.create(professor), HttpStatus.CREATED);
     }


     @PutMapping(value="/{id}")
     public ProfessorModel updateProfessor(@RequestBody ProfessorModel professor, @PathVariable Long id) throws StatusInativoException {

         return service.update(professor, id);

     }

     @PatchMapping(value="/atualizarstatus/{id}")
     public ProfessorModel updateProfessorStatus(@PathVariable Long id, @RequestBody String status) {
         return service.updateProfessorStatus(id, status);


     }


    
}
