package com.projetotcs.tcsbackend.controller;


import com.projetotcs.tcsbackend.model.Usuario;
import com.projetotcs.tcsbackend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Usuario pode ser inativo no PutMapping já implementado ou seria em outro método?
 */

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @GetMapping(value="/")
    public List<Usuario> getUsuarios() {
        return service.findAll();
    }

    @GetMapping(value="/{id}")
    public Usuario getUsuarioById(@PathVariable(value="id") Long id) {
        return service.findById(id);
    }


    @PostMapping(value="/")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {

        return new ResponseEntity<>(service.create(usuario), HttpStatus.CREATED);
    }

    @PutMapping(value="/{id}")
    public Usuario updateUsuario(@RequestBody Usuario usuario, @PathVariable(value="id") Long id) {
        return service.update(usuario, id);
    }


    /*@DeleteMapping(value="/{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable(value="id") Long id) {
        service.delete(id);

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }*/


}
