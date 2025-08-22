package com.demo.inventory_service.product;

public record ProductRequest(
        String name,
        int price
) {
}
