package com.projetotcs.tcsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetotcs.tcsbackend.model.ProfessorModel;

public interface ProfessorRepository  extends JpaRepository<ProfessorModel, Long>{

    ProfessorModel findByCpf(String cpf);
}
