package com.projetotcs.tcsbackend.model;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="diaExcecao")
public class DiaExcecao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Verificar se a Data será de tipos pra datas, ou String..
    @Column
    private Date data;

    /*Verificar viabilidade de uso do atributo abaixo: */
    @Column
    private String motivo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
