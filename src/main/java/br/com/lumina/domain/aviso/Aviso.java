package br.com.lumina.domain.aviso;

import br.com.lumina.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "avisos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aviso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate dataCriacao;
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;
    @Enumerated(EnumType.STRING)
    private Publico publicoAlvo;
    @ManyToOne()
    @JoinColumn(name = "usuario_id")
    private Usuario criadoPor;
}
