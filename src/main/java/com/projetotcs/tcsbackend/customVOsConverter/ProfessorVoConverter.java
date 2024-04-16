package com.projetotcs.tcsbackend.customVOsConverter;

import java.util.ArrayList;
import java.util.List;

import com.projetotcs.tcsbackend.customVOs.DiaDisponivelNaSemanaVo;
import com.projetotcs.tcsbackend.customVOs.ProfessorVo;
import com.projetotcs.tcsbackend.model.DiaDisponivelNaSemana;
import com.projetotcs.tcsbackend.model.Professor;

public class ProfessorVoConverter {
    

    public static Professor professorVoToProfessor(ProfessorVo professorVo) {
        
        Professor professor = new Professor();

        List<DiaDisponivelNaSemana> diasDisponiveisNaSemana = new ArrayList<>();

        professor.setNomeCompleto(professorVo.getNomeCompleto());
        professor.setTelefone(professorVo.getTelefone());
        professor.setQtdeDiasDeAula(professorVo.getQtdeDiasDeAula());
        
        diasDisponiveisNaSemana.addAll(DiaDisponivelNaSemanaVoConverter.
        diasDisponiveisnaSemanaVoToDiasDisponiveisnaSemana(professorVo.getDiasDisponiveisnaSemana()));


        System.out.print(diasDisponiveisNaSemana);
        
        professor.setDiasDisponiveisnaSemana(diasDisponiveisNaSemana);

        return professor;
    } 















    public static ProfessorVo ProfessorToProfessorVo(Professor professor) {
        
        ProfessorVo professorVo = null;
        return professorVo;
    } 
}
