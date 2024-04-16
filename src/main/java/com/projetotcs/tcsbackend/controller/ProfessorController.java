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

import com.projetotcs.tcsbackend.customVOs.ProfessorVo;
import com.projetotcs.tcsbackend.customVOsConverter.ProfessorVoConverter;
import com.projetotcs.tcsbackend.model.Professor;
import com.projetotcs.tcsbackend.services.ProfessorService;

@RestController
@RequestMapping("/api/professor")
public class ProfessorController {

    @Autowired
    ProfessorService service;

    /**
     * 
     * 
     * @GetMapping(value="/pedidos")
     * 
     * @PostMapping(value = "/pedidos"
     * 
     * @PutMapping(value = "/pedidos")
     * 
     * @DeleteMapping(value="/pedidos/{id}")
     * 
     * @PathVariable -> para dados passados via URL
     * 
     * @RequestBody -> para dados passados via corpo da requisição;
     * 
     * 
     */
    
     @GetMapping(value="/")
     public List<Professor> getProfessores() {
        return service.findAll();
        
     }

     @GetMapping(value="/{id}")
     public Professor getProfessorById(@PathVariable(value="id") Long id) {
        return service.findById(id);
        
     }

     @PostMapping(value="/")
     public ResponseEntity<Professor> createProfessor(@RequestBody ProfessorVo professorVo) {

        Professor professor = service.create(professorVo);

        return new ResponseEntity<>(professor, HttpStatus.CREATED);
     }


     @PutMapping(value="/{id}")
     public Professor updateProfessor(@RequestBody Professor professor, @PathVariable Long id) {

         return service.update(professor, id);

     }

     @DeleteMapping(value="/{id}")
     public ResponseEntity<Professor> deleteProfessor(@PathVariable(value= "id") Long id) {
        service.delete(id);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
     }
    
}
