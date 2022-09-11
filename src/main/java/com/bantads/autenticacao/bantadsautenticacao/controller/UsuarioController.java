package com.bantads.autenticacao.bantadsautenticacao.controller;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bantads.autenticacao.bantadsautenticacao.DTOs.UsuarioDTO;
import com.bantads.autenticacao.bantadsautenticacao.DTOs.UsuarioResponseDTO;
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

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getUsuario(@PathVariable UUID id) {
        try {
            Optional<Usuario> usuarioOp = usuarioRepository.findById(id);

            if (usuarioOp.isPresent()) {
                UsuarioResponseDTO gerente = mapper.map(usuarioOp.get(), UsuarioResponseDTO.class);
                return ResponseEntity.ok(gerente);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/login")
    ResponseEntity<UsuarioResponseDTO> login(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            Usuario usuario = usuarioRepository.login(usuarioDTO.getEmail(), Security.hash(usuarioDTO.getSenha()));
            if (usuario != null) {
                UsuarioResponseDTO response = mapper.map(usuario, UsuarioResponseDTO.class);
                return ResponseEntity.ok().body(response);
            } else {
                return ResponseEntity.status(401).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/admin")
    public ResponseEntity<String> createAdmin() {
        try {
            Usuario usuarioOp = usuarioRepository.findByEmail("admin");
            if(usuarioOp != null)
                return ResponseEntity.status(409).build();

            Usuario usuario = new Usuario(UUID.randomUUID(), "admin", Security.hash("admin"), TipoUsuario.Administrador, true, UUID.randomUUID());
            usuarioRepository.save(usuario);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/health")
    public String getUsuarios() {
        return "ʕ·͡ᴥ·ʔ";
    }
}
