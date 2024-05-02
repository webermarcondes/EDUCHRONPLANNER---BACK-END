package com.projetotcs.tcsbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.projetotcs.tcsbackend.model.DiaDaSemana;
import com.projetotcs.tcsbackend.model.Professor;
import com.projetotcs.tcsbackend.repository.ProfessorRepository;

@Service
public class ProfessorService {
 
    @Autowired
    ProfessorRepository repository;

    @Autowired
    DiaDaSemanaService diaDaSemanaService;

    @Autowired
    AgendaProfessorService agendaProfessorService;

    public List<Professor> findAll() {
        //return ProfessorVoConverter.professoresToProfessoresVo(repository.findAll());
        return repository.findAll();
    }

    public Professor findById(Long id) {

        /*Professor professor = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Nenhum professor foi encontrado com o ID informado"));
        
        return ProfessorVoConverter.professorToProfessorVoV1(professor); */

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nenhum professor foi encontrado com o ID informado"));
    }

    public Professor create(Professor professor) {

        /*List<DiaDaSemanaVo> diasDaSemanaVo = professorVo.getDiasDaSemana();

        Professor professor = ProfessorVoConverter.ProfessorVoV1ToProfessor(professorVo);
        ProfDiaSemDisc profDiaSemDisc = new ProfDiaSemDisc();*/

        repository.save(professor);

        //profDiaSemDisc.setProfessor(professor);


        /*for(DiaDaSemanaVo diaDaSemanaSemId: diasDaSemanaVo) {

            profDiaSemDisc.setDiaDaSemana(diaDaSemanaService.findByDescricao(diaDaSemanaSemId.getDescricao()));
            profDiaSemDiscService.create(profDiaSemDisc);
        }*/

        return professor;

    }

    public Professor update(Professor professor, Long id) {

        var entity = repository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Nenhum professor foi encontrado com o ID informado"));

        List<DiaDaSemana> diasDisponiveisNaSemana = new ArrayList<>();

        /*for(DiaDisponivelNaSemanaVo diaDisponivelNaSemanaVo: professorVo.getDiasDisponiveisnaSemana()) {

            diasDisponiveisNaSemana.add(diaDisponivelNaSemanaService.findByDiaDaSemana(
                                        diaDisponivelNaSemanaVo.getDiaDaSemana()));
        }*/

        entity.setId(id);
        entity.setNomeCompleto(professor.getNomeCompleto());
        entity.setTelefone(professor.getTelefone());
        entity.setQtdeDiasDeAula(professor.getQtdeDiasDeAula());
        entity.setStatus(professor.getStatus());
        //entity.setDiasDisponiveisnaSemana(diasDisponiveisNaSemana);
        
        repository.save(entity);

        return professor;

    }


    
    /* 
    public void delete(Long id) {

        repository.deleteById(id);
    }*/

}
