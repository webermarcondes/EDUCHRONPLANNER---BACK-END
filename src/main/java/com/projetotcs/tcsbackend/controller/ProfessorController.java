package com.projetotcs.tcsbackend.controller;

import java.util.List;

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

Falta implementar a foto de perfil no VO e nos converters;

Como fazer o controle via back-end se um dia da semana atribuido a um professor está disponível ou não?

   - Fazer o controle via tabela de junção de professor e dia da semana?;
   - Fazer o controle via disciplina vinculada ao professor?

- Deve ser feito um método separado para inativar o professor?

*/
@RestController
@RequestMapping("/api/professor")
public class ProfessorController {

    @Autowired
    ProfessorService service;
 
     @GetMapping(value="/")
     public List<ProfessorVoV1> getProfessores() {
        return service.findAll();
        
     }

     @GetMapping(value="/{id}")
     public ProfessorVoV1 getProfessorById(@PathVariable(value="id") Long id) {
        return service.findById(id);
        
     }

     @PostMapping(value="/")
     public ResponseEntity<ProfessorVoV1> createProfessor(@RequestBody ProfessorVoV1 ProfessorVo) {

        return new ResponseEntity<>(service.create(ProfessorVo), HttpStatus.CREATED);
     }


     @PutMapping(value="/{id}")
     public ProfessorVoV2 updateProfessor(@RequestBody ProfessorVoV2 professorVo, @PathVariable Long id) {

         return service.update(professorVo, id);

     }


     /* 
     @DeleteMapping(value="/{id}")
     public ResponseEntity<Professor> deleteProfessor(@PathVariable(value= "id") Long id) {
        service.delete(id);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
     }*/
    
}
