package com.bantads.autenticacao.bantadsautenticacao.services.Producer.GerenteConta;

import java.util.UUID;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SenderGerenteConta {
    @Autowired
    public RabbitTemplate template;

    @Autowired
    private Queue queueRollbackAutocadastroGerenteConta;

    @Autowired
    private ObjectMapper objectMapper;

    public void send(UUID saga) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(saga);
        this.template.convertAndSend(this.queueRollbackAutocadastroGerenteConta.getName(), json);
    }
}