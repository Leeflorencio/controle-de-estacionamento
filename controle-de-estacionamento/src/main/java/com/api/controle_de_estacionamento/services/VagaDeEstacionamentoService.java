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


}
