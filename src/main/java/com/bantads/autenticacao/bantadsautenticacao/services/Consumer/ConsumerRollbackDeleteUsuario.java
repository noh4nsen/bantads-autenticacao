package com.bantads.autenticacao.bantadsautenticacao.services.Consumer;

import java.util.Optional;
import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.autenticacao.bantadsautenticacao.data.UsuarioRepository;
import com.bantads.autenticacao.bantadsautenticacao.model.Usuario;

@Component
public class ConsumerRollbackDeleteUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @RabbitListener(queues = "delete-usuario-rollback")
    public void receive(@Payload String json){
        try {
            UUID id = UUID.fromString(json);
            Optional<Usuario> usuarioOp = usuarioRepository.findById(id);
            Usuario usuario = usuarioOp.get();
            usuario.setAtivo(true);
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
