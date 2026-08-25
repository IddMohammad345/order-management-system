package com.idd.orderservice.dto;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        Long categoryId,
        String categoryName,
        String name,
        String description,
        String sku,
        BigDecimal price,
        String status
) {
}
