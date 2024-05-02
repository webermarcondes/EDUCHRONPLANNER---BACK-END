package com.projetotcs.tcsbackend.customVOs;


import java.io.Serializable;

public class DiaDaSemanaVo implements Serializable{
   
    public String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
