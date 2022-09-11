package com.bantads.autenticacao.bantadsautenticacao.services.Consumer;

import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.autenticacao.bantadsautenticacao.data.UsuarioRepository;

@Component
public class ConsumerRollback {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @RabbitListener(queues = "autocadastro-autenticacao-rollback")
    public void receive(@Payload String json) {
        try {
            UUID saga = UUID.fromString(json);
            usuarioRepository.deleteBySaga(saga);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
