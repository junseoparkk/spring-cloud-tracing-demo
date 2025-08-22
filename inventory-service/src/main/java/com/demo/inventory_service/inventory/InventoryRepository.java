package com.demo.inventory_service.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    Inventory findByProductId(Long productId);
}
