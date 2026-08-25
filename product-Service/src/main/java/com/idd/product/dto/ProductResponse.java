package com.idd.product.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record ProductResponse(
        Long id,
        Long categoryId,
        String categoryName,
        String name,
        String description,
        String sku,
        BigDecimal price,
        String status,
        Instant createdAt
) {
}