package com.bantads.autenticacao.bantadsautenticacao.services.Producer.Rollback.Cliente;

import java.util.UUID;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SenderAprovacao {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queueRollbackAprovacao;

    public void send(UUID saga) {
        this.template.convertAndSend(this.queueRollbackAprovacao.getName(), saga);
    }
}
