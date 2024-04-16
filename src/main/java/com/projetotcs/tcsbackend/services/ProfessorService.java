package com.projetotcs.tcsbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.projetotcs.tcsbackend.customVOs.ProfessorVo;
import com.projetotcs.tcsbackend.customVOsConverter.ProfessorVoConverter;
import com.projetotcs.tcsbackend.model.Professor;
import com.projetotcs.tcsbackend.repository.ProfessorRepository;

@Service
public class ProfessorService {
 
    @Autowired
    ProfessorRepository repository;

    public List<Professor> findAll() {
        return repository.findAll();
    }

    public Professor findById(Long id) {
        return repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Nenhum professor foi encontrado com o ID informado"));} 
    

    public Professor create(ProfessorVo professorVo) {

        Professor professor = ProfessorVoConverter.professorVoToProfessor(professorVo);

        

        return repository.save(professor);

    }

    public Professor update(Professor professor, Long id) {

        var entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Nenhum professor foi encontrado com o ID informado"));

        entity.setNomeCompleto(professor.getNomeCompleto());
        entity.setTelefone(professor.getTelefone());

        return repository.save(entity);

    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

}
