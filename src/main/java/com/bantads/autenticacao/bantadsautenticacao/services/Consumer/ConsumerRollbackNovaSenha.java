package com.bantads.autenticacao.bantadsautenticacao.services.Consumer;

import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.autenticacao.bantadsautenticacao.data.UsuarioRepository;
import com.bantads.autenticacao.bantadsautenticacao.model.Usuario;
import com.bantads.autenticacao.bantadsautenticacao.services.email.MailSenderService;

@Component
public class ConsumerRollbackNovaSenha {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MailSenderService mailSenderService;

    @RabbitListener(queues = "nova-senha-rollback")
    public void receive(@Payload String json) {
        try {
            UUID saga = UUID.fromString(json);
            Usuario usuario = usuarioRepository.findBySaga(saga);
            if (usuario != null){
                usuario.setSenha(null);
                usuario.setAtivo(false);
                usuarioRepository.save(usuario);
            }
            sendMail(usuario);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void sendMail(Usuario usuario) {
        String to = usuario.getEmail();
        String subject = "Erro ao cadastrar nova conta";
        String body = "Ocorreu um erro ao cadastrar sua conta.\n" + "Em breve um gerente fará a análise da sua solicitação novamente.";
        mailSenderService.sendMail(to, subject, body);
    }
}
