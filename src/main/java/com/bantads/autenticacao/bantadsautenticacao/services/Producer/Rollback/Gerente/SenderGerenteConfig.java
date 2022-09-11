package com.bantads.autenticacao.bantadsautenticacao.services.Producer.Rollback.Gerente;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderGerenteConfig {
    @Value("autocadastro-gerente-rollback")
    private String queueRollbackAutocadastroGerente;

    @Bean
    public Queue queueRollbackAutocadastroGerente() {
        return new Queue(queueRollbackAutocadastroGerente);
    }
}
