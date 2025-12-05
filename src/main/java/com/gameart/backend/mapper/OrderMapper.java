package com.gameart.backend.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.gameart.backend.dto.OrderDTO;
import com.gameart.backend.dto.OrderItemDTO;
import com.gameart.backend.entity.Order;
import com.gameart.backend.entity.OrderItem;
import com.gameart.backend.entity.User;



@Component
public class OrderMapper {

    public OrderDTO toDto(Order order) {
        if (order == null) {
            return null;
        }

        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setDate(order.getDate());
        dto.setTotal(order.getTotal());
        dto.setStatus(order.getStatus());
        dto.setAddressId(order.getAddressId());
        
        
        if (order.getItems() != null) {
            dto.setItems(order.getItems().stream()
                    .map(this::toItemDto)
                    .collect(Collectors.toList()));
        }
        
        return dto;
    }

    public Order toEntity(OrderDTO dto) {
        if (dto == null) {
            return null;
        }

        Order order = new Order();
        order.setId(dto.getId());
        order.setDate(dto.getDate());
        order.setTotal(dto.getTotal());
        order.setStatus(dto.getStatus());
        order.setAddressId(dto.getAddressId());
        
    
        if (dto.getItems() != null) {
            order.setItems(dto.getItems().stream()
                    .map(this::toItemEntity)
                    .collect(Collectors.toList()));
        }
        if(dto.getUserId() != null) {
            User user=new User();
            user.setId(dto.getUserId());
            order.setUser(user);
        }
        
        return order;
    }

    public OrderItemDTO toItemDto(OrderItem item) {
        if (item == null) {
            return null;
        }

        OrderItemDTO dto = new OrderItemDTO();
        dto.setGameId(item.getGameId());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        
        return dto;
    }

    public OrderItem toItemEntity(OrderItemDTO dto) {
        if (dto == null) {
            return null;
        }

        OrderItem item = new OrderItem();
        item.setGameId(dto.getGameId());
        item.setQuantity(dto.getQuantity());
        item.setPrice(dto.getPrice());
        
        return item;
    }

   }