package com.bantads.autenticacao.bantadsautenticacao.services.Producer.Rollback.Gerente;

import java.util.UUID;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SenderRollbackDeleteGerente {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queueRollbackDeleteGerente;

    public void send(UUID id) {
        this.template.convertAndSend(this.queueRollbackDeleteGerente.getName(), id);
    }
}
