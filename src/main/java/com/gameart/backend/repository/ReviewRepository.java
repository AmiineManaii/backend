package com.gameart.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gameart.backend.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> findByGameId(String gameId);
    List<Review> findByUserId(String userId);
    List<Review> findByVerifiedTrue();
}