package com.projetotcs.tcsbackend.services;


import com.projetotcs.tcsbackend.enums.Status;
import com.projetotcs.tcsbackend.exceptions.StatusInativoException;
import com.projetotcs.tcsbackend.model.UsuarioModel;
import com.projetotcs.tcsbackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository repository;

    public List<UsuarioModel> findAll() {

        List<UsuarioModel> usuarios = repository.findAll();

        if(usuarios.isEmpty()) {
            throw new ResourceNotFoundException("Não há usuários cadastrados");
        }

        return usuarios;
    }

    public UsuarioModel findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de usuário com o ID informado"));

    }

    public UsuarioModel findByCpf(String cpf) {

        JSONObject jsonString = new JSONObject(cpf);

        UsuarioModel usuario = repository.findByCpf(jsonString.getString("cpf"));

        if (usuario == null) {
            throw new ResourceNotFoundException("Não há registro de usuário com o CPF informado");
        }

        return usuario;
    }

    public UsuarioModel create(UsuarioModel usuario) {
        return repository.save(usuario);
    }

    public UsuarioModel update(UsuarioModel usuario, Long id) throws StatusInativoException {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de usuário com o ID informado para atualizar informações"));

        if (entity.getStatus() == Status.ATIVO) {
            entity.setEmail(usuario.getEmail());
            entity.setNome(usuario.getNome());
            entity.setSenha(usuario.getSenha());
            entity.setCpf(usuario.getCpf());

            repository.save(entity);

            return usuario;
        }

        throw new StatusInativoException("Não é possível atualizar dados de um usuário INATIVO");
    }

    public UsuarioModel updateUsuarioStatus(Long id, String status) {

        JSONObject jsonString = new JSONObject(status);

        UsuarioModel usuario = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não há registro de usuário com o ID informado"));


        if (jsonString.getString("status").equals("ATIVO")) {
            usuario.setStatus(Status.ATIVO);
        }
        else {
            usuario.setStatus(Status.INATIVO);
        }

        repository.save(usuario);

        return usuario;
    }


}
