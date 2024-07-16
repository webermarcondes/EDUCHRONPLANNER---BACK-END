package com.projetotcs.tcsbackend.controller;

import com.projetotcs.tcsbackend.requests.RequestGerarCronograma;
import com.projetotcs.tcsbackend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;


@RestController
@RequestMapping("/api/gerador-cronograma")
public class GeradorCronogramaController {

    @Autowired
    GeradorCronogramaService service;

    @PostMapping(value="/gerar-cronograma")
    public ResponseEntity<byte[]> gerarCronograma(@RequestBody RequestGerarCronograma requestGerarCronograma) throws IOException {

        return service.gerarCronograma(requestGerarCronograma);
    }
}
