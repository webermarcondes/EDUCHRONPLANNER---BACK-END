package com.projetotcs.tcsbackend.customVOs;

import java.io.Serializable;
import java.util.List;

import com.projetotcs.tcsbackend.enums.Status;



public class ProfessorVoV1 implements Serializable {

    private String nomeCompleto;

    private String telefone;

    private List<DiaDaSemanaVo> diasDaSemana;

    private Integer qtdeDiasDeAula;

    //fotoPerfil;

    private Status status;

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

    public List<DiaDaSemanaVo> getDiasDaSemana() {
        return diasDaSemana;
    }

    public void setDiasDaSemana(List<DiaDaSemanaVo> diasDaSemana) {
        this.diasDaSemana = diasDaSemana;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    

    
}
