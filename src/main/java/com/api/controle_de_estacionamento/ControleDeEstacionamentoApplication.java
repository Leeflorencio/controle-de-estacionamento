package com.api.controle_de_estacionamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ControleDeEstacionamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleDeEstacionamentoApplication.class, args);
	}

}
