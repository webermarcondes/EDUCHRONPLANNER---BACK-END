package com.projetotcs.tcsbackend.repository;

import com.projetotcs.tcsbackend.model.CursoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<CursoModel, Long> {
}
