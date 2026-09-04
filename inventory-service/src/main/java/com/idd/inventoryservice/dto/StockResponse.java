package com.idd.inventoryservice.dto;

public record StockResponse(
        Long productId,
        Long orderId,
        Integer quantity,
        String status
) {
}
