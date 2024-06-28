package com.projetotcs.tcsbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetotcs.tcsbackend.enums.NivelPermissao;
import com.projetotcs.tcsbackend.enums.Status;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column(unique = true, length = 11)
    private String cpf;

    @Column
    private String email;

    @Column
    private String senha;


    @Column
    private Status status;

    @Column
    private NivelPermissao nivelPermissao;

    @OneToMany(mappedBy = "usuarioCoordenador")
    @JsonIgnore
    List<CursoModel> cursos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public NivelPermissao getNivelPermissao() {
        return nivelPermissao;
    }

    public void setNivelPermissao(NivelPermissao nivelPermissao) {
        this.nivelPermissao = nivelPermissao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
