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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gameart.backend.dto.ApiResponse;
import com.gameart.backend.dto.GameDTO;
import com.gameart.backend.service.GameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/games")
@Tag(name = "Games", description = "API de gestion des jeux vidéo")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    @Operation(summary = "Obtenir tous les jeux")
    public ApiResponse<List<GameDTO>> getAllGames() {
        List<GameDTO> games = gameService.findAll();
        return ApiResponse.success("Jeux récupérés avec succès", games);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un jeu par son ID")
    public ResponseEntity<ApiResponse<GameDTO>> getGameById(@PathVariable String id) {
        Optional<GameDTO> game = gameService.findById(id);
        return game.map(g -> ResponseEntity.ok(ApiResponse.success("Jeu trouvé", g)))
                  .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/platform/{platform}")
    @Operation(summary = "Obtenir les jeux par plateforme")
    public ApiResponse<List<GameDTO>> getGamesByPlatform(@PathVariable String platform) {
        List<GameDTO> games = gameService.findByPlatform(platform);
        return ApiResponse.success("Jeux par plateforme récupérés", games);
    }

    @GetMapping("/genre/{genre}")
    @Operation(summary = "Obtenir les jeux par genre")
    public ApiResponse<List<GameDTO>> getGamesByGenre(@PathVariable String genre) {
        List<GameDTO> games = gameService.findByGenre(genre);
        return ApiResponse.success("Jeux par genre récupérés", games);
    }

    @GetMapping("/promo")
    @Operation(summary = "Obtenir les jeux en promotion")
    public ApiResponse<List<GameDTO>> getPromoGames() {
        List<GameDTO> games = gameService.findByPromoTrue();
        return ApiResponse.success("Jeux en promotion récupérés", games);
    }

    @GetMapping("/popular")
    @Operation(summary = "Obtenir les jeux populaires")
    public ApiResponse<List<GameDTO>> getPopularGames() {
        List<GameDTO> games = gameService.findByPopularTrue();
        return ApiResponse.success("Jeux populaires récupérés", games);
    }

    @GetMapping("/search")
    @Operation(summary = "Rechercher des jeux")
    public ApiResponse<List<GameDTO>> searchGames(@RequestParam String q) {
        List<GameDTO> games = gameService.searchGames(q);
        return ApiResponse.success("Résultats de recherche récupérés", games);
    }

    @GetMapping("/tag/{tag}")
    @Operation(summary = "Obtenir les jeux par tag")
    public ApiResponse<List<GameDTO>> getGamesByTag(@PathVariable String tag) {
        List<GameDTO> games = gameService.findByTag(tag);
        return ApiResponse.success("Jeux par tag récupérés", games);
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau jeu")
    public ApiResponse<GameDTO> createGame(@Valid @RequestBody GameDTO gameDTO) {
        GameDTO savedGame = gameService.save(gameDTO);
        return ApiResponse.success("Jeu créé avec succès", savedGame);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un jeu")
    public ResponseEntity<ApiResponse<GameDTO>> updateGame(@PathVariable String id, @Valid @RequestBody GameDTO gameDetails) {
        if (!gameService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        gameDetails.setId(id);
        GameDTO updatedGame = gameService.save(gameDetails);
        return ResponseEntity.ok(ApiResponse.success("Jeu mis à jour avec succès", updatedGame));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un jeu")
    public ResponseEntity<ApiResponse<Void>> deleteGame(@PathVariable String id) {
        if (gameService.existsById(id)) {
            gameService.deleteById(id);
            return ResponseEntity.ok(ApiResponse.success("Jeu supprimé avec succès", null));
        }
        return ResponseEntity.notFound().build();
    }
}