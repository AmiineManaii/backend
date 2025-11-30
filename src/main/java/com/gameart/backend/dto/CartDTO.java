package com.gameart.backend.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description = "DTO pour le panier")
public class CartDTO {
    
    @Schema(description = "ID de l'élément du panier", example = "1762810979063")
    private Long id;

    @NotNull(message = "Le jeu est obligatoire")
    @Schema(description = "Jeu dans le panier")
    private GameDTO game;

    @Min(value = 1, message = "La quantité doit être au moins 1")
    @Schema(description = "Quantité", example = "1")
    private Integer quantity;

    @Min(value = 0, message = "Le sous-total ne peut pas être négatif")
    @Schema(description = "Sous-total", example = "29.99")
    private Double subtotal;

    @Schema(description = "Date d'ajout au panier")
    private LocalDateTime createdAt;

    
    public CartDTO() {}

    public CartDTO(Long id, GameDTO game, Integer quantity, Double subtotal, LocalDateTime createdAt) {
        this.id = id;
        this.game = game;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.createdAt = createdAt;
    }

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public GameDTO getGame() { return game; }
    public void setGame(GameDTO game) { this.game = game; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}