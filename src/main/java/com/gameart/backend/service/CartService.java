package com.gameart.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gameart.backend.dto.CartDTO;
import com.gameart.backend.entity.Cart;
import com.gameart.backend.mapper.GlobalMapper;
import com.gameart.backend.repository.CartRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final GlobalMapper mapper;

    public CartService(CartRepository cartRepository, GlobalMapper mapper) {
        this.cartRepository = cartRepository;
        this.mapper = mapper;
    }

    public List<CartDTO> findAll() {
        return cartRepository.findAll()
                .stream()
                .map(mapper::toCartDto)
                .collect(Collectors.toList());
    }

    public Optional<CartDTO> findById(Long id) {
        return cartRepository.findById(id)
                .map(mapper::toCartDto);
    }

    public CartDTO save(CartDTO cartDTO) {
        Cart cart = mapper.toCartEntity(cartDTO);
        Cart savedCart = cartRepository.save(cart);
        return mapper.toCartDto(savedCart);
    }

    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

    public void deleteAll() {
        cartRepository.deleteAll();
    }

    public boolean existsById(Long id) {
        return cartRepository.existsById(id);
    }
    public void deleteAllBySessionID(String sessionID) {
        cartRepository.deleteAllBySessionID(sessionID);
    }
    public void deleteAllByUserId(String userId) {
        cartRepository.deleteAllByUserId(userId);
    }
    public List<CartDTO> getCartBySessionID(String sessionID) {
        return cartRepository.getCartBySessionID(sessionID)
                 .stream()
                 .map(mapper::toCartDto)
                 .collect(Collectors.toList());
    }
    public List<CartDTO> getCartByUserId(String userId) {
        return cartRepository.getCartByUserId(userId)
                 .stream()
                 .map(mapper::toCartDto)
                 .collect(Collectors.toList());
    }
}