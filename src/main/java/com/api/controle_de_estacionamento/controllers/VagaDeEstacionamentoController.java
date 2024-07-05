package com.api.controle_de_estacionamento.controllers;

import com.api.controle_de_estacionamento.dtos.VagaDeEstacionamentoDto;
import com.api.controle_de_estacionamento.models.VagaDeEstacionamentoModel;
import com.api.controle_de_estacionamento.services.VagaDeEstacionamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/vaga-estacionamento")
public class VagaDeEstacionamentoController {

    @Autowired
    VagaDeEstacionamentoService vagaDeEstacionamentoService;

    @PostMapping
    public ResponseEntity<Object> salvarVaga(@RequestBody @Valid VagaDeEstacionamentoDto vagaDeEstacionamentoDto){
        var vagaDeEstacionamentoModel = new VagaDeEstacionamentoModel();
        if (vagaDeEstacionamentoService.existsByPlacaDoCarro(vagaDeEstacionamentoDto.getPlacaDoCarro())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("A placa do carro já está em uso");
        }
        if (vagaDeEstacionamentoService.existsByNumVagaEstacionamento(vagaDeEstacionamentoDto.getNumVagaEstacionamento())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O número da vaga já está em uso");
        }
        if(vagaDeEstacionamentoService.existsByApartamentoAndBloco(vagaDeEstacionamentoDto.getApartamento(), vagaDeEstacionamentoDto.getBloco())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Vaga já contém regstro com o apartamento e o bloco informados");
        }

        BeanUtils.copyProperties(vagaDeEstacionamentoDto, vagaDeEstacionamentoModel);
        vagaDeEstacionamentoModel.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(vagaDeEstacionamentoService.salvar(vagaDeEstacionamentoModel));
    }

    @GetMapping
    public ResponseEntity<Page<VagaDeEstacionamentoModel>> listarTodasAsVagas(@PageableDefault( page = 0, sort = "id", size = 10,
                                                                               direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(vagaDeEstacionamentoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarVagaPorId(@PathVariable(value = "id") UUID id){
        Optional<VagaDeEstacionamentoModel> vagaDeEstacionamentoOptional = vagaDeEstacionamentoService.findById(id);
        if (!vagaDeEstacionamentoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não localizada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(vagaDeEstacionamentoOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluirVaga(@PathVariable(value = "id") UUID id){
        Optional<VagaDeEstacionamentoModel> vagaDeEstacionamentoOptional = vagaDeEstacionamentoService.findById(id);
        if (!vagaDeEstacionamentoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não localizada");
        }
        vagaDeEstacionamentoService.deletar(vagaDeEstacionamentoOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Vaga excluída com sucesso");
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarVaga(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid VagaDeEstacionamentoDto vagaDeEstacionamentoDto){
        Optional<VagaDeEstacionamentoModel> vagaDeEstacionamentoOptional = vagaDeEstacionamentoService.findById(id);
        if (!vagaDeEstacionamentoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não localizada");
        }
        var vagaDeEstacionamentoModel = vagaDeEstacionamentoOptional.get();

        vagaDeEstacionamentoModel.setNumVagaEstacionamento(vagaDeEstacionamentoDto.getNumVagaEstacionamento());
        vagaDeEstacionamentoModel.setPlacaDoCarro(vagaDeEstacionamentoDto.getPlacaDoCarro());
        vagaDeEstacionamentoModel.setMarcaDoCarro(vagaDeEstacionamentoDto.getMarcaDoCarro());
        vagaDeEstacionamentoModel.setModeloDoCarro(vagaDeEstacionamentoDto.getModeloDoCarro());
        vagaDeEstacionamentoModel.setCorDoCarro(vagaDeEstacionamentoDto.getCorDoCarro());
        vagaDeEstacionamentoModel.setNomeDoResponsavel(vagaDeEstacionamentoDto.getNomeDoResponsavel());
        vagaDeEstacionamentoModel.setApartamento(vagaDeEstacionamentoDto.getApartamento());
        vagaDeEstacionamentoModel.setBloco(vagaDeEstacionamentoDto.getBloco());

        vagaDeEstacionamentoModel.setDataRegistro(vagaDeEstacionamentoOptional.get().getDataRegistro());

        return ResponseEntity.status(HttpStatus.OK).body(vagaDeEstacionamentoService.salvar(vagaDeEstacionamentoModel));
    }



}
