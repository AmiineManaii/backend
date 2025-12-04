package com.gameart.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gameart.backend.dto.ReviewDTO;
import com.gameart.backend.entity.Review;
import com.gameart.backend.mapper.GlobalMapper;
import com.gameart.backend.repository.ReviewRepository;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final GlobalMapper mapper;

    public ReviewService(ReviewRepository reviewRepository, GlobalMapper mapper) {
        this.reviewRepository = reviewRepository;
        this.mapper = mapper;
    }

    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll()
                .stream()
                .map(mapper::toReviewDto)
                .collect(Collectors.toList());
    }

    public Optional<ReviewDTO> findById(String id) {
        return reviewRepository.findById(id)
                .map(mapper::toReviewDto);
    }

    public List<ReviewDTO> findByGameId(String gameId) {
        return reviewRepository.GetByGameId(gameId)
                .stream()
                .map(mapper::toReviewDto)
                .collect(Collectors.toList());
    }

    public List<ReviewDTO> findByUserId(String userId) {
        return reviewRepository.GetByUserId(userId)
                .stream()
                .map(mapper::toReviewDto)
                .collect(Collectors.toList());
    }

    public List<ReviewDTO> findByVerifiedTrue() {
        return reviewRepository.GetVerifiedReviews()
                .stream()
                .map(mapper::toReviewDto)
                .collect(Collectors.toList());
    }

    public ReviewDTO save(ReviewDTO reviewDTO) {
        Review review = mapper.toReviewEntity(reviewDTO);
        Review savedReview = reviewRepository.save(review);
        return mapper.toReviewDto(savedReview);
    }

    public void deleteById(String id) {
        reviewRepository.deleteById(id);
    }

    public boolean existsById(String id) {
        return reviewRepository.existsById(id);
    }

    public Optional<ReviewDTO> verifyReview(String id) {
        Optional<Review> optionalReview = reviewRepository.findById(id);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setVerified(true);
            Review savedReview = reviewRepository.save(review);
            return Optional.of(mapper.toReviewDto(savedReview));
        }
        return Optional.empty();
    }
}