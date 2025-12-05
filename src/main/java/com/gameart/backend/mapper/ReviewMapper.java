package com.gameart.backend.mapper;

import org.springframework.stereotype.Component;

import com.gameart.backend.dto.ReviewDTO;
import com.gameart.backend.entity.Game;
import com.gameart.backend.entity.Review;
import com.gameart.backend.entity.User;

@Component
public class ReviewMapper {

    public ReviewDTO toDto(Review review) {
        if (review == null) {
            return null;
        }

        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setGameId(review.getGame().getId());
        dto.setUserId(review.getUser().getId());
        dto.setMsg(review.getMsg());
        dto.setNote(review.getNote());
        dto.setDate(review.getDate());
        dto.setVerified(review.getVerified());
        
        return dto;
    }

    public Review toEntity(ReviewDTO dto) {
        if (dto == null) {
            return null;
        }

        Review review = new Review();
        review.setId(dto.getId());
     
        review.setMsg(dto.getMsg());
        review.setNote(dto.getNote());
        review.setDate(dto.getDate());
        review.setVerified(dto.getVerified());
        if (dto.getGameId() != null) {
            Game game = new Game();
            game.setId(dto.getGameId());
            review.setGame(game);
        }
        if (dto.getUserId() != null) {
            User user = new User();
            user.setId(dto.getUserId());
            review.setUser(user);
        }
        return review;
    }
}