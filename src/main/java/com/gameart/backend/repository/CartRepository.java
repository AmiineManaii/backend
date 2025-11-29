package com.gameart.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gameart.backend.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}