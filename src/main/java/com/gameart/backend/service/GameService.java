package com.gameart.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gameart.backend.entity.Game;
import com.gameart.backend.repository.GameRepository;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    public Optional<Game> findById(String id) {
        return gameRepository.findById(id);
    }

    public Game save(Game game) {
        return gameRepository.save(game);
    }

    public void deleteById(String id) {
        gameRepository.deleteById(id);
    }

    public boolean existsById(String id) {
        return gameRepository.existsById(id);
    }

    public List<Game> findByPlatform(String platform) {
        return gameRepository.findByPlatform(platform);
    }

    public List<Game> findByGenre(String genre) {
        return gameRepository.findByGenre(genre);
    }

    public List<Game> findByPromoTrue() {
        return gameRepository.findByPromoTrue();
    }

    public List<Game> findByPopularTrue() {
        return gameRepository.findByPopularTrue();
    }

    public List<Game> searchGames(String search) {
        return gameRepository.searchGames(search);
    }

    public List<Game> findByTag(String tag) {
        return gameRepository.findByTagsContaining(tag);
    }
}