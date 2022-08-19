package com.bantads.autenticacao.bantadsautenticacao;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BantadsAutenticacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BantadsAutenticacaoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
