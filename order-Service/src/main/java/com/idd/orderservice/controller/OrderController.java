package com.idd.orderservice.controller;

import com.idd.orderservice.dto.OrderCreateRequest;
import com.idd.orderservice.dto.OrderResponse;
import com.idd.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @RequestBody @Valid OrderCreateRequest orderCreateRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.createOrder(orderCreateRequest));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(
            @PathVariable Long orderId
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getOrder(orderId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderResponse>> getOrderByUser(
            @PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getOrderByUser(userId));
    }
}
