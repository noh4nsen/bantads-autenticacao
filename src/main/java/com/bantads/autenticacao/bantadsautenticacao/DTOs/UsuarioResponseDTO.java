package com.bantads.autenticacao.bantadsautenticacao.DTOs;

import java.io.Serializable;
import java.util.UUID;

import com.bantads.autenticacao.bantadsautenticacao.model.TipoUsuario;

public class UsuarioResponseDTO implements Serializable {
    private UUID id;
    private String email;
    private TipoUsuario tipoUsuario;

    public UsuarioResponseDTO() {
        super();
    }

    public UsuarioResponseDTO(UUID id, String email, TipoUsuario tipoUsuario) {
        super();
        this.id = id;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
