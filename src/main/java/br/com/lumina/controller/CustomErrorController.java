package br.com.lumina.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.webmvc.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int code = Integer.parseInt(status.toString());

            if (code == 401) return "error/401";
            if (code == 403) return "error/403";
            if (code == 404) return "error/404";
            if (code == 500) return "error/500";
        }

        return "error/500";
    }

    @GetMapping("/erro-teste")
    public String erroTeste() {
        throw new RuntimeException("Erro forçado");
    }

}
