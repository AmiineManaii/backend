package com.gameart.backend.mapper;

import org.springframework.stereotype.Component;

import com.gameart.backend.dto.CartDTO;
import com.gameart.backend.entity.Cart;
import com.gameart.backend.entity.Game;

@Component
public class CartMapper {

    private final GameMapper gameMapper;

    public CartMapper(GameMapper gameMapper) {
        this.gameMapper = gameMapper;
    }

    public CartDTO toDto(Cart cart) {
        if (cart == null) {
            return null;
        }

        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setQuantity(cart.getQuantity());
        dto.setSubtotal(cart.getSubtotal());
        dto.setCreatedAt(cart.getCreatedAt());
        
        
        if (cart.getGame() != null) {
            dto.setGame(gameMapper.toDto(cart.getGame()));
        }
        
        return dto;
    }

    public Cart toEntity(CartDTO dto) {
        if (dto == null) {
            return null;
        }

        Cart cart = new Cart();
        cart.setId(dto.getId());
        cart.setQuantity(dto.getQuantity());
        cart.setSubtotal(dto.getSubtotal());
        cart.setCreatedAt(dto.getCreatedAt());
        
        
        if (dto.getGame() != null) {
            Game game = gameMapper.toEntity(dto.getGame());
            cart.setGame(game);
        }
        
        return cart;
    }

    }