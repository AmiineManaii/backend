package com.gameart.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gameart.backend.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    @Query("SELECT r FROM Review r WHERE r.game.id = :gameId")
    List<Review> GetByGameId(@Param("gameId") String gameId);
    
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId")
    List<Review> GetByUserId(@Param("userId") String userId);
    
    @Query("SELECT r FROM Review r WHERE r.verified = true")
    List<Review> GetVerifiedReviews();
}