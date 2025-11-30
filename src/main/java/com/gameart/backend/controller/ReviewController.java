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
import com.gameart.backend.dto.ReviewDTO;
import com.gameart.backend.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reviews")
@Tag(name = "Reviews", description = "API de gestion des avis")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    @Operation(summary = "Obtenir tous les avis")
    public ApiResponse<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviews = reviewService.findAll();
        return ApiResponse.success("Avis récupérés avec succès", reviews);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un avis par son ID")
    public ResponseEntity<ApiResponse<ReviewDTO>> getReviewById(@PathVariable String id) {
        Optional<ReviewDTO> review = reviewService.findById(id);
        return review.map(r -> ResponseEntity.ok(ApiResponse.success("Avis trouvé", r)))
                    .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/game/{gameId}")
    @Operation(summary = "Obtenir les avis d'un jeu")
    public ApiResponse<List<ReviewDTO>> getReviewsByGameId(@PathVariable String gameId) {
        List<ReviewDTO> reviews = reviewService.findByGameId(gameId);
        return ApiResponse.success("Avis du jeu récupérés", reviews);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtenir les avis d'un utilisateur")
    public ApiResponse<List<ReviewDTO>> getReviewsByUserId(@PathVariable String userId) {
        List<ReviewDTO> reviews = reviewService.findByUserId(userId);
        return ApiResponse.success("Avis utilisateur récupérés", reviews);
    }

    @GetMapping("/verified")
    @Operation(summary = "Obtenir les avis vérifiés")
    public ApiResponse<List<ReviewDTO>> getVerifiedReviews() {
        List<ReviewDTO> reviews = reviewService.findByVerifiedTrue();
        return ApiResponse.success("Avis vérifiés récupérés", reviews);
    }

    @PostMapping
    @Operation(summary = "Créer un nouvel avis")
    public ApiResponse<ReviewDTO> createReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        ReviewDTO savedReview = reviewService.save(reviewDTO);
        return ApiResponse.success("Avis créé avec succès", savedReview);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un avis")
    public ResponseEntity<ApiResponse<ReviewDTO>> updateReview(@PathVariable String id, @Valid @RequestBody ReviewDTO reviewDetails) {
        if (!reviewService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        reviewDetails.setId(id);
        ReviewDTO updatedReview = reviewService.save(reviewDetails);
        return ResponseEntity.ok(ApiResponse.success("Avis mis à jour avec succès", updatedReview));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un avis")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable String id) {
        if (reviewService.existsById(id)) {
            reviewService.deleteById(id);
            return ResponseEntity.ok(ApiResponse.success("Avis supprimé avec succès", null));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/verify")
    @Operation(summary = "Marquer un avis comme vérifié")
    public ResponseEntity<ApiResponse<ReviewDTO>> verifyReview(@PathVariable String id) {
        Optional<ReviewDTO> review = reviewService.verifyReview(id);
        return review.map(r -> ResponseEntity.ok(ApiResponse.success("Avis vérifié avec succès", r)))
                    .orElse(ResponseEntity.notFound().build());
    }
}