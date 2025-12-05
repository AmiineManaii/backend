package com.gameart.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gameart.backend.dto.ApiResponse;
import com.gameart.backend.dto.UserDTO;
import com.gameart.backend.security.JwtTokenProvider;
import com.gameart.backend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "API d'authentification")
public class AuthController {
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    @Operation(summary = "Authentifier un utilisateur")
    public ApiResponse<Map<String, Object>> login(@Valid @RequestBody LoginRequest loginRequest) {

        // Trouver l'utilisateur par email
        var userOptional = userService.findByEmail(loginRequest.getEmail());

        if (userOptional.isEmpty()) {
            return ApiResponse.error("Utilisateur non trouvé");
        }

        var user = userOptional.get();

        // Vérifier le mot de passe
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            return ApiResponse.error("Mot de passe incorrect");
        }

        // Générer le token JWT
        String token = jwtTokenProvider.generateToken(user.getId());

        // Créer la réponse
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);

        // Masquer le mot de passe
        user.setPassword(null);
        response.put("user", user);

        return ApiResponse.success("Connexion réussie", response);
    }
    @PostMapping("/register")
    @Operation(summary = "Créer un nouvel utilisateur")
    public ApiResponse<Map<String, Object>> createUser(@Valid @RequestBody UserDTO userDTO) {
        if (userService.existsByEmail(userDTO.getEmail())) {
            return ApiResponse.error("Un utilisateur avec cet email existe déjà");
        }
        
        UserDTO savedUser = userService.save(userDTO);
        String token = jwtTokenProvider.generateToken(savedUser.getId());
        // Créer la réponse
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);

        // Masquer le mot de passe
        savedUser.setPassword(null);
        response.put("user", savedUser);

        return ApiResponse.success("Utilisateur créé avec succès", response);

    }

    
    // Note: Nous utiliserons votre endpoint POST /users existant pour l'inscription
    
    // Classe interne pour la requête de login
    public static class LoginRequest {
        private String email;
        private String password;
        
        // Getters et setters
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }
}