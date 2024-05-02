package com.projetotcs.tcsbackend.repository;

import com.projetotcs.tcsbackend.model.Fase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaseRepository extends JpaRepository<Fase, Long> {

    Fase findByNumero(Integer numeroFase);
}
