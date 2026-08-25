package com.idd.product.dto;

import java.time.Instant;

public record CategoryResponse(
        Long id,
        String name,
        String description,
        String status,
        Instant createdAt
) {
}
