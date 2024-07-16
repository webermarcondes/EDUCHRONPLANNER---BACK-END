package com.projetotcs.tcsbackend.repository;

import com.projetotcs.tcsbackend.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    UsuarioModel findByCpf(String cpf);
    UsuarioModel findByEmail(String email);
    UsuarioModel findBySenha(String senha);
}


