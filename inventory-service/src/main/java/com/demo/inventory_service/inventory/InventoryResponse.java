package com.demo.inventory_service.inventory;

import com.demo.inventory_service.product.Product;

public record InventoryResponse(
        Long inventoryId,
        Long productId,
        String productName,
        int stock
) {
    public static InventoryResponse of(Inventory inventory, Product product) {
        return new InventoryResponse(inventory.getId(), product.getId(), product.getName(), inventory.getStock());
    }
}
