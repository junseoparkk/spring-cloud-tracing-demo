package com.demo.inventory_service.product;

public record ProductResponse(
        Long productId,
        String name,
        int price
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }
}
