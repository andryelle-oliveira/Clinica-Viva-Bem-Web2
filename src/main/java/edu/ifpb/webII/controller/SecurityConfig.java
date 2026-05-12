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
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(auth -> auth
                // ADICIONAMOS "/esqueci-senha/**" NA LINHA ABAIXO:
                .requestMatchers("/login", "/esqueci-senha/**", "/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login") 
                .defaultSuccessUrl("/home", true) 
                .permitAll()
            )
            .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Seus usuários de demonstração continuam iguais
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