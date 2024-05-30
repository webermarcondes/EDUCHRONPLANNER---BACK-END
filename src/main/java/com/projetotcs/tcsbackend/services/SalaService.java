package com.projetotcs.tcsbackend.services;


import com.projetotcs.tcsbackend.model.SalaModel;
import com.projetotcs.tcsbackend.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SalaService {


    @Autowired
    SalaRepository repository;

    public List<SalaModel> findAll() {

        List<SalaModel> salas = repository.findAll();

        if(salas.isEmpty()) {
            throw new ResourceNotFoundException("Não há salas cadastradas");
        }

        return salas;
    }


    public SalaModel findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não registro de sala com o ID informado"));
    }


    public Optional<SalaModel> findByNumero(Integer numeroSala) {

        Optional<SalaModel> sala = repository.findByNumero(numeroSala);

        if(sala.isPresent()) {
            return sala;
        }

        throw new ResourceNotFoundException("Não há registro de sala com o número informado");
    }

    public SalaModel create(SalaModel sala) {

        Optional<SalaModel> salaEntity = repository.findByNumero(sala.getNumero());

        if (salaEntity.isPresent()) {
            throw  new DataIntegrityViolationException("Já existe sala com o número informado");
        }
        else {
            repository.save(sala);
        }
        return sala;
    }

    public SalaModel update(SalaModel sala, Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não registro de sala com o ID informado"));

        entity.setNumero(sala.getNumero());

        repository.save(entity);

        return entity;
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }


}
