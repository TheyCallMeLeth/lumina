package br.com.lumina.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    boolean existsByNome(String nome);
    Optional<Usuario> findByEmail(String email);
}
