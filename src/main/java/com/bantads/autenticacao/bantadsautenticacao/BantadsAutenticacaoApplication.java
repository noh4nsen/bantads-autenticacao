package com.bantads.autenticacao.bantadsautenticacao;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bantads.autenticacao.bantadsautenticacao.services.Producer.Rollback.Cliente.SenderCliente;
import com.bantads.autenticacao.bantadsautenticacao.services.Producer.Rollback.Gerente.SenderGerente;
import com.bantads.autenticacao.bantadsautenticacao.services.Producer.Rollback.GerenteConta.SenderGerenteConta;
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

	@Bean
	public SenderCliente senderC() {
		return new SenderCliente();
	}

	@Bean
	public SenderGerente senderG() {
		return new SenderGerente();
	}

	@Bean
	public SenderGerenteConta senderGC() {
		return new SenderGerenteConta();
	}
}
