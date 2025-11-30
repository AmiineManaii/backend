package com.gameart.backend.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "DTO pour les utilisateurs")
public class UserDTO {
    
    @Schema(description = "ID de l'utilisateur", example = "1")
    private String id;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    @Schema(description = "Nom d'utilisateur", example = "Amine")
    private String name;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    @Schema(description = "Email de l'utilisateur", example = "amine@example.com")
    private String email;

    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
    @Schema(description = "Mot de passe", example = "123456")
    private String password;

    @Schema(description = "Nom de famille", example = "Manai")
    private String nom;

    @Schema(description = "Prénom", example = "Mohamed Amine")
    private String prenom;

    @Schema(description = "Adresse", example = "Mornaguia")
    private String adresse;

    @Schema(description = "Liste des jeux dans la wishlist")
    private List<String> wishlist;


    public UserDTO() {}

    public UserDTO(String id, String name, String email, String password, 
                   String nom, String prenom, String adresse, List<String> wishlist) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.wishlist = wishlist;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public List<String> getWishlist() { return wishlist; }
    public void setWishlist(List<String> wishlist) { this.wishlist = wishlist; }
}