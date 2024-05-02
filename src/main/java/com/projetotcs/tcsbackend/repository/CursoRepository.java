package com.projetotcs.tcsbackend.repository;

import com.projetotcs.tcsbackend.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
