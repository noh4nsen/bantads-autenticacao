package com.bantads.autenticacao.bantadsautenticacao.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    private UUID id;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
    private boolean ativo;
    private UUID saga;

    public Usuario() {
        super();
    }

    public Usuario(UUID id, String email, String senha, TipoUsuario tipoUsuario, boolean ativo, UUID saga) {
        super();
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.ativo = ativo;
        this.saga = saga;
    }

    @Id
    @Column(name = "id")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "senha")
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Column(name = "tipo_usuario")
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Column(name = "saga")
    public UUID getSaga() {
        return saga;
    }

    public void setSaga(UUID saga) {
        this.saga = saga;
    }

    @Column(name = "ativo")
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
