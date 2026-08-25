package com.idd.orderservice.dto;

public record ErrorResponse(
        int statusCode,
        String message
) {
}
