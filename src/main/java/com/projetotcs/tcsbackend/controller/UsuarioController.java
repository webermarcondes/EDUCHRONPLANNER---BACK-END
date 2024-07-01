package com.projetotcs.tcsbackend.controller;

import com.projetotcs.tcsbackend.exceptions.StatusInativoException;
import com.projetotcs.tcsbackend.model.UsuarioModel;
import com.projetotcs.tcsbackend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @PatchMapping(value="/atualizarstatus/{id}")
    public UsuarioModel updateUsuarioStatus(@PathVariable(value="id") Long id, @RequestBody String status) {

        return service.updateUsuarioStatus(id, status);

    }

    @PostMapping("/login/")
    public ResponseEntity<String> login(@RequestBody UsuarioModel usuario) {
        UsuarioModel existingUser = service.findByEmail(usuario.getEmail());
        if (existingUser != null && existingUser.getSenha().equals(usuario.getSenha())) {
            return ResponseEntity.ok("Usuario Logado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha invalidos");
        }
    }


}
