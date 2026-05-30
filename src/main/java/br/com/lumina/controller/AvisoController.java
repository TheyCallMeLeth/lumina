package br.com.lumina.controller;

import br.com.lumina.domain.aviso.AvisoCreateDTO;
import br.com.lumina.domain.aviso.AvisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AvisoController {

    @Autowired
    private AvisoService service;

    @GetMapping("/avisos")
    public String avisos(Model model){
        model.addAttribute("avisos", service.listarAvisos());
        return "aviso/feed";
    }

    @GetMapping("/avisos/novo")
    public String novoAviso(){
        return "aviso/novo";
    }

    @PostMapping("/avisos/novo")
    public String criarAviso(@ModelAttribute AvisoCreateDTO dto, Model model){
        try {
            service.criarAnuncio(dto);

            return "redirect:/avisos";

        } catch (RuntimeException e) {

            model.addAttribute("erro", e.getMessage());

            return "avisos/novo";
        }

    }
}
