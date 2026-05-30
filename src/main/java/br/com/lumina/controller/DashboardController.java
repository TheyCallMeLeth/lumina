package br.com.lumina.controller;

import br.com.lumina.domain.usuario.Usuario;
import br.com.lumina.domain.usuario.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final UsuarioRepository repository;

    public DashboardController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/dashboard")
    public String dashboard(
            Authentication authentication,
            Model model) {

        String email = authentication.getName();

        Usuario usuario = repository
                .findByEmail(email)
                .orElseThrow();

        model.addAttribute("usuario", usuario);

        return "dashboard/index";
    }
}
