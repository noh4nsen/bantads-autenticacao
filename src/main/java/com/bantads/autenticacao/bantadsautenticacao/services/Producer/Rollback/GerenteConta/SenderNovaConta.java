package com.bantads.autenticacao.bantadsautenticacao.services.Producer.Rollback.GerenteConta;

import java.util.UUID;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SenderNovaConta {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue queueRollbackNovaConta;

    public void send(UUID saga) {
        this.template.convertAndSend(this.queueRollbackNovaConta.getName(), saga);
    }
}
