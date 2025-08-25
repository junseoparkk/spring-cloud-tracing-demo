package com.demo.inventory_service.inventory;

import com.demo.inventory_service.product.Product;
import com.demo.inventory_service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductService productService;

    @Transactional
    public InventoryResponse create(InventoryRequest request) {
        Product product = productService.getById(request.productId());

        Inventory inventory = Inventory.builder()
                .productId(product.getId())
                .stock(request.quantity())
                .build();
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryResponse.of(savedInventory, product);
    }

    @Transactional
    public InventoryResponse decreaseStock(Long productId, int quantity) {
        Product product = productService.getById(productId);
        Inventory inventory = inventoryRepository.findByProductId(productId).orElseThrow();

        if (inventory.getStock() < quantity) {
            throw new IllegalArgumentException("[ERROR] Not enough stock");
        }

        inventory.setStock(inventory.getStock() - quantity);
        inventoryRepository.save(inventory);
        return InventoryResponse.of(inventory, product);
    }
}
