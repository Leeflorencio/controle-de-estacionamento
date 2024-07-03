package com.api.controle_de_estacionamento.controllers;

import com.api.controle_de_estacionamento.dtos.VagaDeEstacionamentoDto;
import com.api.controle_de_estacionamento.models.VagaDeEstacionamentoModel;
import com.api.controle_de_estacionamento.services.VagaDeEstacionamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/vaga-estacionamento")
public class VagaDeEstacionamentoController {

    @Autowired
    VagaDeEstacionamentoService vagaDeEstacionamentoService;

    @PostMapping
    public ResponseEntity<Object> salvarVaga(@RequestBody @Valid VagaDeEstacionamentoDto vagaDeEstacionamentoDto){
        var vagaDeEstacionamentoModel = new VagaDeEstacionamentoModel();

        BeanUtils.copyProperties(vagaDeEstacionamentoDto, vagaDeEstacionamentoModel);
        vagaDeEstacionamentoModel.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(vagaDeEstacionamentoService.salvar(vagaDeEstacionamentoModel));
    }
}
