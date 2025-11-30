package com.gameart.backend.dto;

import com.gameart.backend.entity.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;


@Schema(description = "DTO pour les adresses")
public class AddressDTO {
    
    @Schema(description = "ID de l'adresse", example = "c2f1")
    private String id;

    @NotBlank(message = "Le nom de l'adresse est obligatoire")
    @Schema(description = "Nom de l'adresse", example = "mornaguia")
    private String nom;

    @NotBlank(message = "La rue est obligatoire")
    @Schema(description = "Rue", example = "04")
    private String rue;

    @NotBlank(message = "La ville est obligatoire")
    @Schema(description = "Ville", example = "mornaguia")
    private String ville;

    @NotBlank(message = "Le code postal est obligatoire")
    @Schema(description = "Code postal", example = "1110")
    private String codePostal;

    @NotBlank(message = "Le pays est obligatoire")
    @Schema(description = "Pays", example = "tunisie")
    private String pays;

    @NotBlank(message = "L'ID utilisateur est obligatoire")
    @Schema(description = "ID de l'utilisateur", example = "1")
    private User user;

    @Schema(description = "Indique si c'est l'adresse par défaut", example = "true")
    private Boolean isDefault;

    
    public AddressDTO() {}

    public AddressDTO(String id, String nom, String rue, String ville, 
                      String codePostal, String pays, User user, Boolean isDefault) {
        this.id = id;
        this.nom = nom;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.pays = pays;
        this.user = user;
        this.isDefault = isDefault;
    }

    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getRue() { return rue; }
    public void setRue(String rue) { this.rue = rue; }

    public String getVille() { return ville; }
    public void setVille(String ville) { this.ville = ville; }

    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }

    public String getPays() { return pays; }
    public void setPays(String pays) { this.pays = pays; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Boolean getIsDefault() { return isDefault; }
    public void setIsDefault(Boolean isDefault) { this.isDefault = isDefault; }
}