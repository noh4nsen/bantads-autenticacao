package com.bantads.autenticacao.bantadsautenticacao.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    
    @GetMapping("/health")
    public String getUsuarios() {
        return "ʕ·͡ᴥ·ʔ";
    }
}
