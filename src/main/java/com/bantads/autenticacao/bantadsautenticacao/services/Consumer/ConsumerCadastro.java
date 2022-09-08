package com.bantads.autenticacao.bantadsautenticacao.services.Consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.autenticacao.bantadsautenticacao.data.UsuarioRepository;
import com.bantads.autenticacao.bantadsautenticacao.model.Usuario;
import com.bantads.autenticacao.bantadsautenticacao.services.Producer.Cliente.SenderCliente;
import com.bantads.autenticacao.bantadsautenticacao.services.Producer.Gerente.SenderGerente;
import com.bantads.autenticacao.bantadsautenticacao.services.Producer.GerenteConta.SenderGerenteConta;
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
            usuario.setSenha(Security.hash(password));
            usuarioRepository.save(usuario);
            sendMail(usuario, password);
        } catch (Exception e) {
            System.out.println(e);
            Usuario usuario = objectMapper.readValue(json, Usuario.class);
            senderGerente.send(usuario.getSaga());
            senderCliente.send(usuario.getSaga());
            senderGerenteConta.send(usuario.getSaga());
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