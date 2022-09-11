package com.bantads.autenticacao.bantadsautenticacao.DTOs;

import java.util.UUID;

public class GerarSenhaDTO {
    private UUID idExternoUsuario;
    private UUID saga;

    public GerarSenhaDTO() {
    }

    public GerarSenhaDTO(UUID idExternoUsuario, UUID saga) {
        this.idExternoUsuario = idExternoUsuario;
        this.saga = saga;
    }

    public UUID getIdExternoUsuario() {
        return idExternoUsuario;
    }

    public void setIdExternoUsuario(UUID idExternoUsuario) {
        this.idExternoUsuario = idExternoUsuario;
    }

    public UUID getSaga() {
        return saga;
    }

    public void setSaga(UUID saga) {
        this.saga = saga;
    }

}

