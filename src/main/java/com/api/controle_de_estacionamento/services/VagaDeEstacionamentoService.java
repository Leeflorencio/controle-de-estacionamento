package com.api.controle_de_estacionamento.services;

import com.api.controle_de_estacionamento.models.VagaDeEstacionamentoModel;
import com.api.controle_de_estacionamento.repositories.VagaDeEstacionamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VagaDeEstacionamentoService {

    @Autowired
    VagaDeEstacionamentoRepository vagaDeEstacionamentoRepository;

    @Transactional
    public VagaDeEstacionamentoModel salvar(VagaDeEstacionamentoModel vagaDeEstacionamentoModel) {
        return vagaDeEstacionamentoRepository.save(vagaDeEstacionamentoModel);
    }


    public boolean existsByPlacaDoCarro(String placaDoCarro) {
        return vagaDeEstacionamentoRepository.existsByPlacaDoCarro(placaDoCarro);
    }

    public boolean existsByNumVagaEstacionamento(String numVagaEstacionamento) {
        return vagaDeEstacionamentoRepository.existsByNumVagaEstacionamento(numVagaEstacionamento);
    }

    public boolean existsByApartamentoAndBloco(String apartamento, String bloco) {
        return vagaDeEstacionamentoRepository.existsByApartamentoAndBloco(apartamento, bloco);
    }
}
