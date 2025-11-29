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

import com.gameart.backend.entity.Game;
import com.gameart.backend.service.GameService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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
    public List<Game> getAllGames() {
        return gameService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un jeu par son ID")
    public ResponseEntity<Game> getGameById(@PathVariable String id) {
        Optional<Game> game = gameService.findById(id);
        return game.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/platform/{platform}")
    @Operation(summary = "Obtenir les jeux par plateforme")
    public List<Game> getGamesByPlatform(@PathVariable String platform) {
        return gameService.findByPlatform(platform);
    }

    @GetMapping("/genre/{genre}")
    @Operation(summary = "Obtenir les jeux par genre")
    public List<Game> getGamesByGenre(@PathVariable String genre) {
        return gameService.findByGenre(genre);
    }

    @GetMapping("/promo")
    @Operation(summary = "Obtenir les jeux en promotion")
    public List<Game> getPromoGames() {
        return gameService.findByPromoTrue();
    }

    @GetMapping("/popular")
    @Operation(summary = "Obtenir les jeux populaires")
    public List<Game> getPopularGames() {
        return gameService.findByPopularTrue();
    }

    @GetMapping("/search")
    @Operation(summary = "Rechercher des jeux")
    public List<Game> searchGames(@RequestParam String q) {
        return gameService.searchGames(q);
    }

    @GetMapping("/tag/{tag}")
    @Operation(summary = "Obtenir les jeux par tag")
    public List<Game> getGamesByTag(@PathVariable String tag) {
        return gameService.findByTag(tag);
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau jeu")
    public Game createGame(@RequestBody Game game) {
        return gameService.save(game);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un jeu")
    public ResponseEntity<Game> updateGame(@PathVariable String id, @RequestBody Game gameDetails) {
        Optional<Game> optionalGame = gameService.findById(id);
        if (optionalGame.isPresent()) {
            Game game = optionalGame.get();
            // Mise à jour des champs
            game.setTitle(gameDetails.getTitle());
            game.setPlatform(gameDetails.getPlatform());
            game.setGenre(gameDetails.getGenre());
            game.setPrice(gameDetails.getPrice());
            game.setRating(gameDetails.getRating());
            game.setReleaseDate(gameDetails.getReleaseDate());
            game.setStock(gameDetails.getStock());
            game.setDescription(gameDetails.getDescription());
            game.setCoverImage(gameDetails.getCoverImage());
            game.setImages(gameDetails.getImages());
            game.setUrlTrailer(gameDetails.getUrlTrailer());
            game.setPromo(gameDetails.getPromo());
            game.setPopular(gameDetails.getPopular());
            game.setTags(gameDetails.getTags());
            return ResponseEntity.ok(gameService.save(game));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un jeu")
    public ResponseEntity<Void> deleteGame(@PathVariable String id) {
        if (gameService.existsById(id)) {
            gameService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}