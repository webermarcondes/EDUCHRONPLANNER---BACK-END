package com.projetotcs.tcsbackend.model;

import java.util.List;

public class Turma {


    private Long id;
    private List<Disciplina> disciplinas;
    private Curso curso;
    private Integer fase;


    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
    public Curso getCurso() {
        return curso;
    }
    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    public Integer getFase() {
        return fase;
    }
    public void setFase(Integer fase) {
        this.fase = fase;
    }
  

    
}
