package com.projetotcs.tcsbackend.repository;

import com.projetotcs.tcsbackend.enums.Status;
import com.projetotcs.tcsbackend.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    UsuarioModel findByCpf(String cpf);
}


