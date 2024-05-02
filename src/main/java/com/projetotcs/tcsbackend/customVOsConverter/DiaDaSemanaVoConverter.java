package com.projetotcs.tcsbackend.customVOsConverter;

import java.util.ArrayList;
import java.util.List;

import com.projetotcs.tcsbackend.customVOs.DiaDaSemanaVo;
import com.projetotcs.tcsbackend.model.DiaDaSemana;

public class DiaDaSemanaVoConverter {
    

    public static List<DiaDaSemana> diasDaSemanaVoToDiasDiasDaSemana(List<DiaDaSemanaVo> diasDaSemanaVo){
        
        List<DiaDaSemana> diasDaSemana = new ArrayList<>();
        DiaDaSemana diaDaSemana;
        
        
        for (DiaDaSemanaVo diaDaSemanaVo: diasDaSemanaVo) {

           diaDaSemana = new DiaDaSemana();

           diaDaSemana.setDescricao(diaDaSemanaVo.getDescricao());

            diasDaSemana.add(diaDaSemana);
        }

        
        return diasDaSemana;
    }




    
    public static List<DiaDaSemanaVo> diasDaSemanaToDiasDaSemanaVo(
        List<DiaDaSemana> diasDaSemana){

        List<DiaDaSemanaVo> diasDaSemanaVo = new ArrayList<>();

        for(DiaDaSemana diaDaSemana: diasDaSemana) {
            DiaDaSemanaVo diaDaSemanaVo = new DiaDaSemanaVo();

            diaDaSemanaVo.setDescricao(diaDaSemana.getDescricao());

            diasDaSemanaVo.add(diaDaSemanaVo);
        }

        return diasDaSemanaVo;
    }

}
