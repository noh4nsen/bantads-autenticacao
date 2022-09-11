package com.bantads.autenticacao.bantadsautenticacao.data;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bantads.autenticacao.bantadsautenticacao.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    public Usuario findByEmail(String email);
    public Usuario findBySaga(UUID saga);

    @Query("from Usuario where email = :email and senha = :senha and ativo = true")
    public Usuario login(@Param("email") String email,
            @Param("senha") String senha);

    @Transactional
    public Long deleteBySaga(UUID saga);
}
