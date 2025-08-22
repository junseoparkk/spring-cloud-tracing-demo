package com.demo.order_service;

public record OrderRequest(
        Long userId,
        Long productId,
        int quantity
) {
}
