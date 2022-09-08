package com.bantads.autenticacao.bantadsautenticacao.services.Producer.GerenteConta;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class SenderGerenteConta {
    @Autowired
    public RabbitTemplate template;

    @Autowired
    private Queue queueRollbackAutocadastroGerenteConta;

    public void send(String json) throws JsonProcessingException {
        this.template.convertAndSend(this.queueRollbackAutocadastroGerenteConta.getName(), json);
    }
}
