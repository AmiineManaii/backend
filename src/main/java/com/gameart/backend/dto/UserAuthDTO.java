package com.gameart.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "DTO pour l'authentification des utilisateurs")
public class UserAuthDTO {
    
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    @Schema(description = "Email de l'utilisateur", example = "amine@example.com")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Schema(description = "Mot de passe", example = "123456")
    private String password;


    public UserAuthDTO() {}

    public UserAuthDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }


    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}