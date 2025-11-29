package com.gameart.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gameart.backend.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {
    
    List<Game> findByPlatform(String platform);
    List<Game> findByGenre(String genre);
    List<Game> findByPromoTrue();
    List<Game> findByPopularTrue();
    
    @Query("SELECT g FROM Game g WHERE LOWER(g.title) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(g.description) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Game> searchGames(@Param("search") String search);
    
    List<Game> findByTagsContaining(String tag);
}