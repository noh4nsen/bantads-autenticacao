package com.bantads.autenticacao.bantadsautenticacao.DTOs;

import java.io.Serializable;

import com.bantads.autenticacao.bantadsautenticacao.model.TipoUsuario;

public class NovoUsuarioDTO implements Serializable {
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;

    public NovoUsuarioDTO() {
        super();
    }

    public NovoUsuarioDTO(String email, String senha, TipoUsuario tipoUsuario) {
        super();
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
