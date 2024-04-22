package com.projetotcs.tcsbackend.customVOsConverter;


import java.util.ArrayList;
import java.util.List;

import com.projetotcs.tcsbackend.customVOs.DiaDisponivelNaSemanaVo;

import com.projetotcs.tcsbackend.customVOs.ProfessorVoV1;
import com.projetotcs.tcsbackend.model.DiaDisponivelNaSemana;
import com.projetotcs.tcsbackend.model.Professor;

public class ProfessorVoConverter {
    

    public static Professor ProfessorVoV1ToProfessor(ProfessorVoV1 professorVoV1) {
        

        Professor professor = new Professor();
        List<DiaDisponivelNaSemana> diasDisponiveisNaSemana = new ArrayList<>();

        professor.setNomeCompleto(professorVoV1.getNomeCompleto());
        professor.setTelefone(professorVoV1.getTelefone());
        professor.setQtdeDiasDeAula(professorVoV1.getQtdeDiasDeAula());
        professor.setStatus(professorVoV1.getStatus());

        diasDisponiveisNaSemana = DiaDisponivelNaSemanaVoConverter.
            diasDisponiveisnaSemanaVoToDiasDisponiveisnaSemana(professorVoV1.getDiasDisponiveisnaSemana());   
        
        professor.setDiasDisponiveisnaSemana(diasDisponiveisNaSemana);

        
        return professor;
    }


    public static ProfessorVoV1 professorToProfessorVoV1(Professor professor) {
        
        
        ProfessorVoV1 professorVoV1 = new ProfessorVoV1();
        List<DiaDisponivelNaSemanaVo> diasDisponiveisNaSemanaVo = new ArrayList<>();

        professorVoV1.setNomeCompleto(professor.getNomeCompleto());
        professorVoV1.setQtdeDiasDeAula(professor.getQtdeDiasDeAula());
        professorVoV1.setTelefone(professor.getTelefone());
        professorVoV1.setStatus(professor.getStatus());

        diasDisponiveisNaSemanaVo = DiaDisponivelNaSemanaVoConverter.diasDisponiveisNaSemanaToDiasDisponiveisNaSemanaVo(professor.getDiasDisponiveisnaSemana());
        professorVoV1.setDiasDisponiveisnaSemana(diasDisponiveisNaSemanaVo);

        return professorVoV1;
    }


    public static List<ProfessorVoV1> professoresToProfessoresVo(List<Professor> professores) {

        List<ProfessorVoV1> professoresVo = new ArrayList<>();

        for(Professor professor: professores) {
            professoresVo.add(professorToProfessorVoV1(professor));
        }

        return professoresVo;

}














    public static ProfessorVoV1 ProfessorToProfessorVoV1(Professor professor) {
        
        ProfessorVoV1 ProfessorVoV1 = null;
        return ProfessorVoV1;
    } 
}
