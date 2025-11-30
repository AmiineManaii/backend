package com.gameart.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.gameart.backend.entity.User;

@Schema(description = "DTO pour les commandes")
public class OrderDTO {
    
    @Schema(description = "ID de la commande", example = "1")
    private Long id;

    @NotBlank(message = "L'ID utilisateur est obligatoire")
    @Schema(description = "ID de l'utilisateur", example = "1")
    private User user;

    @NotNull(message = "La date est obligatoire")
    @Schema(description = "Date de la commande")
    private LocalDateTime date;

    @Schema(description = "Liste des articles de la commande")
    private List<OrderItemDTO> items;

    @Min(value = 0, message = "Le total ne peut pas être négatif")
    @Schema(description = "Total de la commande", example = "59.99")
    private Double total;

    @NotBlank(message = "Le statut est obligatoire")
    @Schema(description = "Statut de la commande", example = "completed")
    private String status;

    @NotBlank(message = "L'adresse de livraison est obligatoire")
    @Schema(description = "ID de l'adresse de livraison", example = "c2f1")
    private String addressId;

    // Constructeurs
    public OrderDTO() {}

    public OrderDTO(Long id, User user, LocalDateTime date, List<OrderItemDTO> items, 
                    Double total, String status, String addressId) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.items = items;
        this.total = total;
        this.status = status;
        this.addressId = addressId;
    }

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public List<OrderItemDTO> getItems() { return items; }
    public void setItems(List<OrderItemDTO> items) { this.items = items; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAddressId() { return addressId; }
    public void setAddressId(String addressId) { this.addressId = addressId; }
}

