package com.gameart.backend.mapper;

import org.springframework.stereotype.Component;

import com.gameart.backend.dto.CartDTO;
import com.gameart.backend.entity.Cart;
import com.gameart.backend.entity.Game;
import com.gameart.backend.entity.User;

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
        dto.setUserId(cart.getUser().getId());
        dto.setSessionId(cart.getSessionID());
        
        
        if (cart.getGame() != null) {
            dto.setGameId(cart.getGame().getId());
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
        cart.setSessionID(dto.getSessionId());
        
        
        if (dto.getGameId() != null) {

            Game game = new Game();
            game.setId(dto.getGameId());
            cart.setGame(game);
        }
        if(dto.getUserId() != null) {
            User user = new User();
            user.setId(dto.getUserId());
            cart.setUser(user);
        }
        
        return cart;
    }

    }