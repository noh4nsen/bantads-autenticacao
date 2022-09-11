package com.bantads.autenticacao.bantadsautenticacao.services.Producer.Rollback.Gerente;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderRollbackDeleteGerenteConfig {
    @Value("delete-gerente-rollback")
    private String queueRollbackDeleteGerente;

    @Bean
    public Queue queueRollbackDeleteGerente() {
        return new Queue(queueRollbackDeleteGerente);
    }
}
