package br.com.lumina.domain.aviso;

import br.com.lumina.domain.usuario.Usuario;
import br.com.lumina.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AvisoService {

    @Autowired
    private AvisoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void criarAnuncio(AvisoCreateDTO dto) {
        // validando os campos
        if (dto.getTitulo() == null || dto.getTitulo().isBlank()) {
            throw new RuntimeException("Preencha este campo");
        }
        if (dto.getDescricao() == null || dto.getDescricao().isBlank()) {
            throw new RuntimeException("Preencha este campo");
        }
        if (dto.getPrioridade() == null) {
            throw new RuntimeException("Preencha este campo");
        }
        if (dto.getPublicoAlvo() == null) {
            throw new RuntimeException("Preencha este campo");
        }

        // encontrando usuario logado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String email = auth.getName();

        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // convertendo para entidade
        Aviso aviso = new Aviso();
        aviso.setTitulo(dto.getTitulo());
        aviso.setDescricao(dto.getDescricao());
        aviso.setPrioridade(dto.getPrioridade());
        aviso.setPublicoAlvo(dto.getPublicoAlvo());
        aviso.setDataCriacao(LocalDate.now());
        aviso.setCriadoPor(usuario);

        repository.save(aviso);
    }

    public List<Aviso> listarAvisos() {
        return repository.findAllByOrderByDataCriacaoDesc();
    }

}
