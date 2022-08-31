package com.bantads.autenticacao.bantadsautenticacao;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bantads.autenticacao.bantadsautenticacao.services.email.MailSenderService;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableRabbit
@SpringBootApplication
public class BantadsAutenticacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BantadsAutenticacaoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public MailSenderService mailSenderS() {
		return new MailSenderService();
	}
}
