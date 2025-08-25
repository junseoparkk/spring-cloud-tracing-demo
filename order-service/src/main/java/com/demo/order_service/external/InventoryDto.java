package com.demo.order_service.external;

public record InventoryDto(
        Long inventoryId,
        Long productId,
        String productName,
        int stock
) {
}
