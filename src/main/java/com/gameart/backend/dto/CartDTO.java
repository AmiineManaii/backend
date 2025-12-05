package com.gameart.backend.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;


@Schema(description = "DTO pour le panier")
public class CartDTO {
    
    @Schema(description = "ID de l'élément du panier", example = "1762810979063")
    private Long id;

    
    @Schema(description = "ID du jeu dans le panier", example = "12345")
    private String gameId;

    @NotBlank(message="l'utilisateur est obligatoire")
    @Schema(description = "ID de l'utilisateur", example = "12345")
    private String userId;

    
    @Schema(description = "ID de la session", example = "12345")
    private String sessionId;

    @Min(value = 1, message = "La quantité doit être au moins 1")
    @Schema(description = "Quantité", example = "1")
    private Integer quantity;

    @Min(value = 0, message = "Le sous-total ne peut pas être négatif")
    @Schema(description = "Sous-total", example = "29.99")
    private Double subtotal;

    @Schema(description = "Date d'ajout au panier")
    private LocalDateTime createdAt;
    
    
    public CartDTO() {}

    public CartDTO(Long id, String gameId, Integer quantity, Double subtotal, LocalDateTime createdAt, String userId, String sessionId) {
        this.id = id;
        this.gameId = gameId;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.createdAt = createdAt;
        this.userId = userId;
        this.sessionId = sessionId;
    }

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getGameId() { return gameId; }
    public void setGameId(String gameId) { this.gameId = gameId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}