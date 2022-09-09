package com.bantads.autenticacao.bantadsautenticacao.services.Producer.Rollback.Gerente;

import java.util.UUID;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class SenderGerente {
    @Autowired
    public RabbitTemplate template;

    @Autowired
    private Queue queueRollbackAutocadastroGerente;

    public void send(UUID saga) throws JsonProcessingException {
        this.template.convertAndSend(this.queueRollbackAutocadastroGerente.getName(), saga);
    }
}
