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
        return repository.findAll();
    }

    public Disciplina findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum registro de disciplina foi encontrado com o ID informado"));
    }

    public List<Disciplina> findByNumeroSala(Integer numeroSala) {

        Long salaId = salaService.findByNumero(numeroSala).getId();

        return repository.findBySalaId(salaId);


    }

    public List<Disciplina> findByNumeroFase(Integer numeroFase) {

        Long faseId = faseService.findByNumero(numeroFase).getId();

        return repository.findByFaseId(faseId);


    }

    public Disciplina create(Disciplina disciplina) {
        return repository.save(disciplina);
    }

    public Disciplina update(Disciplina disciplina, Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma disciplina foi encontrada com o ID informado"));

        entity.setCargaHoraria(disciplina.getCargaHoraria());
        entity.setNome(disciplina.getNome());
        entity.setCodigoCor(disciplina.getCodigoCor());
        entity.setSala(disciplina.getSala());
        entity.setFase(disciplina.getFase());

        repository.save(entity);

        return disciplina;

    }

    public void delete(Long id) {

        repository.deleteById(id);
    }



}
