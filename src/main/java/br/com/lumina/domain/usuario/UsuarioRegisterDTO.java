package br.com.lumina.domain.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRegisterDTO {
    private String nomeCompleto;
    private String email;
    private String senha;
    private String confirmarSenha;
}