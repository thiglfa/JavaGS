package com.example.mottu_springboot.service;

import com.example.mottu_springboot.model.Usuario;

import com.example.mottu_springboot.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository repo;

    public CustomUserDetailsService(UsuarioRepository repo){
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return User.builder()
                .username(u.getUsername())
                .password(u.getPassword())
                .roles(u.getRole().name().replace("ROLE_", ""))
                .disabled(!u.isEnabled())
                .build();
    }
}

