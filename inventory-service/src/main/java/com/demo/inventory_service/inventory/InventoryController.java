package com.demo.inventory_service.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventories")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryResponse> createInventory(
            @RequestBody InventoryRequest request
    ) {
        return ResponseEntity.ok(inventoryService.create(request));
    }

    @PostMapping("/decrease")
    public ResponseEntity<InventoryResponse> decreaseStock(
            @RequestParam Long productId,
            @RequestParam int quantity
    ) {
        return ResponseEntity.ok(inventoryService.decreaseStock(productId, quantity));
    }
}
