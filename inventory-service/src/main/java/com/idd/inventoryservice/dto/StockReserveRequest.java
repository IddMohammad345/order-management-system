package com.idd.inventoryservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record StockReserveRequest(
        @NotNull
        Long productId,
        @NotNull
        Long orderId,
        @NotNull
        @Min(1)
        Integer quantity
) {
}
