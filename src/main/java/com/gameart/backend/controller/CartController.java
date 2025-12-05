package com.gameart.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gameart.backend.dto.ApiResponse;
import com.gameart.backend.dto.CartDTO;
import com.gameart.backend.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart")
@Tag(name = "Cart", description = "API de gestion du panier")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    @Operation(summary = "Obtenir tous les éléments du panier")
    public ApiResponse<List<CartDTO>> getAllCartItems() {
        List<CartDTO> cartItems = cartService.findAll();
        return ApiResponse.success("Éléments du panier récupérés avec succès", cartItems);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un élément du panier par son ID")
    public ResponseEntity<ApiResponse<CartDTO>> getCartItemById(@PathVariable Long id) {
        Optional<CartDTO> cartItem = cartService.findById(id);
        return cartItem.map(item -> ResponseEntity.ok(ApiResponse.success("Élément du panier trouvé", item)))
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Ajouter un élément au panier")
    public ApiResponse<CartDTO> addToCart(@Valid @RequestBody CartDTO cartDTO) {
        CartDTO savedCartItem = cartService.save(cartDTO);
        return ApiResponse.success("Élément ajouté au panier avec succès", savedCartItem);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un élément du panier")
    public ResponseEntity<ApiResponse<CartDTO>> updateCartItem(@PathVariable Long id, @Valid @RequestBody CartDTO cartDetails) {
        if (!cartService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        cartDetails.setId(id);
        CartDTO updatedCartItem = cartService.save(cartDetails);
        return ResponseEntity.ok(ApiResponse.success("Élément du panier mis à jour avec succès", updatedCartItem));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un élément du panier")
    public ResponseEntity<ApiResponse<Void>> removeFromCart(@PathVariable Long id) {
        if (cartService.existsById(id)) {
            cartService.deleteById(id);
            return ResponseEntity.ok(ApiResponse.success("Élément supprimé du panier avec succès", null));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    @Operation(summary = "Vider tout le panier")
    public ApiResponse<Void> clearCart() {
        cartService.deleteAll();
        return ApiResponse.success("Panier vidé avec succès", null);
    }
    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtenir tous les éléments du panier d'un utilisateur")
    public ApiResponse<List<CartDTO>> getCartItemsByUserId(@PathVariable String userId) {
        List<CartDTO> cartItems = cartService.getCartByUserId(userId);
        return ApiResponse.success("Éléments du panier de l'utilisateur récupérés avec succès", cartItems);
    }
    @DeleteMapping("/user/{userId}")
    @Operation(summary = "Supprimer tous les éléments du panier d'un utilisateur")
    public ResponseEntity<ApiResponse<Void>> removeCartItemsByUserId(@PathVariable String userId) {
        cartService.deleteAllByUserId(userId);
        return ResponseEntity.ok(ApiResponse.success("Éléments du panier de l'utilisateur supprimés avec succès", null));
    }
    
    @DeleteMapping("/session/{sessionID}")
    @Operation(summary = "Supprimer tous les éléments du panier d'une session")
    public ResponseEntity<ApiResponse<Void>> removeCartItemsBySessionID(@PathVariable String sessionID) {
        cartService.deleteAllBySessionID(sessionID);
        return ResponseEntity.ok(ApiResponse.success("Éléments du panier de la session supprimés avec succès", null));
    }
    @GetMapping("/session/{sessionID}")
    @Operation(summary = "Obtenir tous les éléments du panier d'une session")
    public ApiResponse<List<CartDTO>> getCartItemsBySessionID(@PathVariable String sessionID) {
        List<CartDTO> cartItems = cartService.getCartBySessionID(sessionID);
        return ApiResponse.success("Éléments du panier de la session récupérés avec succès", cartItems);
    }
    @PostMapping("/session/{sessionID}")
    @Operation(summary = "Ajouter un élément au panier d'une session")
    public ApiResponse<CartDTO> addToCartSession(@PathVariable String sessionID, @Valid @RequestBody CartDTO cartDTO) {
        CartDTO savedCartItem = cartService.saveSessionCart(sessionID, cartDTO);
        return ApiResponse.success("Élément ajouté au panier de la session avec succès", savedCartItem);
    }

}