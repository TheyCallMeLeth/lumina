package br.com.lumina.controller;

import br.com.lumina.domain.usuario.UsuarioRegisterDTO;
import br.com.lumina.domain.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsuarioRegisterDTO dto, Model model) {
        try {
            service.registrar(dto);

            return "redirect:/login";

        } catch (RuntimeException e) {

            model.addAttribute("erro", e.getMessage());

            return "auth/register";
        }
    }

}
