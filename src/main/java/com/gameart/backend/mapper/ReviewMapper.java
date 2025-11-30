package com.gameart.backend.mapper;

import org.springframework.stereotype.Component;

import com.gameart.backend.dto.ReviewDTO;
import com.gameart.backend.entity.Review;

@Component
public class ReviewMapper {

    public ReviewDTO toDto(Review review) {
        if (review == null) {
            return null;
        }

        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setGame(review.getGame());
        dto.setUser(review.getUser());
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
        review.setGame(dto.getGame());
        review.setUser(dto.getUser());
        review.setMsg(dto.getMsg());
        review.setNote(dto.getNote());
        review.setDate(dto.getDate());
        review.setVerified(dto.getVerified());
        
        return review;
    }
}