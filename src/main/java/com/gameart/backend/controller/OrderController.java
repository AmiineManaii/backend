package com.gameart.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gameart.backend.dto.ApiResponse;
import com.gameart.backend.dto.OrderDTO;
import com.gameart.backend.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "API de gestion des commandes")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @Operation(summary = "Obtenir toutes les commandes")
    public ApiResponse<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.findAll();
        return ApiResponse.success("Commandes récupérées avec succès", orders);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une commande par son ID")
    public ResponseEntity<ApiResponse<OrderDTO>> getOrderById(@PathVariable Long id) {
        Optional<OrderDTO> order = orderService.findById(id);
        return order.map(o -> ResponseEntity.ok(ApiResponse.success("Commande trouvée", o)))
                   .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtenir les commandes d'un utilisateur")
    public ApiResponse<List<OrderDTO>> getOrdersByUserId(@PathVariable String userId) {
        List<OrderDTO> orders = orderService.findByUserId(userId);
        return ApiResponse.success("Commandes utilisateur récupérées", orders);
    }

    @PostMapping
    @Operation(summary = "Créer une nouvelle commande")
    public ApiResponse<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        OrderDTO savedOrder = orderService.save(orderDTO);
        return ApiResponse.success("Commande créée avec succès", savedOrder);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour une commande")
    public ResponseEntity<ApiResponse<OrderDTO>> updateOrder(@PathVariable Long id, @Valid @RequestBody OrderDTO orderDetails) {
        if (!orderService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        orderDetails.setId(id);
        OrderDTO updatedOrder = orderService.save(orderDetails);
        return ResponseEntity.ok(ApiResponse.success("Commande mise à jour avec succès", updatedOrder));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une commande")
    public ResponseEntity<ApiResponse<Void>> deleteOrder(@PathVariable Long id) {
        if (orderService.existsById(id)) {
            orderService.deleteById(id);
            return ResponseEntity.ok(ApiResponse.success("Commande supprimée avec succès", null));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Mettre à jour le statut d'une commande")
    public ResponseEntity<ApiResponse<OrderDTO>> updateOrderStatus(@PathVariable Long id, @RequestBody String status) {
        Optional<OrderDTO> order = orderService.updateStatus(id, status);
        return order.map(o -> ResponseEntity.ok(ApiResponse.success("Statut de la commande mis à jour", o)))
                   .orElse(ResponseEntity.notFound().build());
    }
}