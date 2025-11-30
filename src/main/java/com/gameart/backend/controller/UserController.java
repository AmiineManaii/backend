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
import com.gameart.backend.dto.UserDTO;
import com.gameart.backend.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "API de gestion des utilisateurs")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Obtenir tous les utilisateurs")
    public ApiResponse<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAll();
        return ApiResponse.success("Utilisateurs récupérés avec succès", users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un utilisateur par son ID")
    public ResponseEntity<ApiResponse<UserDTO>> getUserById(@PathVariable String id) {
        Optional<UserDTO> user = userService.findById(id);
        return user.map(u -> ResponseEntity.ok(ApiResponse.success("Utilisateur trouvé", u)))
                  .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Obtenir un utilisateur par email")
    public ResponseEntity<ApiResponse<UserDTO>> getUserByEmail(@PathVariable String email) {
        Optional<UserDTO> user = userService.findByEmail(email);
        return user.map(u -> ResponseEntity.ok(ApiResponse.success("Utilisateur trouvé", u)))
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Créer un nouvel utilisateur")
    public ApiResponse<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        if (userService.existsByEmail(userDTO.getEmail())) {
            return ApiResponse.error("Un utilisateur avec cet email existe déjà");
        }
        
        UserDTO savedUser = userService.save(userDTO);
        return ApiResponse.success("Utilisateur créé avec succès", savedUser);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un utilisateur")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@PathVariable String id, @Valid @RequestBody UserDTO userDetails) {
        if (!userService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        userDetails.setId(id); 
        UserDTO updatedUser = userService.save(userDetails);
        return ResponseEntity.ok(ApiResponse.success("Utilisateur mis à jour avec succès", updatedUser));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un utilisateur")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String id) {
        if (userService.existsById(id)) {
            userService.deleteById(id);
            return ResponseEntity.ok(ApiResponse.success("Utilisateur supprimé avec succès", null));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/wishlist/{gameId}")
    @Operation(summary = "Ajouter un jeu à la wishlist")
    public ResponseEntity<ApiResponse<UserDTO>> addToWishlist(@PathVariable String id, @PathVariable String gameId) {
        Optional<UserDTO> user = userService.addToWishlist(id, gameId);
        return user.map(u -> ResponseEntity.ok(ApiResponse.success("Jeu ajouté à la wishlist", u)))
                  .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/wishlist/{gameId}")
    @Operation(summary = "Retirer un jeu de la wishlist")
    public ResponseEntity<ApiResponse<UserDTO>> removeFromWishlist(@PathVariable String id, @PathVariable String gameId) {
        Optional<UserDTO> user = userService.removeFromWishlist(id, gameId);
        return user.map(u -> ResponseEntity.ok(ApiResponse.success("Jeu retiré de la wishlist", u)))
                  .orElse(ResponseEntity.notFound().build());
    }
}