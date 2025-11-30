package com.gameart.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gameart.backend.entity.Cart;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    void deleteAllBySessionID(String sessionID);
    void deleteAllByUserId(String userId);
    List<Cart> getCartBySessionID(String sessionID);
    List<Cart> getCartByUserId(String userId);
}