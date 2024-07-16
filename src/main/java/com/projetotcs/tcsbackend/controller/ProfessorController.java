package com.projetotcs.tcsbackend.controller;

import java.util.List;
import com.projetotcs.tcsbackend.exceptions.StatusInativoException;
import com.projetotcs.tcsbackend.model.ProfessorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.projetotcs.tcsbackend.services.ProfessorService;

@RestController
@RequestMapping("/api/professor")
public class ProfessorController {

    @Autowired
    ProfessorService service;
 
    @GetMapping(value="/get-professores")
     public List<ProfessorModel> getProfessores() {
        return service.findAll();
        
     }

     @GetMapping(value="/get-professor/{id}")
     public ProfessorModel getProfessorById(@PathVariable(value="id") Long id) {
        return service.findById(id);
        
     }

     @GetMapping(value="/get-professor-by-cpf")
     public ProfessorModel getProfessorByCpf(@RequestBody String cpf) {


         return service.findByCpf(cpf);
     }


     @PostMapping(value="/create-professor")
     public ResponseEntity<ProfessorModel> createProfessor(@RequestBody ProfessorModel professor) {

        return new ResponseEntity<>(service.create(professor), HttpStatus.CREATED);
     }


     @PutMapping(value="/update-professor/{id}")
     public ProfessorModel updateProfessor(@RequestBody ProfessorModel professor, @PathVariable Long id) throws StatusInativoException {

         return service.update(professor, id);

     }

     @PatchMapping(value="/update-professor-status/{id}")
     public ProfessorModel updateProfessorStatus(@PathVariable Long id, @RequestBody String status) {
         return service.updateProfessorStatus(id, status);


     }
}
