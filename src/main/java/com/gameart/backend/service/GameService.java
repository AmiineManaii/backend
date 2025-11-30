package com.gameart.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gameart.backend.dto.GameDTO;
import com.gameart.backend.entity.Game;
import com.gameart.backend.mapper.GlobalMapper;
import com.gameart.backend.repository.GameRepository;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GlobalMapper mapper;

    public GameService(GameRepository gameRepository, GlobalMapper mapper) {
        this.gameRepository = gameRepository;
        this.mapper = mapper;
    }

    public List<GameDTO> findAll() {
        return gameRepository.findAll()
                .stream()
                .map(mapper::toGameDto)
                .collect(Collectors.toList());
    }

    public Optional<GameDTO> findById(String id) {
        return gameRepository.findById(id)
                .map(mapper::toGameDto);
    }

    public GameDTO save(GameDTO gameDTO) {
        Game game = mapper.toGameEntity(gameDTO);
        Game savedGame = gameRepository.save(game);
        return mapper.toGameDto(savedGame);
    }

    public void deleteById(String id) {
        gameRepository.deleteById(id);
    }

    public boolean existsById(String id) {
        return gameRepository.existsById(id);
    }

    public List<GameDTO> findByPlatform(String platform) {
        return gameRepository.findByPlatform(platform)
                .stream()
                .map(mapper::toGameDto)
                .collect(Collectors.toList());
    }

    public List<GameDTO> findByGenre(String genre) {
        return gameRepository.findByGenre(genre)
                .stream()
                .map(mapper::toGameDto)
                .collect(Collectors.toList());
    }

    public List<GameDTO> findByPromoTrue() {
        return gameRepository.findByPromoTrue()
                .stream()
                .map(mapper::toGameDto)
                .collect(Collectors.toList());
    }

    public List<GameDTO> findByPopularTrue() {
        return gameRepository.findByPopularTrue()
                .stream()
                .map(mapper::toGameDto)
                .collect(Collectors.toList());
    }

    public List<GameDTO> searchGames(String search) {
        return gameRepository.searchGames(search)
                .stream()
                .map(mapper::toGameDto)
                .collect(Collectors.toList());
    }

    public List<GameDTO> findByTag(String tag) {
        return gameRepository.findByTagsContaining(tag)
                .stream()
                .map(mapper::toGameDto)
                .collect(Collectors.toList());
    }
}