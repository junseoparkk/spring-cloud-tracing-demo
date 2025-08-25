package com.demo.order_service.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryClient {

    @PostMapping("/inventories/decrease")
    InventoryDto decreaseStock(
            @RequestParam Long productId,
            @RequestParam int quantity
    );
}
