package com.demo.order_service.order;

public record OrderResponse(
        Long orderId,
        Long userId,
        Long productId,
        int quantity
) {
    public static OrderResponse from(Order order) {
        return new OrderResponse(order.getId(), order.getUserId(), order.getProductId(), order.getQuantity());
    }
}
