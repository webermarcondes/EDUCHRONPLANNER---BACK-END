package com.projetotcs.tcsbackend.customVOsConverter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.projetotcs.tcsbackend.customVOs.DiaDisponivelNaSemanaVo;
import com.projetotcs.tcsbackend.enums.DiaDaSemana;
import com.projetotcs.tcsbackend.model.DiaDisponivelNaSemana;
import com.projetotcs.tcsbackend.services.DiaDisponivelNaSemanaService;

public class DiaDisponivelNaSemanaVoConverter {
    
    @Autowired
    static DiaDisponivelNaSemanaService service;
    
    public static List<DiaDisponivelNaSemana> diasDisponiveisnaSemanaVoToDiasDisponiveisnaSemana(List<DiaDisponivelNaSemanaVo> diasDisponiveisNaSemanaVo){
        
        List<DiaDisponivelNaSemana> diasDisponiveisNaSemana = new ArrayList<>();  
        DiaDisponivelNaSemana diaDisponivelNaSemana;
        
        
        for (DiaDisponivelNaSemanaVo DiaDisponivelNaSemanaVo: diasDisponiveisNaSemanaVo) {

           diaDisponivelNaSemana = new DiaDisponivelNaSemana();

           DiaDaSemana diaDaSemana = DiaDaSemana.getByDescricao(DiaDisponivelNaSemanaVo.getDiaDaSemana());
           
           diaDisponivelNaSemana = service.findByDiaDaSemana(diaDaSemana);

           diasDisponiveisNaSemana.add(diaDisponivelNaSemana);
        }

        
        return diasDisponiveisNaSemana;
    }




    /* 
    public static DiaDisponivelNaSemanaVo DiaDisponivelNaSemanaToDiaDisponivelNaSemanaVo(
        DiaDisponivelNaSemana DiaDisponivelNaSemana){

    }*/

}
