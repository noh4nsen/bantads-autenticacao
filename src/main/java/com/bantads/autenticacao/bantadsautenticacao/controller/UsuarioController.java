package com.bantads.autenticacao.bantadsautenticacao.controller;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.autenticacao.bantadsautenticacao.DTOs.NovoUsuarioDTO;
import com.bantads.autenticacao.bantadsautenticacao.DTOs.UsuarioDTO;
import com.bantads.autenticacao.bantadsautenticacao.data.UsuarioRepository;
import com.bantads.autenticacao.bantadsautenticacao.model.TipoUsuario;
import com.bantads.autenticacao.bantadsautenticacao.model.Usuario;
import com.bantads.autenticacao.bantadsautenticacao.tools.Security;

@CrossOrigin
@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper mapper;


    // ROTA TEMPORARIA, DEVE SER REMOVIDA DEPOIS DA IMPLEMENTACAO EM SAGA
    @PostMapping("/cadastrar")
    ResponseEntity<Usuario> cadastrar(@RequestBody NovoUsuarioDTO NovousuarioDTO) {
        Usuario usuario = mapper.map(NovousuarioDTO, Usuario.class);
        usuario.setId(UUID.randomUUID());
        usuario.setSenha(Security.hash(usuario.getSenha()));
        
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/login")
    ResponseEntity<Usuario> login(@RequestBody UsuarioDTO usuarioDTO) {

        if (usuarioDTO.getEmail().equals(usuarioDTO.getSenha())) {
            Usuario usuario = new Usuario(UUID.randomUUID(), usuarioDTO.getEmail(), usuarioDTO.getSenha(), TipoUsuario.Administrador);
            return ResponseEntity.ok().body(usuario);
        } else {
            return ResponseEntity.status(401).build();
        }

    }

    @GetMapping("/health")
    public String getUsuarios() {
        return "ʕ·͡ᴥ·ʔ";
    }
}
