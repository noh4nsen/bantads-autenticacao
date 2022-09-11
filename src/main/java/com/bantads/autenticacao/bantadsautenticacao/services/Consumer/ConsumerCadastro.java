package com.bantads.autenticacao.bantadsautenticacao.services.Consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.autenticacao.bantadsautenticacao.data.UsuarioRepository;
import com.bantads.autenticacao.bantadsautenticacao.model.TipoUsuario;
import com.bantads.autenticacao.bantadsautenticacao.model.Usuario;
import com.bantads.autenticacao.bantadsautenticacao.services.Producer.Rollback.Cliente.SenderCliente;
import com.bantads.autenticacao.bantadsautenticacao.services.Producer.Rollback.Gerente.SenderGerente;
import com.bantads.autenticacao.bantadsautenticacao.services.Producer.Rollback.GerenteConta.SenderGerenteConta;
import com.bantads.autenticacao.bantadsautenticacao.services.email.MailSenderService;
import com.bantads.autenticacao.bantadsautenticacao.tools.Security;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConsumerCadastro {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private SenderCliente senderCliente;

    @Autowired
    private SenderGerente senderGerente;

    @Autowired
    private SenderGerenteConta senderGerenteConta;

    @RabbitListener(queues = "autocadastro-autenticacao")
    public void receive(@Payload String json) throws JsonProcessingException {
        try {
            Usuario usuario = objectMapper.readValue(json, Usuario.class);
            String password = createPassword();
            usuario.setAtivo(false);
            if (usuario.getTipoUsuario() == TipoUsuario.Gerente) {
                usuario.setSenha(Security.hash(password));      
                usuario.setAtivo(true);          
                sendMail(usuario, password);
            }
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            System.out.println(e);
            Usuario usuario = objectMapper.readValue(json, Usuario.class);
            if (usuario.getTipoUsuario() == TipoUsuario.Gerente) {
                senderGerente.send(usuario.getSaga());
                senderGerenteConta.send(usuario.getSaga());
            }
            if (usuario.getTipoUsuario() == TipoUsuario.Cliente) {
                senderCliente.send(usuario.getSaga());
            }
        }
    }

    private String createPassword() {
        String password = "";
        for (int i = 0; i < 8; i++) {
            password += (char) (Math.random() * 26 + 97);
        }
        return password;
    }

    private void sendMail(Usuario usuario, String senha) {
        String to = usuario.getEmail();
        String subject = "Bantads " + usuario.getTipoUsuario() + " - Cadastro realizado com sucesso";
        String body = "Seu cadastro foi realizado com sucesso.\n" + "Sua senha é: " + senha;
        mailSenderService.sendMail(to, subject, body);
    }
}
