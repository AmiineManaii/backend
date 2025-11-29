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

import com.gameart.backend.entity.Review;
import com.gameart.backend.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

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
    public List<Review> getAllReviews() {
        return reviewService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un avis par son ID")
    public ResponseEntity<Review> getReviewById(@PathVariable String id) {
        Optional<Review> review = reviewService.findById(id);
        return review.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/game/{gameId}")
    @Operation(summary = "Obtenir les avis d'un jeu")
    public List<Review> getReviewsByGameId(@PathVariable String gameId) {
        return reviewService.findByGameId(gameId);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Obtenir les avis d'un utilisateur")
    public List<Review> getReviewsByUserId(@PathVariable String userId) {
        return reviewService.findByUserId(userId);
    }

    @GetMapping("/verified")
    @Operation(summary = "Obtenir les avis vérifiés")
    public List<Review> getVerifiedReviews() {
        return reviewService.findByVerifiedTrue();
    }

    @PostMapping
    @Operation(summary = "Créer un nouvel avis")
    public Review createReview(@RequestBody Review review) {
        return reviewService.save(review);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un avis")
    public ResponseEntity<Review> updateReview(@PathVariable String id, @RequestBody Review reviewDetails) {
        Optional<Review> optionalReview = reviewService.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setGameId(reviewDetails.getGameId());
            review.setUserId(reviewDetails.getUserId());
            review.setMsg(reviewDetails.getMsg());
            review.setNote(reviewDetails.getNote());
            review.setDate(reviewDetails.getDate());
            review.setVerified(reviewDetails.getVerified());
            return ResponseEntity.ok(reviewService.save(review));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un avis")
    public ResponseEntity<Void> deleteReview(@PathVariable String id) {
        if (reviewService.existsById(id)) {
            reviewService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/verify")
    @Operation(summary = "Marquer un avis comme vérifié")
    public ResponseEntity<Review> verifyReview(@PathVariable String id) {
        Optional<Review> review = reviewService.verifyReview(id);
        return review.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}