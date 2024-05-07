package com.projetotcs.tcsbackend.controller;

import java.util.List;
import java.util.Optional;

import com.projetotcs.tcsbackend.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetotcs.tcsbackend.customVOs.ProfessorVoV1;
import com.projetotcs.tcsbackend.customVOs.ProfessorVoV2;
import com.projetotcs.tcsbackend.services.ProfessorService;


/*
Falta implementar uma constraint para impedir que o professor seja deletado;

Definir busca por nome de professor

Definir um atributo unico para o professor, que não seja o ID;

Verificar se a desativação do professor fica em um metodo separado, ou pode ser no PutMapping
que já está implementado;

*/

@RestController
@RequestMapping("/api/professor")
public class ProfessorController {

    @Autowired
    ProfessorService service;
 
     @GetMapping(value="/")
     public List<Professor> getProfessores() {
        return service.findAll();
        
     }

     @GetMapping(value="/{id}")
     public Professor getProfessorById(@PathVariable(value="id") Long id) {
        return service.findById(id);
        
     }


     @PostMapping(value="/")
     public ResponseEntity<Professor> createProfessor(@RequestBody Professor Professor) {

        return new ResponseEntity<>(service.create(Professor), HttpStatus.CREATED);
     }


     @PutMapping(value="/{id}")
     public Professor updateProfessor(@RequestBody Professor professor, @PathVariable Long id) {

         return service.update(professor, id);

     }


     /* 
     @DeleteMapping(value="/{id}")
     public ResponseEntity<Professor> deleteProfessor(@PathVariable(value= "id") Long id) {
        service.delete(id);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
     }*/
    
}
