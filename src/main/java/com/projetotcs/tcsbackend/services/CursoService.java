package com.projetotcs.tcsbackend.services;


import com.projetotcs.tcsbackend.model.Curso;
import com.projetotcs.tcsbackend.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CursoService {

    @Autowired
    CursoRepository repository;

    public List<Curso> findAll(){
        return repository.findAll();
    }

    public Curso findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro de curso foi encontrado com o ID informado"));

    }

    public Curso create(Curso curso) {
        return repository.save(curso);
    }

    public Curso update(Curso curso, Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro de curso foi encontrado com o ID informado"));

        entity.setNome(curso.getNome());
        entity.setHorasTotais(curso.getHorasTotais());
        entity.setQtdeFases(curso.getQtdeFases());

        repository.save(entity);

        return curso;
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

}
