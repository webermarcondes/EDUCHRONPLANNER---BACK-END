package com.projetotcs.tcsbackend.customVOs;

import java.io.Serializable;
import java.util.List;

import com.projetotcs.tcsbackend.enums.Status;



public class ProfessorVoV2 implements Serializable {


    private Long id;

    private String nomeCompleto;

    private String telefone;

    private List<DiaDaSemanaVo> diasDaSemanaVo;

    private Integer qtdeDiasDeAula;

    //fotoPerfil;

    private Status status;

 
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public Integer getQtdeDiasDeAula() {
        return qtdeDiasDeAula;
    }

    public void setQtdeDiasDeAula(Integer qtdeDiasDeAula) {
        this.qtdeDiasDeAula = qtdeDiasDeAula;
    }

    public List<DiaDaSemanaVo> getDiasDaSemanaVo() {
        return diasDaSemanaVo;
    }

    public void setDiasDaSemanaVo(List<DiaDaSemanaVo> diasDaSemanaVo) {
        this.diasDaSemanaVo = diasDaSemanaVo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    

    
}
