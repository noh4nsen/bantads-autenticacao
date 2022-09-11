package com.bantads.autenticacao.bantadsautenticacao.services.Consumer;

import java.util.Optional;
import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.autenticacao.bantadsautenticacao.data.UsuarioRepository;
import com.bantads.autenticacao.bantadsautenticacao.model.Usuario;
import com.bantads.autenticacao.bantadsautenticacao.services.Producer.Rollback.Gerente.SenderRollbackDeleteGerente;

@Component
public class ConsumerDeleteUsuario {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SenderRollbackDeleteGerente senderRollbackDeleteGerente;

    @RabbitListener(queues = "delete-usuario")
    public void receive(@Payload String json){
        try {
            UUID id = UUID.fromString(json);
            Optional<Usuario> usuarioOp = usuarioRepository.findById(id);
            Usuario usuario = usuarioOp.get();
            usuario.setAtivo(false);
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            System.out.println(e);
            UUID id = UUID.fromString(json);
            senderRollbackDeleteGerente.send(id);
        }
    }
}
