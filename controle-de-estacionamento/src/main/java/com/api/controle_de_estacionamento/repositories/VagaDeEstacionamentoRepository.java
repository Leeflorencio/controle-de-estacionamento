package com.api.controle_de_estacionamento.repositories;

import com.api.controle_de_estacionamento.models.VagaDeEstacionamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VagaDeEstacionamentoRepository extends JpaRepository<VagaDeEstacionamentoModel, UUID> {



}
