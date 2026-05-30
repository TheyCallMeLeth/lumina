package br.com.lumina.domain.aviso;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvisoCreateDTO {
    private String titulo;
    private String descricao;
    private Prioridade prioridade;
    private Publico publicoAlvo;
}
