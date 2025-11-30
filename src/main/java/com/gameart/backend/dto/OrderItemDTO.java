package com.gameart.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;



@Schema(description = "DTO pour les articles de commande")
public class OrderItemDTO {
    
    @NotBlank(message = "L'ID du jeu est obligatoire")
    @Schema(description = "ID du jeu", example = "1")
    private String gameId;

    @Min(value = 1, message = "La quantité doit être au moins 1")
    @Schema(description = "Quantité", example = "1")
    private Integer quantity;

    @Min(value = 0, message = "Le prix ne peut pas être négatif")
    @Schema(description = "Prix unitaire", example = "59.99")
    private Double price;

  
    public OrderItemDTO() {}

    public OrderItemDTO(String gameId, Integer quantity, Double price) {
        this.gameId = gameId;
        this.quantity = quantity;
        this.price = price;
    }

   
    public String getGameId() { return gameId; }
    public void setGameId(String gameId) { this.gameId = gameId; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}