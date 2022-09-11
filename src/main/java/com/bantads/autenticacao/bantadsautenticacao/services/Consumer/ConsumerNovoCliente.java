package com.bantads.autenticacao.bantadsautenticacao.services.Consumer;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.bantads.autenticacao.bantadsautenticacao.DTOs.GerarSenhaDTO;
import com.bantads.autenticacao.bantadsautenticacao.data.UsuarioRepository;
import com.bantads.autenticacao.bantadsautenticacao.model.Usuario;
import com.bantads.autenticacao.bantadsautenticacao.services.Producer.Rollback.Cliente.SenderAprovacao;
import com.bantads.autenticacao.bantadsautenticacao.services.Producer.Rollback.GerenteConta.SenderNovaConta;
import com.bantads.autenticacao.bantadsautenticacao.services.email.MailSenderService;
import com.bantads.autenticacao.bantadsautenticacao.tools.Security;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConsumerNovoCliente {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private SenderAprovacao senderAprovacao;

    @Autowired
    private SenderNovaConta senderNovaConta;

    @RabbitListener(queues = "gerar-senha-cliente")
    public void receive(@Payload String json) throws JsonMappingException, JsonProcessingException {
        try {
            GerarSenhaDTO gerarSenhaDTO = objectMapper.readValue(json, GerarSenhaDTO.class);
            String password = createPassword();
            Optional<Usuario> usuarioOp = usuarioRepository.findById(gerarSenhaDTO.getIdExternoUsuario());
            Usuario usuario = usuarioOp.get();
            usuario.setSenha(Security.hash(password));
            usuario.setAtivo(true);
            usuario.setSaga(gerarSenhaDTO.getSaga());
            usuarioRepository.save(usuario);
            sendMail(usuario, password);
        } catch (Exception e) {
            System.out.println(e);
            GerarSenhaDTO gerarSenhaDTO = objectMapper.readValue(json, GerarSenhaDTO.class);
            senderAprovacao.send(gerarSenhaDTO.getSaga());
            senderNovaConta.send(gerarSenhaDTO.getSaga());
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
        String subject = "Olá " + usuario.getEmail() + " - Cadastro realizado com sucesso";
        String body = "Seu cadastro foi realizado com sucesso.\n" + "Sua senha é: " + senha;
        mailSenderService.sendMail(to, subject, body);
    }
}
