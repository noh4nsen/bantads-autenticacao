package com.bantads.autenticacao.bantadsautenticacao.services.Producer.GerenteConta;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderGerenteContaConfig {
    @Value("rollback-autocadastro-gerente-conta")
    private String queueRollbackAutocadastroGerenteConta;

    @Bean
    public Queue queueRollbackAutocadastroGerenteConta(){
        return new Queue(queueRollbackAutocadastroGerenteConta);
    }
}
