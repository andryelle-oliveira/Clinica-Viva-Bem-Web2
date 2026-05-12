package edu.ifpb.webII.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EsqueciSenhaController {

    @GetMapping("/esqueci-senha")
    public String mostrarFormularioEsqueciSenha() {
        return "esqueci-senha"; 
    }

    @PostMapping("/esqueci-senha")
    public String processarEsqueciSenha(@RequestParam("email") String email, Model model) {
        System.out.println("Solicitação para: " + email);
        model.addAttribute("mensagem", "Se o e-mail estiver cadastrado, você receberá um link.");
        return "esqueci-senha"; 
    }
}