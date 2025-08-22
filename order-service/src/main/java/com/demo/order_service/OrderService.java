package com.demo.order_service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public OrderResponse create(OrderRequest request) {
        Order order = Order.builder()
                .userId(request.userId())
                .productId(request.productId())
                .quantity(request.quantity())
                .build();
        Order savedOrder = orderRepository.save(order);
        return OrderResponse.from(savedOrder);
    }

    public OrderResponse findById(Long id) {
        return OrderResponse.from(orderRepository.findById(id).orElseThrow());
    }

    public List<OrderResponse> findAllByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId).stream()
                .map(OrderResponse::from)
                .toList();
    }
}
