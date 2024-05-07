package com.projetotcs.tcsbackend.services;


import com.projetotcs.tcsbackend.model.Sala;
import com.projetotcs.tcsbackend.repository.SalaRepository;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {


    @Autowired
    SalaRepository repository;

    public List<Sala> findAll() {

        List<Sala> salas = repository.findAll();

        if(salas.isEmpty()) {
            throw new ResourceNotFoundException("Não há salas cadastradas");
        }

        return salas;
    }

    public Sala findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não registro de sala com o ID informado"));
    }

    public Sala findByNumero(Integer numeroSala) {

        Sala sala = repository.findByNumero(numeroSala);

        if(sala.getId() == null) {
            throw new ResourceNotFoundException("Não há registro de sala com o número informado");
        }

        return sala;
    }

    public Sala create(Sala sala) {
        return repository.save(sala);
    }

    public Sala update(Sala sala, Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não registro de sala com o ID informado para atualizar informações"));

        entity.setNumero(sala.getNumero());

        repository.save(entity);

        return sala;
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }


}
