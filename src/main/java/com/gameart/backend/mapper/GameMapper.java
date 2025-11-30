package com.gameart.backend.mapper;

import org.springframework.stereotype.Component;

import com.gameart.backend.dto.GameDTO;
import com.gameart.backend.entity.Game;

@Component
public class GameMapper {

    public GameDTO toDto(Game game) {
        if (game == null) {
            return null;
        }

        GameDTO dto = new GameDTO();
        dto.setId(game.getId());
        dto.setTitle(game.getTitle());
        dto.setPlatform(game.getPlatform());
        dto.setGenre(game.getGenre());
        dto.setPrice(game.getPrice());
        dto.setRating(game.getRating());
        dto.setReleaseDate(game.getReleaseDate());
        dto.setStock(game.getStock());
        dto.setDescription(game.getDescription());
        dto.setCoverImage(game.getCoverImage());
        dto.setImages(game.getImages());
        dto.setUrlTrailer(game.getUrlTrailer());
        dto.setPromo(game.getPromo());
        dto.setPopular(game.getPopular());
        dto.setTags(game.getTags());
        
        return dto;
    }

    public Game toEntity(GameDTO dto) {
        if (dto == null) {
            return null;
        }

        Game game = new Game();
        game.setId(dto.getId());
        game.setTitle(dto.getTitle());
        game.setPlatform(dto.getPlatform());
        game.setGenre(dto.getGenre());
        game.setPrice(dto.getPrice());
        game.setRating(dto.getRating());
        game.setReleaseDate(dto.getReleaseDate());
        game.setStock(dto.getStock());
        game.setDescription(dto.getDescription());
        game.setCoverImage(dto.getCoverImage());
        game.setImages(dto.getImages());
        game.setUrlTrailer(dto.getUrlTrailer());
        game.setPromo(dto.getPromo());
        game.setPopular(dto.getPopular());
        game.setTags(dto.getTags());
        
        return game;
    }

    }