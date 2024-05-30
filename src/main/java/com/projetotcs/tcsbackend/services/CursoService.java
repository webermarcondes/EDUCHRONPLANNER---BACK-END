package com.projetotcs.tcsbackend.services;


import com.projetotcs.tcsbackend.model.CursoModel;
import com.projetotcs.tcsbackend.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CursoService {

    @Autowired
    CursoRepository repository;

    public List<CursoModel> findAll(){
        List<CursoModel> cursos = repository.findAll();

        if(cursos.isEmpty()) {
            throw new ResourceNotFoundException("Não há cursos cadastrados");
        }

        return cursos;
    }

    public CursoModel findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de curso com o ID informado"));

    }

    public CursoModel create(CursoModel curso) {
        return repository.save(curso);
    }

    public CursoModel update(CursoModel curso, Long id) {

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não há registro de curso com o ID informado para atualizar informações"));

        entity.setNome(curso.getNome());
        entity.setQtdeFases(curso.getQtdeFases());
        entity.setUsuarioCoordenador(curso.getUsuarioCoordenador());

        repository.save(entity);

        return entity;
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

}
