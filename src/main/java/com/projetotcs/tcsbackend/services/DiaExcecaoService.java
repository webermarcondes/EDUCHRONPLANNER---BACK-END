package com.projetotcs.tcsbackend.services;


import com.projetotcs.tcsbackend.model.DiaExcecaoModel;
import com.projetotcs.tcsbackend.repository.DiaExcecaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaExcecaoService {

    @Autowired
    DiaExcecaoRepository repository;

    public List<DiaExcecaoModel> findAll() {
        return repository.findAll();
    }

    public DiaExcecaoModel findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de dia exceção com o ID informado"));
    }

    public DiaExcecaoModel create(DiaExcecaoModel diaExcecao) {
        return repository.save(diaExcecao);
    }

    public DiaExcecaoModel update(DiaExcecaoModel diaExcecao, Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de dia exceção com o ID informado para atualizar informações"));

        entity.setData(diaExcecao.getData());
        entity.setMotivo(diaExcecao.getMotivo());

        repository.save(entity);

        return entity;
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }
}
