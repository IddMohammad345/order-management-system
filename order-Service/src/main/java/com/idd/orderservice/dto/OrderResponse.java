package com.idd.orderservice.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderResponse(
        Long orderId,
        Long userId,
        BigDecimal totalAmount,
        String orderStatus,
        String paymentStatus,
        Instant createdAt,
        List<OrderItemResponse>items
) {
}
