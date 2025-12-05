package com.gameart.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gameart.backend.entity.Order;
import org.springframework.data.jpa.repository.Query;
@Repository


public interface OrderRepository extends JpaRepository<Order, String> {
    @Query("SELECT o FROM Order o WHERE o.user.id = :userId")
    List<Order> getByUserId(String userId);
}