package com.demo.inventory_service.inventory;

public record InventoryRequest(
        Long productId,
        int quantity
) {
}
