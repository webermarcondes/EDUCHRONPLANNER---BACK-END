package com.projetotcs.tcsbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.projetotcs.tcsbackend.customVOs.DiaDisponivelNaSemanaVo;
import com.projetotcs.tcsbackend.customVOs.ProfessorVoV1;
import com.projetotcs.tcsbackend.customVOs.ProfessorVoV2;
import com.projetotcs.tcsbackend.customVOsConverter.ProfessorVoConverter;
import com.projetotcs.tcsbackend.model.DiaDisponivelNaSemana;
import com.projetotcs.tcsbackend.model.Professor;
import com.projetotcs.tcsbackend.repository.ProfessorRepository;

@Service
public class ProfessorService {
 
    @Autowired
    ProfessorRepository repository;

    @Autowired
    DiaDisponivelNaSemanaService diaDisponivelNaSemanaService;

    public List<ProfessorVoV1> findAll() {
        return ProfessorVoConverter.professoresToProfessoresVo(repository.findAll());  
    }

    public ProfessorVoV1 findById(Long id) {

        Professor professor = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Nenhum professor foi encontrado com o ID informado"));
        
        return ProfessorVoConverter.professorToProfessorVoV1(professor);  
    }

    public ProfessorVoV1 create(ProfessorVoV1 professorVo) {

        Professor professor = ProfessorVoConverter.ProfessorVoV1ToProfessor(professorVo);
        
        List<DiaDisponivelNaSemana> diasDisponiveisNaSemanaComIds = new ArrayList<>();

        for(DiaDisponivelNaSemana diaDisponivelNaSemanaSemId: professor.getDiasDisponiveisnaSemana()) {

            diasDisponiveisNaSemanaComIds.add(diaDisponivelNaSemanaService.findByDiaDaSemana(
                                        diaDisponivelNaSemanaSemId.getDiaDaSemana()));
        }
  
        professor.setDiasDisponiveisnaSemana(diasDisponiveisNaSemanaComIds);

        repository.save(professor);

        return professorVo;

    }

    public ProfessorVoV2 update(ProfessorVoV2 professorVo, Long id) {

        var entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Nenhum professor foi encontrado com o ID informado"));

        List<DiaDisponivelNaSemana> diasDisponiveisNaSemana = new ArrayList<>();

        for(DiaDisponivelNaSemanaVo diaDisponivelNaSemanaVo: professorVo.getDiasDisponiveisnaSemana()) {

            diasDisponiveisNaSemana.add(diaDisponivelNaSemanaService.findByDiaDaSemana(
                                        diaDisponivelNaSemanaVo.getDiaDaSemana()));
        }

        entity.setId(id);
        entity.setNomeCompleto(professorVo.getNomeCompleto());
        entity.setTelefone(professorVo.getTelefone());
        entity.setQtdeDiasDeAula(professorVo.getQtdeDiasDeAula());
        entity.setStatus(professorVo.getStatus());
        entity.setDiasDisponiveisnaSemana(diasDisponiveisNaSemana);
        
        repository.save(entity);

        return professorVo;

    }


    
    /* 
    public void delete(Long id) {

        repository.deleteById(id);
    }*/

}
