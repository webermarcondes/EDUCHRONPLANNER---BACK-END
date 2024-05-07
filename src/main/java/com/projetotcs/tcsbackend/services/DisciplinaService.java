package com.projetotcs.tcsbackend.services;

import com.projetotcs.tcsbackend.model.Disciplina;
import com.projetotcs.tcsbackend.model.Sala;
import com.projetotcs.tcsbackend.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    DisciplinaRepository repository;

    @Autowired
    SalaService salaService;

    @Autowired
    FaseService faseService;

    public List<Disciplina> findAll() {

        List<Disciplina> disciplinas = repository.findAll();

        if(disciplinas.isEmpty()) {
            throw new ResourceNotFoundException("Não há disciplinas cadastradas");
        }

        return disciplinas;
    }

    public Disciplina findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de disciplina com o ID informado"));
    }

    //Atualizar método para busca por número fase e curso...
    public List<Disciplina> findByNumeroFase(Integer numeroFase) {

        Long faseId = faseService.findByNumero(numeroFase).getId();

        List<Disciplina> disciplinas = repository.findByFaseId(faseId);

        if(disciplinas.isEmpty()) {
            throw new ResourceNotFoundException("Não há registro de disciplina com o número de fase informado");

        }

        return disciplinas;
    }

    public Disciplina create(Disciplina disciplina) {
        return repository.save(disciplina);
    }

    public Disciplina update(Disciplina disciplina, Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de disciplina com o ID informado para atualizar informações"));

        entity.setCargaHoraria(disciplina.getCargaHoraria());
        entity.setNome(disciplina.getNome());
        entity.setCodigoCor(disciplina.getCodigoCor());

        entity.setFase(disciplina.getFase());

        repository.save(entity);

        return disciplina;

    }

    public void delete(Long id) {

        repository.deleteById(id);
    }



}
