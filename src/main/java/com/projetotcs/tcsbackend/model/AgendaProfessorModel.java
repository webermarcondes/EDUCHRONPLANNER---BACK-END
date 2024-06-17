package com.projetotcs.tcsbackend.model;


import jakarta.persistence.*;


/*
A agenda professor deve ser preenchida da seguinte forma:

No cadastro de professor:  professor e dia;
No cadastro de disciplina: disciplina e sala
 */
@Entity
@Table(name="agendaProfessor")
public class AgendaProfessorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name="professor_id")
    private ProfessorModel professor;

    @ManyToOne()
    @JoinColumn(name="diaDaSemana_id", nullable=false)
    private DiaDaSemanaModel diaDaSemana;

    @ManyToOne()
    @JoinColumn(name="disciplina_id")
    private DisciplinaModel disciplina;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProfessorModel getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorModel professor) {
        this.professor = professor;
    }

    public DiaDaSemanaModel getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setDiaDaSemana(DiaDaSemanaModel diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public DisciplinaModel getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaModel disciplina) {
        this.disciplina = disciplina;
    }

}
