package com.idd.orderservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderCreateRequest(
    @NotNull(message = "User ID is required")
    Long userId,
    @NotNull(message = "Order must contain at least one item")
    List<@Valid OrderItemRequest> items
) {
}
