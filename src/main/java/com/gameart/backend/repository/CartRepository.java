package com.gameart.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



import com.gameart.backend.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.sessionID = :sessionID")
    List<Cart> getCartBySessionID(@Param("sessionID") String sessionID);
    
    @Query("SELECT c FROM Cart c WHERE c.user.id = :userId")
    List<Cart> getCartByUserId(@Param("userId") String userId);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.sessionID = :sessionID")
    void deleteAllBySessionID(@Param("sessionID") String sessionID);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.user.id = :userId")
    void deleteAllByUserId(@Param("userId") String userId);

    
}