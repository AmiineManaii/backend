package com.gameart.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gameart.backend.entity.Review;
import com.gameart.backend.repository.ReviewRepository;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Optional<Review> findById(String id) {
        return reviewRepository.findById(id);
    }

    public List<Review> findByGameId(String gameId) {
        return reviewRepository.findByGameId(gameId);
    }

    public List<Review> findByUserId(String userId) {
        return reviewRepository.findByUserId(userId);
    }

    public List<Review> findByVerifiedTrue() {
        return reviewRepository.findByVerifiedTrue();
    }

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public void deleteById(String id) {
        reviewRepository.deleteById(id);
    }

    public boolean existsById(String id) {
        return reviewRepository.existsById(id);
    }

    public Optional<Review> verifyReview(String id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setVerified(true);
            return Optional.of(reviewRepository.save(review));
        }
        return Optional.empty();
    }
}