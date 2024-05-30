package com.projetotcs.tcsbackend.services;


import java.util.List;

import com.projetotcs.tcsbackend.enums.Status;
import com.projetotcs.tcsbackend.exceptions.StatusInativoException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.projetotcs.tcsbackend.model.ProfessorModel;
import com.projetotcs.tcsbackend.repository.ProfessorRepository;


@Service
public class ProfessorService {
 
    @Autowired
    ProfessorRepository repository;

    public List<ProfessorModel> findAll() {

        List<ProfessorModel> professores = repository.findAll();

        if(professores.isEmpty()) {
            throw new ResourceNotFoundException("Não há professores cadastrados");
        }

        return professores;
    }

    public ProfessorModel findById(Long id) {

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não há registro de professor com o ID informado"));
    }

    public ProfessorModel findByCpf(String cpf) {

        JSONObject jsonString = new JSONObject(cpf);

        ProfessorModel professor = repository.findByCpf(jsonString.getString("cpf"));

        if(professor == null) {
            throw new ResourceNotFoundException("Não há professor cadastrado com o CPF informado");
        }

        return professor;
    }

    public ProfessorModel create(ProfessorModel professor) {

        repository.save(professor);

        return professor;

    }

    public ProfessorModel update(ProfessorModel professor, Long id) throws StatusInativoException {

        var entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Não há registro de professor com o ID informado para atualizar informações"));


        if(entity.getStatus() == Status.ATIVO) {
            entity.setNomeCompleto(professor.getNomeCompleto());
            entity.setTelefone(professor.getTelefone());
            entity.setQtdeDiasDeAula(professor.getQtdeDiasDeAula());
            entity.setCpf(professor.getCpf());
            entity.setUrlFotoPerfil(professor.getUrlFotoPerfil());

            repository.save(entity);

            return entity;
        }

        throw new StatusInativoException("Não é possível atualizar dados de um professor INATIVO");
    }

    public ProfessorModel updateProfessorStatus(Long id, String status) {

        JSONObject jsonString = new JSONObject(status);

        ProfessorModel professor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de professor com o ID informado para atualizar informações"));

        if (jsonString.getString("status").equals("ATIVO")) {
            professor.setStatus(Status.ATIVO);
        }
        else {
            professor.setStatus(Status.INATIVO);
        }

        repository.save(professor);
        return professor;
    }
}
