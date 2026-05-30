package br.com.lumina.domain.usuario;

import br.com.lumina.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registrar(UsuarioRegisterDTO dto) {
        // validar senha
        if (dto.getSenha() == null || dto.getConfirmarSenha() == null) {
            throw new RuntimeException("Preencha os campos de senha");
        }

        if (!dto.getSenha().equals(dto.getConfirmarSenha())) {
            throw new RuntimeException("Senhas não conferem");
        }

        // verificar se email existe
        if (repository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        // verificar se nome existe
        if (repository.existsByNome(dto.getNomeCompleto())) {
            throw new RuntimeException("Esse nome já existe");
        }

        // mapear dto para entity
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNomeCompleto());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setRole(Role.ALUNO);
        usuario.setDataCriacao(LocalDate.now());

        // salvar no banco
        repository.save(usuario);

    }
}
