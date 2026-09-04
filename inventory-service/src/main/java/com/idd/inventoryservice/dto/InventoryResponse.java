package com.idd.inventoryservice.dto;

import java.time.Instant;

public record InventoryResponse(
        Long inventoryId,
        Long productId,
        Integer availableQuantity,
        Integer reserveQuantity,
        String status,
        Instant updateAt
) {
}
