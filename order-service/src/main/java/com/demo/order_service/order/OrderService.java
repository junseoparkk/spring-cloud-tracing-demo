package com.demo.order_service.order;

import com.demo.order_service.external.InventoryClient;
import com.demo.order_service.external.InventoryDto;
import com.demo.order_service.external.UserClient;
import com.demo.order_service.external.UserDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserClient userClient;
    private final InventoryClient inventoryClient;

    @Transactional
    public OrderResponse create(OrderRequest request) {
        log.info("📌 주문 생성 요청 - userId={}, productId={}, quantity={}",
                request.userId(), request.productId(), request.quantity());

        // 1. 사용자 검증
        UserDto user = userClient.getUser(request.userId());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        log.info("✅ user-service 응답: {}", user);

        // 2. 재고 차감
        InventoryDto inventory = inventoryClient.decreaseStock(request.productId(), request.quantity());
        if (inventory.stock() < 0) {
            throw new RuntimeException("Insufficient stock");
        }
        log.info("✅ inventory-service 응답: {}", inventory);

        // 3. 주문 저장
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
