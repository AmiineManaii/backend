package com.gameart.backend.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gameart.backend.dto.OrderDTO;
import com.gameart.backend.entity.Order;
import com.gameart.backend.mapper.GlobalMapper;
import com.gameart.backend.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final GlobalMapper mapper;

    public OrderService(OrderRepository orderRepository, GlobalMapper mapper) {
        this.orderRepository = orderRepository;
        this.mapper = mapper;
    }

    public List<OrderDTO> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::toOrderDto)
                .collect(Collectors.toList());
    }

    public Optional<OrderDTO> findById(String id) {
        return orderRepository.findById(id)
                .map(mapper::toOrderDto);
    }

    public List<OrderDTO> findByUserId(String userId) {
        return orderRepository.getByUserId(userId)
                .stream()
                .map(mapper::toOrderDto)
                .collect(Collectors.toList());
    }

    public OrderDTO save(OrderDTO orderDTO) {
        Order order = mapper.toOrderEntity(orderDTO);
        Order savedOrder = orderRepository.save(order);
        return mapper.toOrderDto(savedOrder);
    }

    public void deleteById(String id) {
        orderRepository.deleteById(id);
    }

    public boolean existsById(String id) {
        return orderRepository.existsById(id);
    }

    public Optional<OrderDTO> updateStatus(String id, String status) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(status);
            Order savedOrder = orderRepository.save(order);
            return Optional.of(mapper.toOrderDto(savedOrder));
        }
        return Optional.empty();
    }
}