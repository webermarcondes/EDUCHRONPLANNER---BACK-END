package com.projetotcs.tcsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetotcs.tcsbackend.model.Professor;

public interface ProfessorRepository  extends JpaRepository<Professor, Long>{
    
}
