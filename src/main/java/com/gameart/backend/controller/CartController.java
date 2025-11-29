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

import com.gameart.backend.entity.Cart;
import com.gameart.backend.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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
    public List<Cart> getAllCartItems() {
        return cartService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un élément du panier par son ID")
    public ResponseEntity<Cart> getCartItemById(@PathVariable Long id) {
        Optional<Cart> cartItem = cartService.findById(id);
        return cartItem.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Ajouter un élément au panier")
    public Cart addToCart(@RequestBody Cart cartItem) {
        return cartService.save(cartItem);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un élément du panier")
    public ResponseEntity<Cart> updateCartItem(@PathVariable Long id, @RequestBody Cart cartDetails) {
        Optional<Cart> optionalCart = cartService.findById(id);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.setGame(cartDetails.getGame());
            cart.setQuantity(cartDetails.getQuantity());
            cart.setSubtotal(cartDetails.getSubtotal());
            cart.setCreatedAt(cartDetails.getCreatedAt());
            return ResponseEntity.ok(cartService.save(cart));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un élément du panier")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long id) {
        if (cartService.existsById(id)) {
            cartService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    @Operation(summary = "Vider tout le panier")
    public ResponseEntity<Void> clearCart() {
        cartService.deleteAll();
        return ResponseEntity.ok().build();
    }
}