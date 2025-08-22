package com.demo.order_service;

import jakarta.ws.rs.QueryParam;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
                @RequestBody OrderRequest request
    ) {
        return ResponseEntity.ok(orderService.create(request));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getByOrderId(
            @PathVariable(value = "orderId") Long orderId
    ) {
        return ResponseEntity.ok(orderService.findById(orderId));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrdersByUserId(
            @RequestParam(value = "userId") Long userId
    ) {
        return ResponseEntity.ok(orderService.findAllByUserId(userId));
    }
}
