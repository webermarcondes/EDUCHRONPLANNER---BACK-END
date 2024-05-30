package com.projetotcs.tcsbackend.repository;

import com.projetotcs.tcsbackend.model.FaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaseRepository extends JpaRepository<FaseModel, Long> {

    FaseModel findByNumero(Integer numeroFase);
}
