package com.projetotcs.tcsbackend.customVOsConverter;

import java.util.ArrayList;
import java.util.List;

import com.projetotcs.tcsbackend.customVOs.DiaDisponivelNaSemanaVo;
import com.projetotcs.tcsbackend.model.DiaDisponivelNaSemana;

public class DiaDisponivelNaSemanaVoConverter {
    

    public static List<DiaDisponivelNaSemana> diasDisponiveisnaSemanaVoToDiasDisponiveisnaSemana(List<DiaDisponivelNaSemanaVo> diasDisponiveisNaSemanaVo){
        
        List<DiaDisponivelNaSemana> diasDisponiveisNaSemana = new ArrayList<>();  
        DiaDisponivelNaSemana diaDisponivelNaSemana;
        
        
        for (DiaDisponivelNaSemanaVo diaDisponivelNaSemanaVo: diasDisponiveisNaSemanaVo) {

           diaDisponivelNaSemana = new DiaDisponivelNaSemana();

           diaDisponivelNaSemana.setDiaDaSemana(diaDisponivelNaSemanaVo.getDiaDaSemana());

           diasDisponiveisNaSemana.add(diaDisponivelNaSemana);
        }

        
        return diasDisponiveisNaSemana;
    }




    
    public static List<DiaDisponivelNaSemanaVo> diasDisponiveisNaSemanaToDiasDisponiveisNaSemanaVo(
        List<DiaDisponivelNaSemana> diasDisponiveisNaSemana){

        List<DiaDisponivelNaSemanaVo> diasDisponiveisNaSemanaVo = new ArrayList<>();

        for(DiaDisponivelNaSemana diaDisponivelNaSemana: diasDisponiveisNaSemana) {
            DiaDisponivelNaSemanaVo diaDisponivelNaSemanaVo = new DiaDisponivelNaSemanaVo();
        
            diaDisponivelNaSemanaVo.setDiaDaSemana(diaDisponivelNaSemana.getDiaDaSemana());

            diasDisponiveisNaSemanaVo.add(diaDisponivelNaSemanaVo);
        }

        return diasDisponiveisNaSemanaVo;
    }

}
