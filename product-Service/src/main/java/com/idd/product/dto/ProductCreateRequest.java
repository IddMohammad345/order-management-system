package com.idd.product.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductCreateRequest(

        @NotNull(message = "Category ID is required")
        Long categoryId,

        @NotBlank(message = "Product name is required")
        @Size(max = 150)
        String name,

        @Size(max = 1000)
        String description,

        @NotBlank(message = "SKU is required")
        @Size(max = 50)
        String sku,

        @NotNull(message = "Price is required")
        @DecimalMin(
                value = "0.0",
                inclusive = true,
                message = "Price cannot be negative"
        )
        BigDecimal price
) {
}