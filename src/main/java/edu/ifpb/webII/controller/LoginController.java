package edu.ifpb.webII.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String telaLogin() {
        // Retorna apenas o nome do arquivo HTML em templates
        return "login"; 
    }

    @GetMapping("/home")
    public String telaHome() {
        return "home";
    }
}