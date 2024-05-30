package com.projetotcs.tcsbackend.controller;


import com.projetotcs.tcsbackend.exceptions.StatusInativoException;
import com.projetotcs.tcsbackend.model.UsuarioModel;
import com.projetotcs.tcsbackend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Fazer validação no update e no create para que não sejá possível cadastrar um usuário com um CPF
que já está persistido, ou atualizar o CPF de um usuário para um que já está persistido;
 */
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService service;


    @GetMapping(value="/")
    public List<UsuarioModel> getUsuarios() {
        return service.findAll();
    }

    @GetMapping(value="/{id}")
    public UsuarioModel getUsuarioById(@PathVariable(value="id") Long id) {
        return service.findById(id);
    }

    @GetMapping(value="/buscaporcpf/")
    public UsuarioModel getUsuarioByCpf(@RequestBody String cpf) {
        return service.findByCpf(cpf);
    }

    @PostMapping(value="/")
    public ResponseEntity<UsuarioModel> createUsuario(@RequestBody UsuarioModel usuario) {

        return new ResponseEntity<>(service.create(usuario), HttpStatus.CREATED);
    }

    @PutMapping(value="/{id}")
    public UsuarioModel updateUsuario(@RequestBody UsuarioModel usuario, @PathVariable(value="id") Long id) throws StatusInativoException {
        return service.update(usuario, id);
    }

    //Método pra desativar ou ativar  o Usuário
    @PatchMapping(value="/atualizarstatus/{id}")
    public UsuarioModel updateUsuarioStatus(@PathVariable(value="id") Long id, @RequestBody String status) {

        return service.updateUsuarioStatus(id, status);

    }


}
