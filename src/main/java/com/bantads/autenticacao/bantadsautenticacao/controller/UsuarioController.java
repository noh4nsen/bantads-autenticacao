package com.bantads.autenticacao.bantadsautenticacao.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.autenticacao.bantadsautenticacao.DTOs.UsuarioDTO;
import com.bantads.autenticacao.bantadsautenticacao.DTOs.UsuarioResponseDTO;
import com.bantads.autenticacao.bantadsautenticacao.data.UsuarioRepository;
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

    @PostMapping("/login")
    ResponseEntity<UsuarioResponseDTO> login(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.login(usuarioDTO.getEmail(), Security.hash(usuarioDTO.getSenha()));
        if (usuario != null) {
            UsuarioResponseDTO response = mapper.map(usuario, UsuarioResponseDTO.class);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping("/health")
    public String getUsuarios() {
        return "ʕ·͡ᴥ·ʔ";
    }
}
