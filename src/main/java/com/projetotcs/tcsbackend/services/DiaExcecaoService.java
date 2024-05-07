package com.projetotcs.tcsbackend.services;


import com.projetotcs.tcsbackend.model.DiaExcecao;
import com.projetotcs.tcsbackend.repository.DiaExcecaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaExcecaoService {

    @Autowired
    DiaExcecaoRepository repository;

    public List<DiaExcecao> findAll() {
        return repository.findAll();
    }

    public DiaExcecao findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de dia exceção com o ID informado"));
    }

    public DiaExcecao create(DiaExcecao diaExcecao) {
        return repository.save(diaExcecao);
    }

    public DiaExcecao update(DiaExcecao diaExcecao, Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de dia exceção com o ID informado para atualizar informações"));

        entity.setData(diaExcecao.getData());
        entity.setMotivo(diaExcecao.getMotivo());

        repository.save(entity);

        return diaExcecao;
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }
}
