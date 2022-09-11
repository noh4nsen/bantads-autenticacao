package com.bantads.autenticacao.bantadsautenticacao.services.Producer.Rollback.GerenteConta;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SenderNovaContaConfig {
    @Value("aprovacao-rollback")
    private String queueRollbackNovaConta;

    @Bean
    public Queue queueRollbackNovaConta() {
        return new Queue(queueRollbackNovaConta);
    }
}
