package edu.ifpb.webII.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desativa para facilitar os testes iniciais
            .authorizeHttpRequests(auth -> auth
                // Libera o acesso à página de login e aos estilos (CSS/Imagens)
                .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login") // Diz ao Spring para usar a sua página bonita
                .defaultSuccessUrl("/home", true) // Para onde vai depois de logar
                .permitAll()
            )
            .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Criando os usuários de demonstração que você colocou no HTML
        
        UserDetails paciente = User.withDefaultPasswordEncoder()
            .username("paciente@vivabem.com")
            .password("123456")
            .roles("USER")
            .build();

        UserDetails clinica = User.withDefaultPasswordEncoder()
            .username("clinica@vivabem.com")
            .password("123456")
            .roles("CLINICA")
            .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin@vivabem.com")
            .password("123456")
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(paciente, clinica, admin);
    }
}