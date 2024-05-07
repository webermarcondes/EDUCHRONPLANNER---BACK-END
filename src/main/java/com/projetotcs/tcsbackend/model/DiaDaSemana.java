package com.projetotcs.tcsbackend.model;



//import com.projetotcs.tcsbackend.enums.DiaDaSemana;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
        import jakarta.persistence.Table;

@Entity
@Table(name="diaDaSemana")
public class DiaDaSemana {
    
    @Id
    public Long id;

    @Column()
    public String descricao;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
