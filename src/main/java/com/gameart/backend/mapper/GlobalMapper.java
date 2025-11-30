package com.gameart.backend.mapper;

import org.springframework.stereotype.Component;

import com.gameart.backend.dto.AddressDTO;
import com.gameart.backend.dto.CartDTO;
import com.gameart.backend.dto.GameDTO;
import com.gameart.backend.dto.OrderDTO;
import com.gameart.backend.dto.ReviewDTO;
import com.gameart.backend.dto.UserDTO;
import com.gameart.backend.entity.Address;
import com.gameart.backend.entity.Cart;
import com.gameart.backend.entity.Game;
import com.gameart.backend.entity.Order;
import com.gameart.backend.entity.Review;
import com.gameart.backend.entity.User;

@Component
public class GlobalMapper {
    
    private final GameMapper gameMapper;
    private final UserMapper userMapper;
    private final OrderMapper orderMapper;
    private final AddressMapper addressMapper;
    private final CartMapper cartMapper;
    private final ReviewMapper reviewMapper;

    public GlobalMapper(GameMapper gameMapper, UserMapper userMapper, 
                       OrderMapper orderMapper, AddressMapper addressMapper,
                       CartMapper cartMapper, ReviewMapper reviewMapper) {
        this.gameMapper = gameMapper;
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
        this.addressMapper = addressMapper;
        this.cartMapper = cartMapper;
        this.reviewMapper = reviewMapper;
    }

  
    public GameDTO toGameDto(Game game) {
        return gameMapper.toDto(game);
    }

    public Game toGameEntity(GameDTO dto) {
        return gameMapper.toEntity(dto);
    }

    
    public UserDTO toUserDto(User user) {
        return userMapper.toDto(user);
    }

    public User toUserEntity(UserDTO dto) {
        return userMapper.toEntity(dto);
    }

    

   
    public OrderDTO toOrderDto(Order order) {
        return orderMapper.toDto(order);
    }

    public Order toOrderEntity(OrderDTO dto) {
        return orderMapper.toEntity(dto);
    }

    
    public AddressDTO toAddressDto(Address address) {
        return addressMapper.toDto(address);
    }

    public Address toAddressEntity(AddressDTO dto) {
        return addressMapper.toEntity(dto);
    }

  
    public CartDTO toCartDto(Cart cart) {
        return cartMapper.toDto(cart);
    }

    public Cart toCartEntity(CartDTO dto) {
        return cartMapper.toEntity(dto);
    }

    
    public ReviewDTO toReviewDto(Review review) {
        return reviewMapper.toDto(review);
    }

    public Review toReviewEntity(ReviewDTO dto) {
        return reviewMapper.toEntity(dto);
    }
}